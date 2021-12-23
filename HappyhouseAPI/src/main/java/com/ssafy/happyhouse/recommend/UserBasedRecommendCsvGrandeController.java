package com.ssafy.happyhouse.recommend;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
//import org.postgresql.ds.PGPoolingDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.recommend.model.RecommendInfo;
import com.ssafy.happyhouse.recommend.model.ReommendAptInfo;
import com.ssafy.happyhouse.recommend.model.service.RecommendService;

@RestController
@RequestMapping("/recommend")
public class UserBasedRecommendCsvGrandeController {
	private static Logger log = LoggerFactory.getLogger(UserBasedRecommendCsvGrandeController.class);

	@Autowired
	private RecommendService recomService;

	public static int createCsvFile(String filePath, List<RecommendInfo> list) {
		int resultCount = 0;
		try {
			BufferedWriter csvWriter = new BufferedWriter(new FileWriter(filePath, false));

			for (RecommendInfo ri : list) {
				csvWriter.write(ri.getUserId() + "");
				csvWriter.write(",");
				csvWriter.write(ri.getItemId().substring(1, ri.getItemId().length()) + "");
				csvWriter.write(",");
				csvWriter.write(Integer.toString(ri.getScore()) + ".0");
				csvWriter.newLine();
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return resultCount;
	}

	@GetMapping
	public String recommend(HttpServletRequest request) {
		try {
			List<RecommendInfo> list = recomService.getRecommendInfo();
			createCsvFile("csv/ttttt.csv", list);
			
			//r1. 추천 알고리즘 돌려서 아파트 리스트 뽑아내기
			List<ReommendAptInfo> recommendAptList = recomService.recommendAlgo();
			Gson gson = new Gson();
			return gson.toJson(recommendAptList);

		} catch (TasteException e) {
			e.printStackTrace();
			log.error("##" + e.toString());
		} catch (Exception e) {
			e.printStackTrace();
			log.error("##" + e.toString());
		}

		return "";
	}
}
