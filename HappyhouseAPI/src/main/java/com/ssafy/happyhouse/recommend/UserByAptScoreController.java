package com.ssafy.happyhouse.recommend;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.recommend.model.RecommendInfo;
import com.ssafy.happyhouse.recommend.model.service.AptScoreService;
import com.ssafy.happyhouse.user.model.service.UserServiceImpl;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@AllArgsConstructor
@RequestMapping("/aptscore")
public class UserByAptScoreController {
	@Autowired
	private final AptScoreService rservice;
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	public void AptScore(RecommendInfo rinfo) throws Exception {
		RecommendInfo rciDto = new RecommendInfo();
		rciDto.setUserEmail(rinfo.getUserEmail());
		//userId 는 userEmail 값으로 가져오기
		String userId = userService.getMemberId(rinfo.getUserEmail());
		rciDto.setUserId(userId);
		rciDto.setItemId(rinfo.getItemId());
		rciDto.setScore(rinfo.getScore());
		System.out.println("rinfo.toString() "+rinfo.toString());
		rservice.insertRecommendScore(rciDto);
	}
}
