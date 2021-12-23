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
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.apt.model.AptDeal;
import com.ssafy.happyhouse.apt.model.service.AptService;
import com.ssafy.happyhouse.apt.util.AptDealSaxParser;
import com.ssafy.happyhouse.apt.util.AptSaxParser;

/**
 * 정해진 시간마다 공공데이터 API에서 데이터를 받아 파싱해 데이터베이스에 저장하는 스케줄러 클래스
 * 
 * @author Kim Jiun
 *
 */
@EnableAsync
@Component
public class SchedulerConfig {
	private final static String serviceKey = "oymQn0W8EVpAimvw4XJZnAIxO9Fsc6%2FmQ1sKJ%2FEa%2F9FgjfJFi3Ry%2FMAPq1Iv8AtlAzlA0Wn%2FQVZ1DtjI35ntQg%3D%3D";
	@Autowired
	private AptService aptService;
	private int totalCount = 1;
	private List<AptInfo> aptList;

//	@Scheduled(fixedDelay = 1000*60*60*24) 
	public void aptDealUpdateTask() throws Exception {
		List<String> codes = aptService.getGugunCodeList();
		int mon = 11;

		for (int i = 0; i < codes.size(); i++) {
			for (int k = mon; k > mon - 6; k--) {
				for (int j = 1; j <= totalCount / 100 + 1; j++) {
					StringBuilder urlBuilder = new StringBuilder(
							"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
																																		 * URL
																																		 */
					urlBuilder.append(
							"?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
					urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
							+ URLEncoder.encode(Integer.toString(j), "UTF-8")); /* 페이지번호 */
					urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
							+ URLEncoder.encode("100", "UTF-8")); /* 한 페이지 결과 수 */
					urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "="
							+ URLEncoder.encode(codes.get(i), "UTF-8")); /* 지역코드 */
					urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "="
							+ URLEncoder.encode("2021" + Integer.toString(k), "UTF-8")); /* 계약월 */
					URL url = new URL(urlBuilder.toString());
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Content-type", "application/json");
					System.out.println("Response code: " + conn.getResponseCode());
					InputStream is;
					if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
						is = conn.getInputStream();
						AptDealSaxParser parser = new AptDealSaxParser(is);
						List<AptDeal> aptDeal = parser.getAptDealList();
						if (aptDeal != null && aptDeal.size() > 0) {
							System.out.println(aptDeal);
							aptService.insertAptDealList(aptDeal);
						}
						totalCount = parser.getTotalCount();
					} else {
						is = conn.getErrorStream();
					}
					is.close();
					conn.disconnect();
				}
			}
		}
	}

	@Scheduled(cron = "0 0 0 1 * ?", zone = "Asia/Seoul") // 매월 1일 정오에 실행
	public void aptUpdateTask() throws InterruptedException, IOException {
		System.out.println("시작");
		aptList = new ArrayList<AptInfo>();
		for (int i = 1; i <= totalCount / 1000 + 1; i++) {
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/1613000/AptListService2/getTotalAptList"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey); /* Service Key */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode(Integer.toString(i), "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("5000", "UTF-8")); /* 목록 건수 */
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			InputStream is;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				is = conn.getInputStream();
				AptSaxParser parser = new AptSaxParser(is);
				List<AptInfo> apt = parser.getAptList();
				if (apt != null) {
					aptList.addAll(apt);
				}
				totalCount = parser.getTotalCount();
				// System.out.println("Response code: " + conn.getResponseCode());
			} else {
				is = conn.getErrorStream();
			}
			is.close();
			conn.disconnect();
		}

		for (int i = 0; i < aptList.size(); i++) {
//			System.out.println(i + "번째 아파트");
			aptMoreSet(i, aptList.get(i).getAptCode());
//			System.out.println(aptList.get(i));
		}

		try {
			aptService.insertAptList(aptList); // 데이터베이스한테는 요청
			aptService.insertAptInfoList(aptList);
			aptService.insertAptDetailList(aptList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void aptMoreSet(int index, String aptCode) throws InterruptedException, IOException {
		StringBuilder infoUrlBuilder = setbuilder(
				"http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusBassInfo", serviceKey, aptCode);
		StringBuilder detailUrlBuilder = setbuilder(
				"http://apis.data.go.kr/1613000/AptBasisInfoService1/getAphusDtlInfo", serviceKey, aptCode);
		URL infoUrl = new URL(infoUrlBuilder.toString());
		URL detailUrl = new URL(detailUrlBuilder.toString());

		HttpURLConnection infoConn = (HttpURLConnection) infoUrl.openConnection();
		infoConn.setRequestMethod("GET");
		infoConn.setRequestProperty("Content-type", "application/json");

		HttpURLConnection detailConn = (HttpURLConnection) detailUrl.openConnection();
		detailConn.setRequestMethod("GET");
		detailConn.setRequestProperty("Content-type", "application/json");

		InputStream isInfo = null, isDetail = null;
		if (infoConn.getResponseCode() >= 200 && infoConn.getResponseCode() <= 300
				&& detailConn.getResponseCode() >= 200 && detailConn.getResponseCode() <= 300) {
			isInfo = infoConn.getInputStream();
			isDetail = detailConn.getInputStream();
			AptSaxParser parser = new AptSaxParser(aptList.get(index), isInfo, isDetail);
			aptList.set(index, parser.getApt());
		} else {
			isInfo = infoConn.getErrorStream();
			isDetail = detailConn.getErrorStream();
		}
		isInfo.close();
		isDetail.close();
		detailConn.disconnect();
		infoConn.disconnect();
	}

	public static StringBuilder setbuilder(String url, String key, String aptCode) throws UnsupportedEncodingException {
		StringBuilder urlBuilder = new StringBuilder(url); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + key); /* Service Key */
		urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "="
				+ URLEncoder.encode("인증키(URL Encode)", "UTF-8")); /* 공공데이터포털에서 발급받은 인증키 */
		urlBuilder.append(
				"&" + URLEncoder.encode("kaptCode", "UTF-8") + "=" + URLEncoder.encode(aptCode, "UTF-8")); /* 단지코드 */
		return urlBuilder;
	}
}
