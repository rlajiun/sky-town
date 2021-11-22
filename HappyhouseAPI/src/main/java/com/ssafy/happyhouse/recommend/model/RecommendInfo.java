package com.ssafy.happyhouse.recommend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecommendInfo {
	private String userId;
	private String itemId;
	private int score;
}
