package com.ssafy.happyhouse.apt.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.mapper.AptMapper;

@Qualifier
@Service
public class AptServiceImpl implements AptService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertAptList(List<Apt> aptList) throws Exception {
		sqlSession.getMapper(AptMapper.class).insertApt(aptList);
	}

	@Override
	public void insertAptInfoList(List<Apt> aptList) throws Exception {
		sqlSession.getMapper(AptMapper.class).insertAptInfo(aptList);
	}

	@Override
	public void insertAptDetailList(List<Apt> aptList) throws Exception {
		sqlSession.getMapper(AptMapper.class).insertAptDetail(aptList);
	}
}
