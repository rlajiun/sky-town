package com.ssafy.happyhouse.util.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class News {
	
	private String url;
	private String title;
	private String content;

}
