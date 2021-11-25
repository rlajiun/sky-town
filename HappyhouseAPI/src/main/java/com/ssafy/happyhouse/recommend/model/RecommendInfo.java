package com.ssafy.happyhouse.recommend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecommendInfo {
	private String userId;
	private String userEmail;
	private String itemId;
	private int score;
	
	RecommendInfo(String u, String e, String i, int s){
		this.userId = u;
		this.userEmail = e;
		this.itemId = i;
		this.score = s;
	}
	
	public RecommendInfo(){
		
	}
}
