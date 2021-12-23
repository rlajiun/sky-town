package com.ssafy.happyhouse.apt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 간소화된 아파트 정보를 가지는 클래스
 * 
 * @author Kim Jiun
 *
 */
@Getter
@Setter
@ToString
public class AptInfoBasic {
	private String aptCode;
	private String aptName;
	private String dongCode;
	private String aptAddr;
	private String aptDongCnt;
	private String aptdaCnt;
	private String welfareFacility;
	private String aptdWtimesub;
	private String areaAvg;
	private String amtAvg;
}
