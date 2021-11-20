package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.Category;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCode;

public interface MapService {

	List<SidoGugunCode> getSido() throws Exception;

	List<SidoGugunCode> getGugunInSido(String sido) throws Exception;

	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;

	List<HouseInfoDto> getAptInDong(String dong, int start, int cnt, int price) throws Exception;

//	List<HouseInfoDto> getAptLowerPrice(String dong, String price) throws Exception;
	int getCountApt(String dong, int price) throws Exception;

	List<Category> getCategory() throws Exception;
}
