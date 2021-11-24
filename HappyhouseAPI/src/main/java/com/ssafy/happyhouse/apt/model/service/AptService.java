package com.ssafy.happyhouse.apt.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.AptDeal;
import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.apt.model.AptInfoBasic;

public interface AptService {
	void insertAptList(List<AptInfo> aptList) throws Exception;

	void insertAptInfoList(List<AptInfo> aptList) throws Exception;

	void insertAptDetailList(List<AptInfo> aptList) throws Exception;

	void insertAptDealList(List<AptDeal> aptDealList) throws Exception;

	List<String> getGugunCodeList() throws Exception;

	Apt getApt(String aptCode) throws SQLException;

	List<AptInfoBasic> getAptList(String parent) throws SQLException;
}
