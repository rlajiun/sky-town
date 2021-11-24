package com.ssafy.happyhouse.recommend.model.mapper;

import java.sql.SQLException;

import com.ssafy.happyhouse.recommend.model.RecommendInfo;

public interface AptScoreMapper {
	void insertRecommendScore(RecommendInfo rciDto) throws SQLException;
}
