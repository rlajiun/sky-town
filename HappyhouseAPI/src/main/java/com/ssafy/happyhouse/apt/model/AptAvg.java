package com.ssafy.happyhouse.apt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 아파트의 평당 시세, 전체 시세, 최소 거래 값, 최대 거래 값 정보를 가지는 클래스
 * 
 * @author Kim Jiun
 *
 */
@Getter
@Setter
@ToString
public class AptAvg {
	private String areaAvg; // 평당 시세
	private String amtAvg; // 전체 시세
	private String min;
	private String max;
}
