package com.ssafy.happyhouse.map.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.AptDeal;
import com.ssafy.happyhouse.util.model.Category;

public interface MapMapper {

	List<Map<String, String>> selectSido() throws SQLException;
	
	List<Map<String, String>> selectGugunInSido(String sido) throws SQLException;

	List<Map<String, String>> selectDongInGugun(String gugun) throws SQLException;

	List<Apt> selectAptInDong(String dong) throws SQLException;
	
//	List<AptDeal> selectDealInApt(String )
	
	List<Apt> selectAllApt() throws SQLException;

	List<Category> getCategory() throws SQLException;
	

}
