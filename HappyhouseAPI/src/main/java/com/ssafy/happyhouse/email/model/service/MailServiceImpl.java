package com.ssafy.happyhouse.email.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.email.model.Mail;

import lombok.AllArgsConstructor;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "songyein620@naver.com";
    
    @Override
    public void mailSend(Mail mailDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setFrom(MailServiceImpl.FROM_ADDRESS);
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        System.out.println(message);
        mailSender.send(message);
    }
}
