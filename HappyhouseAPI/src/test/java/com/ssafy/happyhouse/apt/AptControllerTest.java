package com.ssafy.happyhouse.apt;

import static org.junit.jupiter.api.Assertions.fail;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.apt.model.service.AptService;

@WebMvcTest(AptController.class)
public class AptControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AptService aptService;

	@Test
	@DisplayName("아파트 정보 가져오기 테스트")
	void testApt() throws Exception {
		AptInfo aptInfo = new AptInfo();
		aptInfo.setAptAddr("주소입니다아ㅏ아ㅏ");
		aptInfo.setAptCode("1234");
		Apt apt = new Apt();
		apt.setAptInfo(aptInfo);

		// given
		given(aptService.getApt("123")).willReturn(apt);

		mockMvc.perform(get("/apt/" + 1234)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	void testAptList() {
		fail("Not yet implemented"); // TODO
	}

}
