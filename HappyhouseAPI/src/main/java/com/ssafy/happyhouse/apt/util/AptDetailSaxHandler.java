package com.ssafy.happyhouse.apt.util;

import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.apt.model.Apt;

/**
 * 아파트 상세 정보를 읽어 파싱하는 핸들러 클래스
 */
public class AptDetailSaxHandler extends DefaultHandler {

	/** 아파트 정보를 담는다 */
	private Apt apt;
	/** 태그 바디 정보를 임시로 저장 */
	private String temp;

	public AptDetailSaxHandler(Apt apt) {
		this.apt = apt;
	}

//	@Override
//	public void startElement(String uri, String localName, String qName, Attributes att) {
//		if (qName.equals("item")) {
//			apt = new Apt();
//		}
//	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equals("codeGarbage")) {
			apt.setCodeGarbage(temp.trim());
		} else if (qName.equals("kaptdEcnt")) {
			apt.setAptdEcnt(temp.trim());
		} else if (qName.equals("kaptdPcnt")) {
			apt.setAptdPcnt(temp.trim());
		} else if (qName.equals("kaptdPcntu")) {
			apt.setAptdPcntu(temp.trim());
		} else if (qName.equals("codeNet")) {
			apt.setCodeNet(temp.trim());
		} else if (qName.equals("kaptdCccnt")) {
			apt.setAptdCccnt(temp.trim());
		} else if (qName.equals("welfareFacility")) {
			apt.setWelfareFacility(temp.trim());
		} else if (qName.equals("kaptdWtimebus")) {
			apt.setAptdWtimebus(temp.trim());
		} else if (qName.equals("subwayLine")) {
			apt.setSubwayLine(temp.trim());
		} else if (qName.equals("subwayStation")) {
			apt.setSubwayStation(temp.trim());
		} else if (qName.equals("kaptdWtimesub")) {
			apt.setAptdWtimesub(temp.trim());
		} else if (qName.equals("convenientFacility")) {
			apt.setConvenientFacility(temp.trim());
		} else if (qName.equals("educationFacility")) {
			apt.setEducationFacility(temp.trim());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		temp = new String(ch, start, length);
	}

	public Apt getApt() {
		return apt;
	}

}
