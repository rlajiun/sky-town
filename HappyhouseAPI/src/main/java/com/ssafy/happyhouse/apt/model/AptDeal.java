package com.ssafy.happyhouse.apt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 아파트 거래 내역과 관련된 클래스
 * 
 * @author Kim Jiun
 *
 */
@Getter
@Setter
@ToString
public class AptDeal {
	private String dealAmount;
	private int buildYear;
	private int dealYear;
	private String dongCode;
	private String aptName;
	private int dealMonth;
	private int dealDay;
	private String dealArea;
	private String jibun;
	private int floor;
	private String reqGbn;
	private String redealerLawdnm;
	private String cancelDealType;
	private String cancelDealDay;
}
