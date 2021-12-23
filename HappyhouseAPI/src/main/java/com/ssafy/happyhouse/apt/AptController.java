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

/**
 * 
 * @author Kim Jiun
 * 
 */
@RestController
@RequestMapping("/apt")
public class AptController {
	@Autowired
	private AptService aptService;

	/**
	 * 아파트 코드로 해당 아파트에 관련된 모든 정보를 검색한다.
	 * 
	 * @param aptCode String 검색하는 아파트 코드
	 * @return 아파트 정보 - 상세 정보, 거래 정보 리스트, 가격 정보, 아파트 dealArea 당 평균 가격 정보
	 * @throws Exception
	 */
	@GetMapping("/{aptCode}")
	public ResponseEntity<Apt> retrieveApt(@PathVariable("aptCode") String aptCode) throws Exception {
		return new ResponseEntity<Apt>(aptService.getApt(aptCode), HttpStatus.OK);
	}

	/**
	 * 상위 그룹(시도, 구군, 읍면동)의 정보로 아파트 목록을 조회한다.
	 * 
	 * @param parent String 상위 그룹의 코드
	 * @return 아파트 기본 정보
	 * @throws Exception
	 */

	@GetMapping("/list")
	public ResponseEntity<List<AptInfoBasic>> retrieveAptList(@RequestParam("parent") String parent) throws Exception {
		return new ResponseEntity<List<AptInfoBasic>>(aptService.getAptList(parent), HttpStatus.OK);
	}
}
