package com.ssafy.happyhouse.apt.util;

import org.xml.sax.helpers.DefaultHandler;

import com.ssafy.happyhouse.apt.model.AptInfo;

/**
 * 아파트 기본 정보를 읽어 파싱하는 핸들러 클래스
 */
public class AptInfoSaxHandler extends DefaultHandler {

	/** 아파트 정보를 담는다 */
	private AptInfo apt;
	/** 태그 바디 정보를 임시로 저장 */
	private String temp;

	public AptInfoSaxHandler(AptInfo apt) {
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
		if (qName.equals("codeAptNm")) {
			apt.setCodeAptNm(temp.trim());
		} else if (qName.equals("codeHallNm")) {
			apt.setCodeHallNm(temp.trim());
		} else if (qName.equals("codeHeatNm")) {
			apt.setCodeHeatNm(temp.trim());
		} else if (qName.equals("codeMgrNm")) {
			apt.setCodeMgrNm(temp.trim());
		} else if (qName.equals("codeSaleNm")) {
			apt.setCodeSaleNm(temp.trim());
		} else if (qName.equals("doroJuso")) {
			apt.setDoroJuso(temp.trim());
		} else if (qName.equals("hoCnt")) {
			apt.setHoCnt(temp.trim());
		} else if (qName.equals("kaptAddr")) {
			apt.setAptAddr(temp.trim());
		} else if (qName.equals("kaptDongCnt")) {
			apt.setAptDongCnt(temp.trim());
		} else if (qName.equals("kaptFax")) {
			apt.setAptFax(temp.trim());
		} else if (qName.equals("kaptMarea")) {
			apt.setAptMarea(temp.trim());
		} else if (qName.equals("kaptMparea_135")) {
			apt.setAptMparea_135(temp.trim());
		} else if (qName.equals("kaptMparea_136")) {
			apt.setAptMparea_136(temp.trim());
		} else if (qName.equals("kaptMparea_60")) {
			apt.setAptMparea_60(temp.trim());
		} else if (qName.equals("kaptMparea_85")) {
			apt.setAptMparea_85(temp.trim());
		} else if (qName.equals("kaptName")) {
			apt.setAptName(temp.trim());
		} else if (qName.equals("kaptTarea")) {
			apt.setAptTarea(temp.trim());
		} else if (qName.equals("kaptTel")) {
			apt.setAptTel(temp.trim());
		} else if (qName.equals("kaptUrl")) {
			apt.setAptUrl(temp.trim());
		} else if (qName.equals("kaptUsedate")) {
			apt.setAptUsedate(temp.trim());
		} else if (qName.equals("kaptdaCnt")) {
			apt.setAptdaCnt(temp.trim());
		} else if (qName.equals("privArea")) {
			apt.setPrivArea(temp.trim());
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		temp = new String(ch, start, length);
	}

	public AptInfo getApt() {
		return apt;
	}

}
