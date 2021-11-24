package com.ssafy.happyhouse.user.model;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConstructorBinding
public class User {
	private String userName;
	private String userId;
	private String userPwd;
	private String email;
	private String joinDate;
}
