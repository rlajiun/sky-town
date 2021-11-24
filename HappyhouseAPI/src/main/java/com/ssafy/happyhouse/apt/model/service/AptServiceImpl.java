package com.ssafy.happyhouse.apt.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.AptDeal;
import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.apt.model.AptInfoBasic;
import com.ssafy.happyhouse.apt.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertAptList(List<AptInfo> aptList) throws Exception {
		sqlSession.getMapper(AptMapper.class).insertApt(aptList);
	}

	@Override
	public void insertAptInfoList(List<AptInfo> aptList) throws Exception {
		sqlSession.getMapper(AptMapper.class).insertAptInfo(aptList);
	}

	@Override
	public void insertAptDetailList(List<AptInfo> aptList) throws Exception {
		sqlSession.getMapper(AptMapper.class).insertAptDetail(aptList);
	}

	@Override
	public void insertAptDealList(List<AptDeal> aptDealList) throws Exception {
		sqlSession.getMapper(AptMapper.class).insertAptDeal(aptDealList);
	}

	@Override
	public List<String> getGugunCodeList() throws Exception {
		return sqlSession.getMapper(AptMapper.class).selectGugunCode();
	}

	@Override
	public Apt getApt(String aptCode) throws SQLException {
		Apt apt = new Apt();
		apt.setAptInfo(sqlSession.getMapper(AptMapper.class).selectApt(aptCode));
		String aptName = apt.getAptInfo().getAptName();
		String dong = apt.getAptInfo().getDongCode();
		System.out.println("aptName: "+aptName);
		System.out.println("dong: "+dong);
		apt.setAptDealList(sqlSession.getMapper(AptMapper.class).selectAptDealList(aptName, dong));
		System.out.println(sqlSession.getMapper(AptMapper.class).selectAptDealList(aptName, dong));
		apt.setAptAvg(sqlSession.getMapper(AptMapper.class).selectAptAvg(aptName, dong));
		System.out.println(sqlSession.getMapper(AptMapper.class).selectAptAvg(aptName, dong));
		apt.setAptAvgForGroup(sqlSession.getMapper(AptMapper.class).selectAptAvgForGroup(aptName, dong));
		System.out.println(sqlSession.getMapper(AptMapper.class).selectAptAvgForGroup(aptName, dong));
		return apt;
	}

	@Override
	public List<AptInfoBasic> getAptList(String parent) throws SQLException {
		return sqlSession.getMapper(AptMapper.class).selectAptBasicList(parent, parent.length());
	}
}
