package com.ssafy.happyhouse.map.model.service;

import java.util.List;

import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.map.model.Zone;
import com.ssafy.happyhouse.map.model.ZoneChild;

public interface MapService {

	List<ZoneChild> getSido() throws Exception;

	Zone getZone(String parent, int len) throws Exception;

	List<AptInfo> getAptInDong(String dong) throws Exception;
	
}
