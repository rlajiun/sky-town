package com.ssafy.happyhouse.map.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.util.model.Category;

public interface MapService {

//	List<SidoGugunCode> getSido() throws Exception;

//	List<SidoGugunCode> getGugunInSido(String sido) throws Exception;
	
	List<Map<String, String>> getGugunInSido(String sido) throws Exception;

	List<Map<String, String>> getDongInGugun(String gugun) throws Exception;

	List<Apt> getAptInDong(String dong) throws Exception;

	List<Apt> getAllApt() throws Exception;

//	int getCountApt(String dong, int price) throws Exception;

	List<Map<String, String>> getSido() throws Exception;

	List<Category> getCategory() throws Exception;
}
