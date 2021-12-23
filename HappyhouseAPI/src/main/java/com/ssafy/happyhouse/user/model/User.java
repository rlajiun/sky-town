package com.ssafy.happyhouse.user.model;

import org.springframework.boot.context.properties.ConstructorBinding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String userName;
	private String userId;
	private String userPwd;
	private String email;
	private String joinDate;
}
