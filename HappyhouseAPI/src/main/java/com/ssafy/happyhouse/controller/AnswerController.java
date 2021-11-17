package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.Answer;
import com.ssafy.happyhouse.model.service.AnswerService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/answer")
public class AnswerController {
	private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AnswerService answerService;

	@ApiOperation(value = "글번호에 해당하는 답글의 정보를 반환한다.", response = Answer.class)
	@GetMapping("{pNo}")
	public ResponseEntity<Answer> detailAnswer(@PathVariable int pNo) {
		logger.debug("detailAnswer - 호출");
		return new ResponseEntity<Answer>(answerService.detailAnswer(pNo), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 답글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeAnswer(@RequestBody Answer answer) {
		logger.debug("writeAnswer - 호출");
		if (answerService.writeAnswer(answer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 답글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateAnswer(@RequestBody Answer answer) {
		logger.debug("updateAnswer - 호출");
		logger.debug("" + answer);

		if (answerService.updateAnswer(answer)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 답글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{no}")
	public ResponseEntity<String> deleteAnswer(@PathVariable int no) {
		logger.debug("deleteAnswer - 호출");
		if (answerService.deleteAnswer(no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
