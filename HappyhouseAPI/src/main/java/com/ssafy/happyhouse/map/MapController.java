package com.ssafy.happyhouse.map;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.apt.model.AptInfoBasic;
import com.ssafy.happyhouse.map.model.Zone;
import com.ssafy.happyhouse.map.model.ZoneChild;
import com.ssafy.happyhouse.map.model.service.MapService;

@RestController
@RequestMapping("/map")
public class MapController {

	@Autowired
	private MapService mapService;

	@GetMapping("/sido")
	public ResponseEntity<List<ZoneChild>> sidoCnt() throws Exception {
		return new ResponseEntity<List<ZoneChild>>(mapService.getSido(), HttpStatus.OK);
	}

	@GetMapping("/gugun")
	public ResponseEntity<Zone> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<Zone>(mapService.getZone(sido, 2), HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<Zone> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<Zone>(mapService.getZone(gugun, 5), HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<AptInfoBasic>> aptList(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<AptInfoBasic>>(mapService.getAptList(dong),
				HttpStatus.OK);
	}

}
