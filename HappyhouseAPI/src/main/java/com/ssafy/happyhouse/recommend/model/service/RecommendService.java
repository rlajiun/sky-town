package com.ssafy.happyhouse.recommend.model.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;

import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.recommend.model.RecommendInfo;
import com.ssafy.happyhouse.recommend.model.ReommendAptInfo;


public interface RecommendService {
	public List<RecommendInfo> getRecommendInfo() throws SQLException;
	public AptInfo selectAptInfo(String aptId) throws SQLException;
	public ArrayList<ReommendAptInfo> recommendAlgo() throws IOException, TasteException, SQLException;
	
}
