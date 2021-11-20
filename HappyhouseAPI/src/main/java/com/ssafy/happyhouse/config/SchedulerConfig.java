package com.ssafy.happyhouse.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.service.AptService;
import com.ssafy.happyhouse.util.AptSaxParser;

@EnableAsync // 추가
@Component
public class SchedulerConfig {
	private final static String serviceKey = "d1Rx181izwjWsfI72cBRRZ648mlRP778AFOTWt%2FgwmGn5lz1OmJGGmbxejtDDXWjvJP8CdO1Th3fjy4zmYcVYg%3D%3D";
	@Autowired
	private AptService aptService;
	private int totalCount = 1;
	private List<Apt> aptList;
	
	@Async
//	@Scheduled(fixedRate = 1000, cron = "0 0 0 1 * ?", zone = "Asia/Seoul") // 매월 1일 정오에 실행
	@Scheduled(cron = "0 36 11 20 11 ?", zone = "Asia/Seoul") // 
	public void aptUpdateTask() throws InterruptedException, IOException {
		aptList = new ArrayList<Apt>();
		for(int i = 1; i <= totalCount / 1000 + 1; i++) {
//		for(int i = 1; i <= 1; i++) {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/AptListService2/getTotalAptList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("인증키(URL E.ncode)", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(Integer.toString(i), "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*목록 건수*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        InputStream is;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        	is = conn.getInputStream();
        	AptSaxParser parser = new AptSaxParser(is);
        	List<Apt> apt = parser.getAptList();
        	if (apt != null) {
        		aptList.addAll(apt);
			}
        	totalCount = parser.getTotalCount();
//        System.out.println("Response code: " + conn.getResponseCode());
        } else {
        	is = conn.getErrorStream();
        }
        is.close();
        conn.disconnect();
		}
		
		for (int i = 0; i < aptList.size(); i++) {
			aptMoreSet(i, aptList.get(i).getAptCode());
		}
		
//		System.out.println(aptList);
        try {
        	aptService.insertAptList(aptList); // 데이터베이스한테는 요청
        	System.out.println("아파트 목록 insert 완료");
        	aptService.insertAptInfoList(aptList);
        	System.out.println("아파트 기본 정보 목록 insert 완료");
        	aptService.insertAptDetailList(aptList);
        	System.out.println("아파트 상세 정보 목록 insert 완료");
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	public void aptMoreSet(int index, String aptCode) throws InterruptedException, IOException {
		StringBuilder infoUrlBuilder = setbuilder("http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusBassInfo", serviceKey, aptCode);
		StringBuilder detailUrlBuilder = setbuilder("http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusDtlInfo", serviceKey, aptCode);
        URL infoUrl = new URL(infoUrlBuilder.toString());
        URL detailUrl = new URL(detailUrlBuilder.toString());
        
        HttpURLConnection infoConn = (HttpURLConnection) infoUrl.openConnection();
        infoConn.setRequestMethod("GET");
        infoConn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + infoConn.getResponseCode());
        
        HttpURLConnection detailConn = (HttpURLConnection) detailUrl.openConnection();
        detailConn.setRequestMethod("GET");
        detailConn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + detailConn.getResponseCode());
        
        InputStream isInfo = null, isDetail = null;
        if(infoConn.getResponseCode() >= 200 && infoConn.getResponseCode() <= 300 && detailConn.getResponseCode() >= 200 && detailConn.getResponseCode() <= 300) {
        	isInfo = infoConn.getInputStream();
        	isDetail = detailConn.getInputStream();
        	AptSaxParser parser = new AptSaxParser(aptList.get(index), isInfo, isDetail);
        	aptList.set(index, parser.getApt());
        }else {
        	isInfo = infoConn.getErrorStream();
        	isDetail = detailConn.getErrorStream();
        }
        isInfo.close();
        isDetail.close();
        detailConn.disconnect();
        infoConn.disconnect();
	}
	
	public static StringBuilder setbuilder(String url, String key, String aptCode) throws UnsupportedEncodingException {
		StringBuilder urlBuilder = new StringBuilder(url); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode("인증키(URL Encode)", "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("kaptCode","UTF-8") + "=" + URLEncoder.encode(aptCode, "UTF-8")); /*단지코드*/
        return urlBuilder;	
	}
}
