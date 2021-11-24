package com.ssafy.happyhouse.email;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.email.model.Mail;
import com.ssafy.happyhouse.email.model.service.MailService;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@AllArgsConstructor
@RequestMapping("/email")
public class EmailController {
	@Autowired
	private final MailService mailService;

//	@GetMapping
//	public String dispMail() {
//		return "mail";
//	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> execMail(HttpServletRequest request) throws URISyntaxException {
		System.out.println("email 서버");
		Mail mailDto = new Mail();
		System.out.println(request.getHeader("useremail"));
		mailDto.setAddress("songyein620@gmail.com");;
		mailDto.setMessage("안녕하세요. SKY TOWN 입니다. 고객님 구독하신 매물에 대한 정보입니다.");
		mailDto.setTitle("OOO 님께 꼭 맞는 매물정보가 도착했어요.");
		mailService.mailSend(mailDto);
		URI redirectUri = new URI("http://localhost:8080/#/recommendhouse");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUri);

		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}

}
