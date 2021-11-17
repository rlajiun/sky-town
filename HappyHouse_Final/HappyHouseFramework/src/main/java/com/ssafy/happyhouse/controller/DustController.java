package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ssafy.happyhouse.model.DustDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DustController {
	@RequestMapping(value = "dustInfo", method = RequestMethod.GET)
	public String dustPage() throws IOException{
		return "dustInfo";
	}
	
	@ResponseBody
	@GetMapping("/ajax/dustInfo")
	public ResponseEntity<List<DustDto>> search() throws ParserConfigurationException, SAXException, IOException { // 네이버랑 통신해서 데이터 받아오자. 마치 네이버가 디비인것처럼.
        String apiURL = "http://openapi.seoul.go.kr:8088/726c6b4b5a737969393555424e696f/xml/RealtimeCityAir/1/10";    // xml결과
        
        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
        Document doc = dBuilder.parse(apiURL);
        System.out.println("doc"+doc.toString());
     // root tag 
        doc.getDocumentElement().normalize();
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("row");
		System.out.println("파싱할 리스트 수: "+nList.getLength());
		
		ArrayList<DustDto> list = new ArrayList<>();
		
		for(int i=0;i<nList.getLength();i++) {
			Node nNode = nList.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element)nNode;
				//Dto 
				System.out.println("구: "+ getTagValue("MSRSTE_NM",eElement));
				System.out.println("미세먼지: "+ getTagValue("PM10", eElement));
				System.out.println("초미세먼지 농도: "+getTagValue("PM25", eElement));
				System.out.println("오존(ppm)"+getTagValue("O3", eElement));
				System.out.println("이산화질소농도(ppm)"+getTagValue("NO2", eElement));
				System.out.println("통합대기환경등급"+getTagValue("IDEX_NM", eElement));
				
				list.add(new DustDto(
						getTagValue("MSRSTE_NM",eElement).toString(),
						getTagValue("PM10",eElement).toString(),
						getTagValue("PM25",eElement).toString(),
						getTagValue("O3",eElement).toString(),
						getTagValue("NO2",eElement).toString(),
						getTagValue("IDEX_NM",eElement).toString()
				));
				
			}
		}
		
		return new ResponseEntity<List<DustDto>>(list, HttpStatus.OK);
		//return ResponseEntity.ok().body(list); // -> 빌더패턴
		
	}

	

	private Object getTagValue(String tag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		Node nValue = (Node)nlList.item(0);
		if(nValue == null) {
			return null;
		}
		return nValue.getNodeValue();
	}

	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
	

}
