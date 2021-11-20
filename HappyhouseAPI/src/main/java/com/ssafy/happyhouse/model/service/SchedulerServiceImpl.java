package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.Apt;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class SchedulerServiceImpl implements SchedulerService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertAptList(List<Apt> aptList) throws Exception {
		sqlSession.getMapper(HouseMapMapper.class).insertApt(aptList);
	}

	@Override
	public void insertAptInfoList(List<Apt> aptList) throws Exception {
		sqlSession.getMapper(HouseMapMapper.class).insertAptInfo(aptList);
	}

	@Override
	public void insertAptDetailList(List<Apt> aptList) throws Exception {
		sqlSession.getMapper(HouseMapMapper.class).insertAptDetail(aptList);
	}
}
