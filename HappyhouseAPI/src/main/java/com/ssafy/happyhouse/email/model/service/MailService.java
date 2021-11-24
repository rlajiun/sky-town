package com.ssafy.happyhouse.email.model.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.mail.MessagingException;

import com.ssafy.happyhouse.email.model.Mail;

public interface MailService {
	void mailSend(Mail maildto,Map<String,String> aptDetailList) throws MessagingException, IOException;
	void sendMailWithFiles(Mail mail) throws MessagingException, IOException;
	void sendMailAttachment(Mail mail, Map<String,String> aptDetailList) throws MessagingException, IOException;
	Map<String, String> getAptDetail(String aptCode) throws MessagingException, IOException, SQLException;
}
