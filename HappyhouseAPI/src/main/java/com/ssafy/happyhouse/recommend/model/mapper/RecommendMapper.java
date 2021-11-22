package com.ssafy.happyhouse.recommend.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.recommend.model.RecommendInfo;

public interface RecommendMapper {
	public List<RecommendInfo> getRecommendInfo() throws SQLException;
}
