package com.ssafy.happyhouse.user.model.service;

import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec.MAP;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NaverOAuthService {
	private final String CLIENT_ID = "FssPbW4KG1cD9O8YXEhN"; // 애플리케이션 클라이언트 아이디값";
	private final String CLIENT_SECRET = "yX_9hprMxM"; // 애플리케이션 클라이언트 시크릿값";
	private final String NAVER_CALLBACK_URL = "http://localhost:9999/naver/callback";
	private final String NAVER_BASE_URL = "https://nid.naver.com/oauth2.0/authorize";
	private final String NAVER_TOKEN_BASE_URL = "https://oauth2.googleapis.com/token";
	private final String REDIRECT_URI = "http://localhost:9999/naver/callback1";
	
	public String getRequestLoginUrl() {
		final String state = new BigInteger(130, new SecureRandom()).toString();
		
		MultiValueMap<String,String> requestParam = new LinkedMultiValueMap<>();
		requestParam.add("response_type", "code");
		requestParam.add("state", state);
		requestParam.add("client_id", CLIENT_ID);
		requestParam.add("redirect_uri", NAVER_CALLBACK_URL);
		
		return UriComponentsBuilder.fromUriString(NAVER_BASE_URL)
				.queryParams(requestParam)
				.build().encode()
				.toString();
	}
	
	public String getApiUrl(String code, String state) {
		String apiURL = null;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + CLIENT_ID;
		apiURL += "&client_secret=" + CLIENT_SECRET;
		apiURL += "&redirect_uri=" + REDIRECT_URI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		return apiURL;
	}

}
