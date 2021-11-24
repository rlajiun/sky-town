package com.ssafy.happyhouse.apt.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.apt.model.AptAvg;
import com.ssafy.happyhouse.apt.model.AptDeal;
import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.apt.model.AptInfoBasic;

public interface AptMapper {
	void insertApt(@Param("aptList") List<AptInfo> aptList) throws SQLException;

	void insertAptInfo(@Param("aptList") List<AptInfo> aptList) throws SQLException;

	void insertAptDetail(@Param("aptList") List<AptInfo> aptList) throws SQLException;

	void insertAptDeal(@Param("aptDealList") List<AptDeal> aptDealList) throws SQLException;

	List<String> selectGugunCode() throws SQLException;

	AptAvg selectAptAvg(@Param("aptName") String aptName, @Param("dong") String dong) throws SQLException;

	List<AptAvg> selectAptAvgForGroup(@Param("aptName") String aptName, @Param("dong") String dong) throws SQLException;

	List<AptDeal> selectAptDealList(@Param("aptName") String aptName, @Param("dong") String dong) throws SQLException;

	AptInfo selectApt(String aptCode) throws SQLException;

	List<AptInfoBasic> selectAptBasicList(@Param("parent") String parent, @Param("len") int len) throws SQLException;

}
