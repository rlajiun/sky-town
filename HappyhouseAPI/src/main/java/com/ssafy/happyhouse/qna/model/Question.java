package com.ssafy.happyhouse.qna.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Question {
	private int no;
	private String title;
	private String writer;
	private String content;
	private String state;
	private String dateTime;
	private Answer answer;
}
