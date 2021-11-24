package com.ssafy.happyhouse.recommend.model;

import com.ssafy.happyhouse.apt.model.AptInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReommendAptInfo {
	private String itemId;
	private float score;
	private AptInfo apt;
	
	public ReommendAptInfo(AptInfo apt, String itemId, float score) {
		this.apt = apt;
		this.itemId = itemId;
		this.score = score;
	}
	public ReommendAptInfo() {}
	
}
