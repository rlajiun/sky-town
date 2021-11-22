package com.ssafy.happyhouse.apt.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.apt.model.Apt;

public interface AptMapper {
	void insertApt(@Param("aptList") List<Apt> aptList) throws SQLException;

	void insertAptInfo(@Param("aptList") List<Apt> aptList) throws SQLException;

	void insertAptDetail(@Param("aptList") List<Apt> aptList) throws SQLException;
	
	int selectSidoAptCnt() throws SQLException;
	
	List<Apt> selectAllApt() throws SQLException;
	
//	List<Apt> selectApt
}
