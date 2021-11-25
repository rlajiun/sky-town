package com.ssafy.happyhouse.recommend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RecommendInfo {
	private String userId;
	private String itemId;
	private int score;
	
	public RecommendInfo(String u, String i, int s) {
		this.userId = u;
		itemId = i;
		score = s;
	}
	public RecommendInfo() {}
}
