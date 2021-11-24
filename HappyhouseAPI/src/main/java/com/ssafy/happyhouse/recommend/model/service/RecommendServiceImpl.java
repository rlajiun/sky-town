package com.ssafy.happyhouse.recommend.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.recommend.model.RecommendInfo;
import com.ssafy.happyhouse.recommend.model.mapper.RecommendMapper;

@Service
public class RecommendServiceImpl implements RecommendService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<RecommendInfo> getRecommendInfo() throws SQLException {
		return sqlSession.getMapper(RecommendMapper.class).getRecommendInfo();
	}

	@Override
	public Apt selectAptInfo(String aptId) throws SQLException {
		return sqlSession.getMapper(RecommendMapper.class).selectAllApt(aptId);
	}

}
