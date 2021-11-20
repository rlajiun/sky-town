package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Apt;

public interface SchedulerService {
	void insertAptList(List<Apt> aptList) throws Exception;

	void insertAptInfoList(List<Apt> aptList) throws Exception;

	void insertAptDetailList(List<Apt> aptList) throws Exception;
}
