package com.ssafy.happyhouse.email.model.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.email.model.Mail;
import com.ssafy.happyhouse.email.model.mapper.AptDetailMapper;


@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "songyein620@naver.com";
    
    @Autowired
	private SqlSession sqlSession;
    
    @Override
    public void mailSend(Mail mail,Map<String,String> aptDetailList) {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setTo(mail.getAddress());
        message.setFrom(MailServiceImpl.FROM_ADDRESS);
        message.setSubject(mail.getTitle());
        message.setText(mail.getMessage());
        
//        StringBuilder sb = new StringBuilder();
//    	sb.append(aptDetailList.toString());
//    	String html = sb.toString();
//    	message.setText(html);
        
        System.out.println(message);
        mailSender.send(message);
      
    }
    
    @Override
	public void sendMailWithFiles(Mail mail) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		
	}
    
    //time out 나네..?
//    @Override
//    public void sendMailWithFiles(Mail mail) throws MessagingException, IOException {
//        MailHandler mailHandler = new MailHandler(mailSender);
//
//        mailHandler.setTo(mail.getAddress());
//        mailHandler.setFrom(MailServiceImpl.FROM_ADDRESS);
//        mailHandler.setSubject(mail.getTitle());
//        
//        String htmlContent = "<p>" + mail.getMessage() + "<p> <img src='cid:google-logo'>";
//        mailHandler.setText(htmlContent, true);
//        mailHandler.setAttach("test.txt", "static/test.txt");
//        mailHandler.setInline("apt", "static/apt.png");
//        System.out.println("mailHandler"+mailHandler);
//        mailHandler.send();
//    }
    
    @Override
    public void sendMailAttachment(Mail mail, Map<String,String> aptDetailList) throws MessagingException, IOException{
    	MimeMessage message = mailSender.createMimeMessage();
    	MimeMessageHelper helper = new MimeMessageHelper(message, true);
    	helper.setTo(mail.getAddress());
    	helper.setFrom(MailServiceImpl.FROM_ADDRESS);
    	helper.setSubject(mail.getTitle());
    	helper.setText(mail.getMessage());
    	
//    	StringBuilder sb = new StringBuilder();
//    	sb.append(aptDetailList.toString());
//    	String html = sb.toString();
//    	helper.setText(html);
    	
    	//aptDetailLIst 정보릂 파일 형태로 만들기
    	String filePath = "csv/aptDetails.csv";
    	BufferedWriter fw = new BufferedWriter(new FileWriter(filePath, false));
    	fw.write(aptDetailList.toString());
    	fw.flush();
    	fw.close();
    	FileSystemResource file = new FileSystemResource(new File(filePath));
    	helper.addAttachment("aptDetails.csv", file);
    	
    	//사진첨부
    	
    	mailSender.send(message);
    	
    }

	@Override
	public Map<String, String> getAptDetail(String aptCode) throws MessagingException, IOException, SQLException {
		return sqlSession.getMapper(AptDetailMapper.class).getAptDetail(aptCode);
	}
}
