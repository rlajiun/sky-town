package com.ssafy.happyhouse.map.model.service;

import java.util.List;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.map.model.SidoGugunCode;
import com.ssafy.happyhouse.util.model.Category;

public interface MapService {

	List<SidoGugunCode> getSido() throws Exception;

	List<SidoGugunCode> getGugunInSido(String sido) throws Exception;

	List<Apt> getDongInGugun(String gugun) throws Exception;

	List<Apt> getAptInDong(String dong, int start, int cnt) throws Exception;

	int getCountApt(String dong, int price) throws Exception;

	List<Category> getCategory() throws Exception;
}
