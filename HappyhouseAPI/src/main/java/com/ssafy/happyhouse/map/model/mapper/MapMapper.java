package com.ssafy.happyhouse.map.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.map.model.SidoGugunCode;
import com.ssafy.happyhouse.util.model.Category;

public interface MapMapper {

	List<SidoGugunCode> getSido() throws SQLException;

	List<SidoGugunCode> getGugunInSido(String sido) throws SQLException;

	List<Apt> getDongInGugun(String gugun) throws SQLException;

	List<Apt> getAptInDong(String dong) throws SQLException;

	List<Category> getCategory() throws SQLException;

}
