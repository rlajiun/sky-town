package com.ssafy.happyhouse.email.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.email.model.Mail;

@Service
public interface MailService {
	void mailSend(Mail maildto);
}
