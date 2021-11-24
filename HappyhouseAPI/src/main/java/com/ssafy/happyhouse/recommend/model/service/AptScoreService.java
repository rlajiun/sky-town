package com.ssafy.happyhouse.recommend.model.service;

import java.sql.SQLException;

import com.ssafy.happyhouse.recommend.model.RecommendInfo;

public interface AptScoreService {

	void insertRecommendScore(RecommendInfo rciDto) throws SQLException;

}
