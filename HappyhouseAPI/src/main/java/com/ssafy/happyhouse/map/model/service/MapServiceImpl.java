package com.ssafy.happyhouse.map.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.apt.model.AptInfoBasic;
import com.ssafy.happyhouse.map.model.Zone;
import com.ssafy.happyhouse.map.model.ZoneChild;
import com.ssafy.happyhouse.map.model.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ZoneChild> getSido() throws Exception {
		return sqlSession.getMapper(MapMapper.class).selectSido();
	}

	@Override
	public List<AptInfo> getAptInDong(String dong) throws Exception {
		return sqlSession.getMapper(MapMapper.class).selectAptInDong(dong);
	}

	@Override
	public Zone getZone(String parent, int len) throws Exception {
		Zone zone = sqlSession.getMapper(MapMapper.class).selectCntForParent(parent, len);
		switch (len) {
		case 2:
			zone.setNextZoneList(sqlSession.getMapper(MapMapper.class).selectGugunInSido(parent));
			break;
		case 5:
			zone.setNextZoneList(sqlSession.getMapper(MapMapper.class).selectDongInGugun(parent));
			break;
		}
		zone.setAptBasicList(sqlSession.getMapper(MapMapper.class).selectAptBasicList(parent, len));
		System.out.println(zone);
		return zone;
	}

	@Override
	public List<AptInfoBasic> getAptList(String parent) throws SQLException {
		return sqlSession.getMapper(MapMapper.class).selectAptBasicList(parent, parent.length());
	}

}
