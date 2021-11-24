package com.ssafy.happyhouse.map;

import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.apt.model.Apt;
import com.ssafy.happyhouse.map.model.service.MapService;
import com.ssafy.happyhouse.util.model.Category;

@RestController
@RequestMapping("/map")
public class MapController {

	@Autowired
	private MapService mapService;
	
	@GetMapping("/gugun")
	public ResponseEntity<JSONArray> gugun(@RequestParam("sido") String sido) throws Exception {
		JSONArray array = new JSONArray();
		array.addAll(mapService.getGugunInSido(sido));
		return new ResponseEntity<JSONArray>(array, HttpStatus.OK);
	}

	@GetMapping("/dong")
	public ResponseEntity<JSONArray> dong(@RequestParam("gugun") String gugun) throws Exception {
		JSONArray array = new JSONArray();
		array.addAll(mapService.getDongInGugun(gugun));
		return new ResponseEntity<JSONArray>(array, HttpStatus.OK);
	}

	@GetMapping("/allApt")
	public ResponseEntity<List<Apt>> apt() throws Exception {
//		System.out.println(mapService.getAllApt().size());
		return new ResponseEntity<List<Apt>>(mapService.getAllApt(),
				HttpStatus.OK);
	}
	
	@GetMapping("/apt")
	public ResponseEntity<List<Apt>> apt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<List<Apt>>(mapService.getAptInDong(dong),
				HttpStatus.OK);
	}

//	@GetMapping("/page")
//	public int page(@RequestParam("dong") String dong,
//			@RequestParam(value = "price", required = false, defaultValue = "0") int price) throws Exception {
//		return mapService.getCountApt(dong, price);
//	}
	
	@GetMapping("/sido")
	public ResponseEntity<JSONArray> sidoCnt() throws Exception{
		JSONArray array = new JSONArray();
		array.addAll(mapService.getSido());
		return new ResponseEntity<JSONArray>(array, HttpStatus.OK);
	}
	
	@GetMapping("/category")
	public ResponseEntity<List<Category>> dong() throws Exception {
		return new ResponseEntity<List<Category>>(mapService.getCategory(), HttpStatus.OK);
	}
	
	
}
