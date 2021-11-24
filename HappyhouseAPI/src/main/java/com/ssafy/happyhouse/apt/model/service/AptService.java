package com.ssafy.happyhouse.apt.model.service;

import java.util.List;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.AptDeal;

public interface AptService {
	void insertAptList(List<Apt> aptList) throws Exception;

	void insertAptInfoList(List<Apt> aptList) throws Exception;

	void insertAptDetailList(List<Apt> aptList) throws Exception;

	void insertAptDealList(List<AptDeal> aptDealList) throws Exception;

	List<String> selectGugunCodeList() throws Exception;
}
