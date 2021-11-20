package com.ssafy.happyhouse.util;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.ssafy.happyhouse.apt.model.Apt;

import lombok.Getter;

@Getter
public class AptSaxParser {
	private List<Apt> aptList;
	private Apt apt;
	private int totalCount;

	public AptSaxParser(InputStream data) {
		loadAptData(data);
	}

	public AptSaxParser(Apt apt, InputStream dataInfo, InputStream dataDetail) {
		this.apt = apt;
		loadAptMoreData(dataInfo, dataDetail);
	}

	private void loadAptData(InputStream data) {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			SAXParser parser = factory.newSAXParser();
			AptSaxHandler handler = new AptSaxHandler();
			parser.parse(data, handler);
			aptList = handler.getAptList();
			totalCount = handler.getTotalCount();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadAptMoreData(InputStream dataInfo, InputStream dataDetail) {

		SAXParserFactory factory = SAXParserFactory.newInstance();

		try {
			SAXParser parser = factory.newSAXParser();
			
			AptInfoSaxHandler infoHandler = new AptInfoSaxHandler(apt);
			parser.parse(dataInfo, infoHandler);
			apt = infoHandler.getApt();
			
			AptDetailSaxHandler detailHandler = new AptDetailSaxHandler(apt);
			parser.parse(dataDetail, detailHandler);
			apt = detailHandler.getApt();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Apt getApt() {
		return apt;
	}

}
