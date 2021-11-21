package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/naver")
public class NaverLoginController {

	private String CLIENT_ID = "***********"; // 애플리케이션 클라이언트 아이디값";
	private String CLI_SECRET = "**********"; // 애플리케이션 클라이언트 시크릿값";

	// 로그인 화면이 있는 페이지 컨트롤 -> 이부분 나중에 vue 로 바꾸기
	//1. 네아로 연동 url 요청 - 네이버로그인 url 생성해서 사용자는 로그인 인증 + 네아로 연동 동의과정 수행
//	@GetMapping
//	public String testNaver(HttpSession session, Model model)
//			throws UnsupportedEncodingException, UnknownHostException {
//		String redirectURI = URLEncoder.encode("http://localhost:9999/naver/login/oauth/code", "UTF-8"); //사용자가 로그인 연동에 동의 하였을 경우 동의 정보를 포함하여 callback url 전송
//		SecureRandom random = new SecureRandom();
//		String state = new BigInteger(130, random).toString();
//		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";//네아로 연동 url 요청
//		apiURL += String.format("&client_id=%s&redirect_uri=%s&state=%s", CLIENT_ID, redirectURI, state);
//		session.setAttribute("state", state);
//		model.addAttribute("apiURL", apiURL);
//		return "test-naver";
//	}

	// 2. 위에서 설정 해 놓은 url 처리하는 컨트롤러
	// 3. 인증 토큰으로 access token 으로 교환하기
	
	@RequestMapping("/login/oauth/code/naver")
	public class OAuthTestController{
		
	}
	
	@GetMapping
	public String naverOAuthRedirect(@RequestParam String code, @RequestParam String status) {
		//status code 무시
		return "code: " +code;
	}
	
	
	
	//통신 갱신 요청 페이지 컨트롤러
	@RequestMapping("/naver/refreshToken")
	  public String refreshToken(HttpSession session, HttpServletRequest request, Model model, String refreshToken) throws IOException, ParseException {
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&";
	    apiURL += "client_id=" + CLIENT_ID;
	    apiURL += "&client_secret=" + CLI_SECRET;
	    apiURL += "&refresh_token=" + refreshToken;
	    System.out.println("apiURL=" + apiURL);
	    String res = requestToServer(apiURL);
	    model.addAttribute("res", res);
	    session.invalidate();
	    return "test-naver-callback";
	  }
	
	
	//토큰 삭제 컨트롤러
	@RequestMapping("/naver/deleteToken")
	  public String deleteToken(HttpSession session, HttpServletRequest request, Model model, String accessToken) throws IOException {
	    String apiURL;
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=delete&";
	    apiURL += "client_id=" + CLIENT_ID;
	    apiURL += "&client_secret=" + CLI_SECRET;
	    apiURL += "&access_token=" + accessToken;
	    apiURL += "&service_provider=NAVER";
	    System.out.println("apiURL=" + apiURL);
	    String res = requestToServer(apiURL);
	    model.addAttribute("res", res);
	    session.invalidate();
	    return "test-naver-callback";
	  }

	//액세스 토큰으로 네이버에서 프로필 받기
	  @ResponseBody
	  @RequestMapping("/naver/getProfile")
	  public String getProfileFromNaver(String accessToken) throws IOException {
	    // 네이버 로그인 접근 토큰;
	    String apiURL = "https://openapi.naver.com/v1/nid/me";
	    String headerStr = "Bearer " + accessToken; // Bearer 다음에 공백 추가
	    String res = requestToServer(apiURL, headerStr);
	    return res;
	  }

	//세션 무효화(로그아웃)
	  @RequestMapping("/naver/invalidate")
	  public String invalidateSession(HttpSession session) {
	    session.invalidate();
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
