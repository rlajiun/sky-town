package com.ssafy.happyhouse.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Question {
	private int no;
	private String writer;
	private String title;
	private String content;
	private String dateTime;
	private String state;
	private Answer answer;
}
