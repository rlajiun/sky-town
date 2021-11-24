package com.ssafy.happyhouse.recommend.model.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.recommend.model.RecommendInfo;
import com.ssafy.happyhouse.recommend.model.mapper.AptScoreMapper;

@Service
public class AptScoreServiceImpl implements AptScoreService{
	
	@Autowired
	private AptScoreMapper aptscoreMapper;
	
	@Override
	public void insertRecommendScore(RecommendInfo rciDto) throws SQLException {
		aptscoreMapper.insertRecommendScore(rciDto);
	}

}
