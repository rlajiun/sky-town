package com.ssafy.happyhouse.email;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@GetMapping
	public ResponseEntity<Map<String, Object>> execMail(Mail email) throws URISyntaxException, MessagingException, IOException, SQLException {
		System.out.println("email 서버");
		Mail mailDto = new Mail();
		System.out.println("email: "+email.getAddress());
		String aptCode = "A"+email.getAptname();
		System.out.println("aptnum: "+ aptCode);
		//aptCode 로 아파트 세부정보 얻어오기
		Map<String, String> aptDetailList = mailService.getAptDetail(aptCode);
		System.out.println("아파트 세부 정보들"+aptDetailList);
		
		mailDto.setAddress(email.getAddress());
		//mailDto.setMessage("안녕하세요. SKY TOWN 입니다. \n 고객님 께서 구독하신 "+ aptDetailList.get("aptAddr") +"매물에 대한 정보입니다.\n\n\n");
		mailDto.setMessage("안녕하세요. SKY TOWN 입니다. \\n 고객님 께서 구독하신 "+ aptDetailList.get("aptAddr") + "매물에 대한 정보입니다."
				+ "본 메일은 발신전용 메일이며 회신처리되지 않습니다.\n" + 
				"문의사항은 SKY TOWN 고객만족센터를 이용해 주십시요.\n" + 
				"사이트 이용 시에 필요한 정보성 메일은 수신동의 여부와 관계없이 발송되오니 양해 바랍니다.\n" + 
				"\n" + 
				"(주)HAPPYHOUSE\n" + 
				"대표자명 : 송예인, 김지언\n" + 
				"주소 : 서울시 성동구 뚝섬로1길 63 영창디지탈타워 6층, 12층\n" + 
				"사업자등록번호 : 550-86-022867\n" + 
				"통신판매업신고 : 2019-서울성동-01148."
				+ "<img src="
				+ ""
				+ "");
		
		mailDto.setTitle("송진우 님께 꼭 맞는 매물정보가 도착했어요.");
		//1. 간단한 메일 보내기
		//mailService.mailSend(mailDto,aptDetailList);
		
		//2. 파일첨부 가능한 메일 보내기
		mailService.sendMailAttachment(mailDto,aptDetailList);
		URI redirectUri = new URI("http://localhost:8080/#/recommendhouse");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUri);
		return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
	}

}
