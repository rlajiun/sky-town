package com.ssafy.happyhouse.apt.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.apt.model.AptInfo;

/**
 * 단지 정보를 읽어 파싱하는 핸들러 클래스
 */
public class AptSaxHandler extends DefaultHandler {
	
	/** 아파트 정보를 담는다 */
	private List<AptInfo> aptList;
	/** 파싱한 아파트 정보 */
	private AptInfo apt;
	/** 태그 바디 정보를 임시로 저장 */
	private String temp;
	/** 총 결과 수 저장 */
	private int totalCount;

	public AptSaxHandler() {
		aptList = new ArrayList<AptInfo>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes att) {
		if (qName.equals("item")) {
			apt = new AptInfo();
		}
	}
	
    @Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equals("as1")) {
			apt.setSidoName(temp.trim());
		} else if (qName.equals("as2")) {
			apt.setGugunName(temp.trim());
		} else if (qName.equals("as3")) {
			apt.setDongName(temp.trim());
		} else if (qName.equals("as4")) {
			apt.setLeeName(temp.trim());
		} else if (qName.equals("bjdCode")) {
			apt.setDongCode(temp.trim());
		} else if (qName.equals("kaptCode")) {
			apt.setAptCode(temp.trim());
		} else if (qName.equals("kaptName")) {
			apt.setAptName(temp.trim());
		} else if (qName.equals("item")) {
			aptList.add(apt);
//			System.out.println(apt);
		} else if(qName.equals("totalCount")) {
			totalCount = Integer.parseInt(temp.trim());
		}
	}
    
    @Override
	public void characters(char[] ch, int start, int length) {
		temp = new String(ch, start, length);
	}

	public List<AptInfo> getAptList() {
		return aptList;
	}
	
	public int getTotalCount() {
		return totalCount;
	}
}
