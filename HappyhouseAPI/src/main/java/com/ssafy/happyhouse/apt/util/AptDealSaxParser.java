package com.ssafy.happyhouse.apt.util;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.happyhouse.apt.model.AptDeal;

import lombok.Getter;

@Getter
public class AptDealSaxParser {
	private List<AptDeal> aptDealList;
	private int totalCount;

	public AptDealSaxParser(InputStream is) {
		loadAptDealData(is);
	}

	private void loadAptDealData(InputStream is) {
		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			SAXParser parser = factory.newSAXParser();
			AptDealSaxHandler handler = new AptDealSaxHandler();
			parser.parse(is, handler);
			aptDealList = handler.getAptDeal();
			totalCount = handler.getTotalCount();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
