package com.ssafy.happyhouse.map.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.map.model.SidoGugunCode;
import com.ssafy.happyhouse.util.model.Category;
import com.ssafy.happyhouse.util.model.HouseInfoDto;

public interface MapMapper {

	List<SidoGugunCode> getSido() throws SQLException;

	List<SidoGugunCode> getGugunInSido(String sido) throws SQLException;

	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;

	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;

	List<Category> getCategory() throws SQLException;

}
