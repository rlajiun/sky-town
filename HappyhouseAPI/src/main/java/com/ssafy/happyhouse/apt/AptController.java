package com.ssafy.happyhouse.apt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.apt.model.AptInfoBasic;
import com.ssafy.happyhouse.apt.model.service.AptService;

@RestController
@RequestMapping("/apt")
public class AptController {
	@Autowired
	private AptService aptService;

	@GetMapping("/{aptCode}")
	public ResponseEntity<Apt> apt(@PathVariable("aptCode") String aptCode) throws Exception {
		System.out.println(aptService.getApt(aptCode));
		return new ResponseEntity<Apt>(aptService.getApt(aptCode), HttpStatus.OK);
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<List<AptInfoBasic>> aptList(@RequestParam("parent") String parent) throws Exception {
		return new ResponseEntity<List<AptInfoBasic>>(aptService.getAptList(parent),
				HttpStatus.OK);
	}
}
