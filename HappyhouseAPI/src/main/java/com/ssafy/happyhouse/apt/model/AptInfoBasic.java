package com.ssafy.happyhouse.apt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
	private AptAvg aptAvg;
}
