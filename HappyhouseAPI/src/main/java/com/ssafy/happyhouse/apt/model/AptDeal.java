package com.ssafy.happyhouse.apt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AptDeal {
	private String dealCode;
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
