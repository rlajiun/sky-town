//package com.ssafy.happyhouse.email;
//
//import java.util.Properties;
//
//import javax.mail.Authenticator;
//import javax.mail.Session;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/email")
//public class EmailController {
//
//	public static void sendMail() {
//		Properties props = new Properties();try{ 
//		//SSL 사용일때 
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory") 
//		props.put("mail.smtp.socketFactory.port", "587"); 
//		props.put("mail.smtp.ssl.enable", "true"); 
//		//TLS 사용일때 
//		props.put("mail.smtp.port", "465"); 
//		props.put("mail.smtp.starttls.enable", "true"); 
//		props.put("mail.smtp.host", "smtp.gmail.com"); 
//		props.put("mail.smtp.socketFactory.fallback", "false"); 
//		props.put("mail.smtp.debug", "true"); 
//		props.put("mail.smtp.auth", "true"); 
//		//인증정보 
//		Authenticator auth = new SMTPAuthenticator("계정 아이디", "계정 비밀번호"); 
//		Session mailSession = Session.getDefaultInstance(props, auth); 
//		MimeMessage mailMessage = mailImpl.createMimeMessage(); 
//		MimeMessageHelper message = new MimeMessageHelper(mailMessage, isAttach, "UTF-8"); 
//			
//			message.setTo("받는사람 메일주소");
//			message.setFrom("보내는 사람 메일주소"); 
//			message.setSubject("메일제목"); 
//			message.setText("메일 본문", true);
//			mailImpl.setJavaMailProperties(props); 
//			mailImpl.setSession(mailSession); 
//			//메일발송 
//			mailImpl.send(mailMessage); 
//			}catch(
//		MessagingException e)
//		{
//			e.printStackTrace();
//		}catch(
//		AddressException e)
//		{
//			e.printStackTrace();
//		}
//	}
//}
