package com.ssafy.happyhouse.qna.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Answer {
	private int no;
	private String content;
	private String dateTime;
	private int p_no;
}
