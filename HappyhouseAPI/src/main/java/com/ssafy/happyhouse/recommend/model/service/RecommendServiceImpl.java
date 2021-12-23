package com.ssafy.happyhouse.recommend.model.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.apt.model.AptInfo;
import com.ssafy.happyhouse.recommend.UserBasedRecommendCsvGrandeController;
import com.ssafy.happyhouse.recommend.model.RecommendInfo;
import com.ssafy.happyhouse.recommend.model.ReommendAptInfo;
import com.ssafy.happyhouse.recommend.model.mapper.RecommendMapper;

@Service
public class RecommendServiceImpl implements RecommendService {
	private static Logger log = LoggerFactory.getLogger(UserBasedRecommendCsvGrandeController.class);

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<RecommendInfo> getRecommendInfo() throws SQLException {
		return sqlSession.getMapper(RecommendMapper.class).getRecommendInfo();
	}

	@Override
	public AptInfo selectAptInfo(String aptId) throws SQLException {
		return sqlSession.getMapper(RecommendMapper.class).selectAllApt(aptId);
	}

	@Override
	public ArrayList<ReommendAptInfo> recommendAlgo() throws IOException, TasteException, SQLException{
		ArrayList<ReommendAptInfo> recommendAptList = new ArrayList<>();

		DataModel model = new FileDataModel(new File("csv/ttttt.csv")); // 데이터 파일 읽기 -> ID,아이템ID,추천점수 순
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model); // 읽은 데이터로 유저 유사도 계산 //
																				// https://en.wikipedia.org/wiki/Pearson_correlation_coefficient
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model); // 유저 유사도 계산된걸 바탕으로
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		List<RecommendedItem> recommendations = recommender.recommend(1, 10); // 추천 조회 user_id, 갯수

		ArrayList<Long> aptIdLists = new ArrayList<>();
		for (RecommendedItem recommendation : recommendations) {
			System.out.println("id: " + recommendation.getItemID() + "   value: " + recommendation.getValue());
			log.debug(
					"############## " + "id: " + recommendation.getItemID() + "   value: " + recommendation.getValue());
			aptIdLists.add(recommendation.getItemID());
		}

		// 여기서 item 에 해당하는 상세 정보 가지고 와서 json 다시 만들기

		for (int i = 0; i < aptIdLists.size(); i++) {
			AptInfo aptInfo = selectAptInfo("A" + aptIdLists.get(i).toString());
			ReommendAptInfo rInfo = new ReommendAptInfo();
			rInfo.setApt(aptInfo);
			rInfo.setItemId(Long.toString(recommendations.get(i).getItemID()));
			rInfo.setScore(recommendations.get(i).getValue());
			recommendAptList.add(rInfo);
		}

		return recommendAptList;
	}

}
