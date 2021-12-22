package com.ssafy.happyhouse.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Map<String, Object>> naverCallback(HttpSession session, HttpServletRequest request, Model model,
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
			profile = profile.split("email")[1];
			profile = profile.split(",")[0].split(":")[1];
			userEmail = profile.substring(1, profile.length() - 1);
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
			
		}else {
			
		}
		
		URI redirectUri = new URI("http://localhost:9999/#/loginOk");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("email", userEmail);
		httpHeaders.setLocation(redirectUri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	

//	@RequestMapping("/callback")
//	public ResponseEntity<Map<String, Object>> naverCallback(HttpSession session, HttpServletRequest request, Model model,
//			HttpServletResponse httpServletResponse) throws Exception {
//		Map<String, Object> resultMap = new HashMap<>();
//		HttpStatus status = null;
//		String code = request.getParameter("code");
//		String state = request.getParameter("state");
//		String redirectURI = URLEncoder.encode("http://localhost:9999/naver/callback1", "UTF-8");
//		String apiURL;
//		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
//		apiURL += "client_id=" + CLIENT_ID;
//		apiURL += "&client_secret=" + CLI_SECRET;
//		apiURL += "&redirect_uri=" + redirectURI;
//		apiURL += "&code=" + code;
//		apiURL += "&state=" + state;
//		System.out.println("apiURL=" + apiURL);
//		
//		String res = requestToServer(apiURL);
//		System.out.println("requestToServer 역할: "+res); //access-token, refresh-token 정보 얻어옴
//		String profile = null;
//
//		if (res != null && !res.equals("")) {
//			Map<String, Object> parsedJson = new JSONParser(res).parseObject();
//			System.out.println("parsedJson" + parsedJson);
//			profile = getProfileFromNaver(parsedJson.get("access_token").toString());
//
//			//? service 로 옮겨야 하나??
//			//네이버에서 받은 프로파일로 데이터 가공해 email, id, pwd 알아내기
//			profile = profile.split("email")[1];
//			profile = profile.split(",")[0].split(":")[1];
//			userEmail = profile.substring(1, profile.length() - 1);
//			String userId = userEmail.split("@")[0];
//			String userPwd = userId;
//			System.out.println("email: " + userEmail);
//			//네이버로그인 정보로 회원가입 시킴(유저의 정보가 없는 경우만!)
//			if(userService.idCheck(userId)==0) {
//				User user = new User();
//				user.setUserId(userId);
//				user.setUserPwd(userPwd);
//				user.setEmail(userEmail);
//				user.setUserName("송진우");
//				userService.registerMember(user);
//			}
//
//			// jwt 토큰 만들기
//			token = jwtService.create("userid", userEmail, "access-token");// key, data, subject
//			logger.debug("로그인 토큰정보 : {}", token);
//			resultMap.put("access-token", token);
//			resultMap.put("message", SUCCESS);
//			resultMap.put("userEmail", userEmail);
//			status = HttpStatus.ACCEPTED;
//
//			
//		} else {
//			resultMap.put("message", FAIL);
//			status = HttpStatus.ACCEPTED;
//		}
//
//		URI redirectUri = new URI("http://localhost:9999/#/loginOk");
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.set("email", userEmail);
//		httpHeaders.setLocation(redirectUri);
//
//		return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//	}

	
	private void makeJWTToken() {
		Map<String, Object> resultMap = new HashMap<>();
		token = jwtService.create("userid", userEmail, "access-token");// key, data, subject
		logger.debug("로그인 토큰정보 : {}", token);
//		resultMap.put("access-token", token);
//		resultMap.put("message", SUCCESS);
//		resultMap.put("userEmail", userEmail);
	}

	@RequestMapping("/getuserlogin")
	public ResponseEntity<Map<String,Object>> getUserInfo() {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		resultMap.put("access-token",token);
		resultMap.put("message", "SUCCESS");
		status = HttpStatus.ACCEPTED;
		
		return new ResponseEntity<>(resultMap,status);
	}
	
	@RequestMapping("/getuserinfo/{userid}")
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
	

	// 통신 갱신 요청 페이지 컨트롤러
//	@RequestMapping("/refreshToken")
//	public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken)
//			throws IOException, ParseException {
//		String apiURL;
//		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
//		apiURL += "client_id=" + CLIENT_ID;
//		apiURL += "&client_secret=" + CLI_SECRET;
//		apiURL += "&refresh_token=" + refreshToken;
//		System.out.println("apiURL=" + apiURL);
//		String res = requestToServer(apiURL);
//		model.addAttribute("res", res);
//		session.invalidate();
//		return "test-naver-callback";
//	}

	// 토큰 삭제 컨트롤러
//	@RequestMapping("/deleteToken")
//	public ResponseEntity<Map<String, Object>> deleteToken(HttpSession session, HttpServletRequest request, Model model)
//			throws IOException, URISyntaxException {
//		String apiURL;
//		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
//		apiURL += "client_id=" + CLIENT_ID;
//		apiURL += "&client_secret=" + CLI_SECRET;
//		apiURL += "&access_token=" + request.getHeader("access-token");
//		apiURL += "&service_provider=NAVER";
//		System.out.println("apiURL=" + apiURL);
//		String res = requestToServer(apiURL);
//		model.addAttribute("res", res);
//		session.invalidate();
//		System.out.println("토큰 잘 삭제됨 ~~~");
//		URI redirectUri = new URI("http://localhost:9999/");
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setLocation(redirectUri);
//
//		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
//	}

	// 액세스 토큰으로 네이버에서 프로필 받기
	@RequestMapping("/getProfile")
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
	@RequestMapping("/invalidate")
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
