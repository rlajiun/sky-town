package com.ssafy.happyhouse.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.response.ResponseHandler;
import com.ssafy.happyhouse.user.model.User;
import com.ssafy.happyhouse.user.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.user.model.service.NaverOAuthService;
import com.ssafy.happyhouse.user.model.service.UserServiceImpl;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/naver")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class NaverLoginController {

	private String CLIENT_ID = "FssPbW4KG1cD9O8YXEhN"; // 애플리케이션 클라이언트 아이디값";
	private String CLI_SECRET = "yX_9hprMxM"; // 애플리케이션 클라이언트 시크릿값";
	private final static String REDIRECT_URI = "http://localhost:9999/naver/callback1";
	private final static String SESSION_STATE = "oauth_state";
	private static String userEmail = null;
	private static String userName = null;
	private static String response = null;
	private static String token = null;

	public static final Logger logger = LoggerFactory.getLogger(NaverLoginController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	// 1. 네아로 연동 url 요청 - 네이버로그인 url 생성해서 사용자는 로그인 인증 + 네아로 연동 동의과정 수행
	// 2. 위에서 설정 해 놓은 url 처리하는 컨트롤러
	// 3. 인증 토큰으로 access token 으로 교환하기
	@Autowired
	private JwtServiceImpl jwtService;
	
	@Autowired
	private UserServiceImpl userService;
	
	private final NaverOAuthService oAuthService = new NaverOAuthService();
	
	
	//리1.vue 에서 /auth/naver 로 요청시
	//네이버 로그인폼으로 리다이렉트 해주는 controller 분리
	@GetMapping
	public void naverLogin(HttpServletRequest request, HttpServletResponse response) {
		final String loginUrl = oAuthService.getRequestLoginUrl();
		try {
			response.sendRedirect(loginUrl);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//리3. 콜백에 대한 처리 분리
	//
	@GetMapping("/callback")
	public ResponseEntity<Object> naverCallback(HttpSession session, HttpServletRequest request, Model model,
			HttpServletResponse httpServletResponse) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		final String apiURL = oAuthService.getApiUrl(code, state);
		String res = requestToServer(apiURL);////access-token, refresh-token 정보 얻어옴
		String profile = null;
		HttpStatus status = null;
		if (res != null && !res.equals("")) {
			//1)유저 profile 얻어오기
			Map<String, Object> parsedJson = new JSONParser(res).parseObject();
			profile = getProfileFromNaver(parsedJson.get("access_token").toString());
			System.out.println("profile>"+profile);
			userEmail = profile.split("email")[1];
			userEmail = userEmail.split(",")[0].split(":")[1];
			System.out.println("???"+userEmail);
			userEmail = userEmail.substring(1, userEmail.length() - 1);
			
			userName = profile.split("name")[1];
			userName = userName.split(",")[0].split(":")[1];
			userName = userName.substring(1, userName.length() - 3);
			
			System.out.println("userEmail: "+userEmail);
			String decodeVal = URLDecoder.decode(userName,  "UTF-8");
			System.out.println("userName: "+decodeVal);

			
			String userId = userEmail.split("@")[0];
			String userPwd = userId;
			//2) 없는 유저라면 회원가입 시키기
			
			//네이버로그인 정보로 회원가입 시킴(유저의 정보가 없는 경우만!)
			if(userService.idCheck(userId)==0) {
				User user = new User();
				user.setUserId(userId);
				user.setUserPwd(userPwd);
				user.setEmail(userEmail);
				user.setUserName("송진우");
				userService.registerMember(user);
			}
			
			//3) jwt 토큰 발급
			makeJWTToken();
			status = HttpStatus.ACCEPTED;

			URI redirectUri = new URI("http://localhost:8080/#/loginOk");
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.set("email", userEmail);
			httpHeaders.setLocation(redirectUri);
			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
		}else {
			return ResponseHandler.generateResponse("Fail to get user data", HttpStatus.MULTI_STATUS, null);
		}

	}
	
	
	private void makeJWTToken() {
		Map<String, Object> resultMap = new HashMap<>();
		token = jwtService.create("userid", userEmail, "access-token");// key, data, subject
		logger.debug("로그인 토큰정보 : {}", token);
	}

	@GetMapping("/login")
	public ResponseEntity<Map<String,Object>> getUserInfo() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		resultMap.put("access-token",token);
		resultMap.put("message", "SUCCESS");
		status = HttpStatus.ACCEPTED;
		//status 만들기
		
		return new ResponseEntity<>(resultMap,status);
	}
	
	@GetMapping("/user/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User memberDto = userService.userInfo(userid);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 토큰 삭제 컨트롤러
	@DeleteMapping
	public ResponseEntity<Map<String, Object>> deleteToken(HttpSession session, HttpServletRequest request, Model model)
			throws IOException, URISyntaxException {
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLI_SECRET;
		apiURL += "&access_token=" + request.getHeader("access-token");
		apiURL += "&service_provider=NAVER";
		System.out.println("apiURL=" + apiURL);
		String res = requestToServer(apiURL);
		model.addAttribute("res", res);
		session.invalidate();
		System.out.println("토큰 잘 삭제됨 ~~~");
		URI redirectUri = new URI("http://localhost:9999/");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUri);

		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}

	// 액세스 토큰으로 네이버에서 프로필 받기
	@GetMapping("/getProfile")
	public String getProfileFromNaver(String accessToken) throws IOException {
		// 네이버 로그인 접근 토큰;
		String apiURL = "https://openapi.naver.com/v1/nid/me";
		String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
		String res = requestToServer(apiURL, headerStr);
		response = res;
		System.out.println("getProfile res" + res);
		return res;
	}
	
	

	// 세션 무효화(로그아웃)
	@GetMapping("/invalidate")
	public String invalidateSession(HttpSession session) {
		session.invalidate();
		System.out.println("로그아웃....");
		return "redirect:/naver";
	}

	/**
	 * 서버 통신 메소드
	 * 
	 * @param apiURL
	 * @return
	 * @throws IOException
	 */
	private String requestToServer(String apiURL) throws IOException {
		return requestToServer(apiURL, "");
	}

	private String requestToServer(String apiURL, String headerStr) throws IOException {
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		System.out.println("header Str: " + headerStr);
		if (headerStr != null && !headerStr.equals("")) {
			con.setRequestProperty("Authorization", headerStr);
		}
		int responseCode = con.getResponseCode();
		BufferedReader br;
		System.out.println("responseCode=" + responseCode);
		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer res = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			res.append(inputLine);
		}
		br.close();
		if (responseCode == 200) {
			return res.toString();
		} else {
			return null;
		}
	}

}
