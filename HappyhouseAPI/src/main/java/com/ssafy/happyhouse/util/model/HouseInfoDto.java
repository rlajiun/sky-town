package com.ssafy.happyhouse.util.model;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HouseInfoDto {

	private String aptCode;
	private String aptName;
	private String dongCode;
	private String dongName;
	private String sidoName;
	private String gugunName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String recentPrice;

	

}
