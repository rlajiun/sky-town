package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.Apt;
import com.ssafy.happyhouse.model.Category;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;

public interface HouseMapMapper {

	List<SidoGugunCodeDto> getSido() throws SQLException;

	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;

	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;

	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;

	List<Category> getCategory() throws SQLException;

	void insertApt(@Param("aptList") List<Apt> aptList) throws SQLException;

	void insertAptInfo(@Param("aptList") List<Apt> aptList) throws SQLException;

	void insertAptDetail(@Param("aptList") List<Apt> aptList) throws SQLException;

//	int getCountApt(String dong) throws SQLException;
}
