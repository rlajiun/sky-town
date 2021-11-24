package com.ssafy.happyhouse.apt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AptAvg {
	private String areaAvg; // 평당 시세
	private String amtAvg; // 전체 시세
	private String min;
	private String max;
}
