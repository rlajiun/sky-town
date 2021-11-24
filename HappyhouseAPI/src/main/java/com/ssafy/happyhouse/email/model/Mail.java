package com.ssafy.happyhouse.email.model;

import javax.mail.Message;

import org.springframework.messaging.handler.annotation.MessageMapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@MessageMapping
@NoArgsConstructor
public class Mail {
	private String address;
    private String title;
    private String message;
    private String aptname;
}
