package com.ssafy.happyhouse.map.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.apt.model.AptInfoBasic;
import com.ssafy.happyhouse.map.model.Zone;
import com.ssafy.happyhouse.map.model.ZoneChild;

public interface MapMapper {

	List<ZoneChild> selectSido() throws SQLException;

	List<ZoneChild> selectGugunInSido(String sido) throws SQLException;

	List<ZoneChild> selectDongInGugun(String gugun) throws SQLException;

	List<AptInfo> selectAptInDong(String dong) throws SQLException;

	Zone selectCntForParent(@Param("parent") String parent, @Param("len") int len) throws SQLException;

	List<AptInfoBasic> selectAptBasicList(@Param("parent") String parent, @Param("len") int len) throws SQLException;
}
