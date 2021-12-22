package com.ssafy.happyhouse.recommend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecommendInfo {
	private String userId;
	private String userEmail;
	private String itemId;
	private int score;
}
