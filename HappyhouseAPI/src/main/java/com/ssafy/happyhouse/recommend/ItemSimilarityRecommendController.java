//package com.ssafy.happyhouse.recommend;
//import java.util.List;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.mahout.cf.taste.common.TasteException;
//import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
//import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
//import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
//import org.apache.mahout.cf.taste.recommender.RecommendedItem;
//import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
//import org.apache.mahout.cf.taste.model.JDBCDataModel;
//import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
////import org.postgresql.ds.PGPoolingDataSource;
//import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
//import com.ssafy.happyhouse.util.Util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//public class ItemSimilarityRecommendController {
//	private static Logger log = LoggerFactory.getLogger(ItemSimilarityRecommendController.class);
//	
//	@ResponseBody
//	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
//	public String recommend(HttpServletRequest request) {
//		
//		try {
////			PGPoolingDataSource dataSource = new PGPoolingDataSource();
////			dataSource.setServerName(  Util.prop("db.host") );
////			dataSource.setDatabaseName( Util.prop("db.dbname") );
////			dataSource.setUser( Util.prop("db.id") );
////			dataSource.setPassword( Util.prop("db.pwd") );
////			dataSource.setMaxConnections(20);
//			//DataSource
//			MysqlDataSource dataSource = new MysqlDataSource();
//			dataSource.setServerName(  Util.prop("spring.datasource.url") );
//			dataSource.setDatabaseName( Util.prop("happy-house-db") );
//			dataSource.setUser( Util.prop("spring.datasource.username") );
//			dataSource.setPassword( Util.prop("spring.datasource.password") );
//			//dataSource.setMaxConnections(20);
//		
//			
//			
////			MySQLJDBCDataModel(DataSource dataSource,
////                    String preferenceTable,
////                    String userIDColumn,
////                    String itemIDColumn,
////                    String preferenceColumn,
////                    String timestampColumn)
//			//JDBCDataModel dm = new PostgreSQLJDBCDataModel( dataSource  ,"recommend" , "userId",  "view", "rank", "time");
//			JDBCDataModel dd = new MySQLJDBCDataModel( dataSource  ,"recommend" , "userId",  "view", "rank", "time");
//			
//			ItemSimilarity sim = new LogLikelihoodSimilarity(dd);
//			GenericItemBasedRecommender recommender = new GenericBooleanPrefItemBasedRecommender(dd, sim);
//			
//			long itemId = 11;
//			List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemId, 5);
//
//			for(RecommendedItem recommendation : recommendations) {
//				log.debug("id" + recommendation.getItemID() );
//				log.debug("value" + recommendation.getValue() );
//			}
//		} catch (TasteException e) {
//			log.error(e.toString());
//		} catch (Exception e){
//			log.error(e.toString());
//		}
//		return "";	
//	}
//}
