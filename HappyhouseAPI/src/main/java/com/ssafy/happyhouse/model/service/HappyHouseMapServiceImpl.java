package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HappyHouseMapServiceImpl implements HappyHouseMapService {

	@Autowired
	private SqlSession sqlSession;
	private Map<String, List<HouseInfoDto>> house = new HashMap<String, List<HouseInfoDto>>();
	private int priceListSize = 0;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getGugunInSido(sido);
	}

	@Override
	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(HouseMapMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong, int start, int cnt, int price) throws Exception {
		List<HouseInfoDto> list;
		
		if (!house.containsKey(dong)) {
			house.put(dong, sqlSession.getMapper(HouseMapMapper.class).getAptInDong(dong));
		}
		
		if (price != 0) {
			List<HouseInfoDto> priceList = new ArrayList<>();
			for (int i = 0; i < house.get(dong).size(); i++) {
				System.out.println("최신가격: " + house.get(dong).get(i).getRecentPrice());
				if (house.get(dong).get(i).getRecentPrice() != null) {
					String[] st = house.get(dong).get(i).getRecentPrice().trim().split(",");
					int p = Integer.parseInt(st[0] + st[1]);// 매물가격이고 기준가격이랑 비교해서 띄울지 말지 결정
					if (p <= price) {
						priceList.add(house.get(dong).get(i));
					}
				}
			}
			list = priceList;
			priceListSize = list.size();
		}else {
			list = house.get(dong);
		}

		if (cnt + start > list.size()) {
			return list.subList(start, list.size());
		}
		return list.subList(start, cnt + start);
	}

//	@Override
//	public List<HouseInfoDto> getAptLowerPrice(String dong, String price) throws Exception {
//		return sqlSession.getMapper(HouseMapMapper.class).getAptLowerPrice(dong, price);
//	}

	@Override
	public int getCountApt(String dong, int price) throws Exception {
		int answer = 0;
		if (price != 0) {
			answer = priceListSize;
			return answer;
		}
		return house.get(dong).size();
	}

}
