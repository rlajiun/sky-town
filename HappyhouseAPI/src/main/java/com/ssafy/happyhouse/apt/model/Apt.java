package com.ssafy.happyhouse.apt.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Apt {
	private AptInfo aptInfo;
	private List<AptDeal> aptDealList;
	private AptAvg aptAvg;
	private List<AptAvg> aptAvgForGroup;
}
