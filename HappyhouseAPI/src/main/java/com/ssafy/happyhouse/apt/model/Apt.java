package com.ssafy.happyhouse.apt.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 아파트의 모든 정보를 가지는 클래스
 * 
 * @author Kim Jiun
 *
 */
@Getter
@Setter
@ToString
public class Apt {
	private AptInfo aptInfo; // 아파트 상세 정보
	private List<AptDeal> aptDealList;
	private AptAvg aptAvg;
	private List<AptAvg> aptAvgForGroup; // 아파트 dealArea 당 평균
}
