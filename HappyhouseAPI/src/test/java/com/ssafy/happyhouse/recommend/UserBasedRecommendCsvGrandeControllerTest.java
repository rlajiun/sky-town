package com.ssafy.happyhouse.recommend;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ssafy.happyhouse.recommend.model.service.RecommendService;

@ExtendWith(MockitoExtension.class)
class UserBasedRecommendCsvGrandeControllerTest {

	@InjectMocks
	private UserBasedRecommendCsvGrandeController recommController;
	
	@Mock
	private RecommendService rService;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(recommController).build();
	}
	
	@Test
	final void testCreateCsvFile() {
		//테스트 필요 없음
		//fail("Not yet implemented"); // TODO
	}

	@Test
	final void testRecommend() {
		
		//fail("Not yet implemented"); // TODO
	}

}
