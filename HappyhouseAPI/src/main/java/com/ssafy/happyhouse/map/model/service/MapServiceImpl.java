package com.ssafy.happyhouse.map.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.map.model.mapper.MapMapper;
import com.ssafy.happyhouse.util.model.Category;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Map<String, String>> getSido() throws Exception {
		return sqlSession.getMapper(MapMapper.class).selectSido();
	}
	
	@Override
	public List<Map<String, String>> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(MapMapper.class).selectGugunInSido(sido);
	}

	@Override
	public List<Map<String, String>> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(MapMapper.class).selectDongInGugun(gugun);
	}

	@Override
	public List<Apt> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(MapMapper.class).selectAptInDong(dong);
	}
	@Override
	public List<Apt> getAllApt() throws Exception {
		return sqlSession.getMapper(MapMapper.class).selectAllApt();
	}

	@Override
	public List<Category> getCategory() throws Exception {
		return sqlSession.getMapper(MapMapper.class).getCategory();
	}

}
