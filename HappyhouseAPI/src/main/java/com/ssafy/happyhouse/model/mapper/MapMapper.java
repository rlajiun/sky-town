package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.Category;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCode;

public interface MapMapper {

	List<SidoGugunCode> getSido() throws SQLException;

	List<SidoGugunCode> getGugunInSido(String sido) throws SQLException;

	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;

	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;

	List<Category> getCategory() throws SQLException;

}
