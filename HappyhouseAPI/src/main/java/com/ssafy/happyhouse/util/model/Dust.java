package com.ssafy.happyhouse.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Dust {
	private String MSRSTE_NM;
	private String PM10;
	private String PM25;
	private String O3;
	private String NO2;
	private String IDEX_NM;
}
