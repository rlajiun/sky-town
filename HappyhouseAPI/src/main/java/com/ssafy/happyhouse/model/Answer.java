package com.ssafy.happyhouse.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Answer {
	private int no;
	private String content;
	private String dateTime;
	private String parentNo;
}
