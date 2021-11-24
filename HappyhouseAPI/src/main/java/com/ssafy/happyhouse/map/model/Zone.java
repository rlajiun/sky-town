package com.ssafy.happyhouse.map.model;

import java.util.List;

import com.ssafy.happyhouse.apt.model.AptInfoBasic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Zone {
	private String totalCnt; // 총 단지 수 
	private String aptCnt; // 총 세대 수
	private List<ZoneChild> nextZoneList;
	private List<AptInfoBasic> aptBasicList;
}
