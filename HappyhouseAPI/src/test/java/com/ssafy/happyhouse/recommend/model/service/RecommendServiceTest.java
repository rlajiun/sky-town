package com.ssafy.happyhouse.recommend.model.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ssafy.happyhouse.recommend.model.RecommendInfo;
import com.ssafy.happyhouse.recommend.model.mapper.RecommendMapper;


@ExtendWith(MockitoExtension.class)
class RecommendServiceTest {
	
	@InjectMocks
	private RecommendServiceImpl recommendService;
	
	@Mock
	//private RecommendInfo recommendInfo;
	private SqlSession sqlSession;
	
	@BeforeEach
	public void beforeEach() {
		recommendService = new RecommendServiceImpl();
	}
	
	@Test
	final void testGetRecommendInfo() throws SQLException {
		//given
		doReturn(recommendList()).when(sqlSession.getMapper(RecommendMapper.class)).getRecommendInfo();
		
		//when
		List<RecommendInfo> list = recommendService.getRecommendInfo();
		
		//then
		assertThat(list.size()).isEqualTo(5);
		
		//fail("Not yet implemented"); // TODO
	}
	
	private List<RecommendInfo> recommendList(){
		final List<RecommendInfo> recommendList = new ArrayList<>();
		for(int i=0;i<5;i++) {
			recommendList.add(new RecommendInfo("11","22","ddd",3));
		}
		return recommendList;
	}
//
//	@Test
//	final void testSelectAptInfo() {
//		//fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	final void testRecommendAlgo() {
//		//fail("Not yet implemented"); // TODO
//	}

}
