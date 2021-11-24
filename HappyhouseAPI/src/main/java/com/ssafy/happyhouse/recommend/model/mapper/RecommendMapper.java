package com.ssafy.happyhouse.recommend.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.recommend.model.RecommendInfo;

public interface RecommendMapper {
	List<RecommendInfo> getRecommendInfo() throws SQLException;
	AptInfo selectAllApt(String aptId) throws SQLException;
}
