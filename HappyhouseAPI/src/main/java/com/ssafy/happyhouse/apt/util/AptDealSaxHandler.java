package com.ssafy.happyhouse.apt.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.math.Arrays;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.apt.model.AptDeal;

/**
 * 아파트 매매 정보를 읽어 파싱하는 핸들러 클래스
 */
public class AptDealSaxHandler extends DefaultHandler {

	/** 거래 정보를 담는다 */
	private List<AptDeal> aptDealList;
	/** 파싱한 거래 정보 */
	private AptDeal aptDeal;
	/** 태그 바디 정보를 임시로 저장 */
	private String temp;
	private String sigun, dong;
	/** 총 결과 수 저장 */
	private int totalCount;

	public AptDealSaxHandler() {
		aptDealList = new ArrayList<AptDeal>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes att) {
		if (qName.equals("item")) {
			aptDeal = new AptDeal();
			sigun = null;
			dong = null;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (qName.equals("거래금액")) {
			String[] str = temp.trim().split(",");
			String s = "";
			for (String string : str) {
				s += string;
			}
			aptDeal.setDealAmount(s);
		} else if (qName.equals("건축년도")) {
			aptDeal.setBuildYear(Integer.parseInt(temp.trim()));
		} else if (qName.equals("년")) {
			aptDeal.setDealYear(Integer.parseInt(temp.trim()));
		} else if (qName.equals("법정동시군구코드")) {
			sigun = temp.trim();
		} else if (qName.equals("법정동읍면동코드")) {
			dong = temp.trim();
		} else if (qName.equals("아파트")) {
			aptDeal.setAptName(temp.trim());
		} else if (qName.equals("월")) {
			aptDeal.setDealMonth(Integer.parseInt(temp.trim()));
		} else if (qName.equals("일")) {
			aptDeal.setDealDay(Integer.parseInt(temp.trim()));
		} else if (qName.equals("전용면적")) {
			aptDeal.setDealArea(temp.trim());
		} else if (qName.equals("중개사소재지")) {
			aptDeal.setRedealerLawdnm(temp.trim());
		} else if (qName.equals("지번")) {
			aptDeal.setJibun(temp.trim());
		} else if (qName.equals("층")) {
			aptDeal.setFloor(Integer.parseInt(temp.trim()));
		} else if (qName.equals("해제사유발생일")) {
			aptDeal.setCancelDealDay(temp.trim());
		} else if (qName.equals("해제여부")) {
			aptDeal.setCancelDealType(temp.trim());
		} else if (qName.equals("거래유형")) {
			aptDeal.setReqGbn(temp.trim());
		} else if (qName.equals("item")) {
			aptDeal.setDongCode(sigun + dong);
			aptDealList.add(aptDeal);
			System.out.println(aptDeal);
		} else if (qName.equals("totalCount")) {
			totalCount = Integer.parseInt(temp.trim());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		temp = new String(ch, start, length);
	}

	public List<AptDeal> getAptDeal() {
		return aptDealList;
	}

	public int getTotalCount() {
		return totalCount;
	}

}
