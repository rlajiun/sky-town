package com.ssafy.happyhouse.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.util.model.News;

@RestController
@RequestMapping("/crawling")
public class NewsController {

	public static HashMap<String, String> map;

	@GetMapping
	public ResponseEntity<ArrayList<News>> startCrawl() throws IOException {
		// public String startCrawl(Model model) throws IOException {
		System.out.println("여오나????");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		Date currentTime = new Date();

		String dTime = formatter.format(currentTime);
		String e_date = dTime;

		currentTime.setDate(currentTime.getDate() - 1);
		String s_date = formatter.format(currentTime);

		String query = "성북구";
		String s_from = s_date.replace(".", "");
		String e_to = e_date.replace(".", "");
		int page = 1;
		ArrayList<News> newsList = new ArrayList<>();
//	       <dt class="tit">
//	        <a href="https://www.mk.co.kr/news/realestate/view/2021/11/1038972/">한강뷰가 뭐길래…용산·여의도 재건축 진통</a>
//	       </dt> 
//	       <dd class="desc"> <span class="desctxt"> 날로 커져가는 '한강 조망권'의 가치로 인해 정비사업 현장 곳곳에서 갈등이 속출하고 있다. 현재는 집 안에서 한강뷰를 볼 수 있는데, 재건축되면 한강뷰가 가능한 동·호수에 배정될지 기약이 없는 곳에서 반발..</span> <span class="date">2021.11.02 16:53</span> 
//	       </dd> 
//	      </dl> 
//	      <dl class="article_list"> 
//	       <dd class="thumb">
//	        <a href="https://www.mk.co.kr/news/realestate/view/2021/11/1039754/"><img src="https://file.mk.co.kr/meet/2021/11/image_listtop_2021_1039754_1635855428.jpg.thumb" alt=""><span class="im_boder"></span></a>
//	       </dd>
		while (page < 2) {
			String address = "https://www.mk.co.kr/news/realestate/";// 부동산 뉴스 url
			Document rawData = Jsoup.connect(address).timeout(5000).get();
//			System.out.println(rawData);
			Elements blogContent = rawData.select(".desctxt");
			Elements blogOption = rawData.select(".tit");
//			System.out.println(blogContent);

			String realURL = "";
			String realTITLE = "";
			String realCONTENT = "";

			// 10개의 뉴스 정보만 주자

			for (Element option : blogOption) {
				// System.out.println(option);
				Elements link = option.select("a");
				if (link.size() == 0)
					continue;
				realTITLE = link.text();
				// System.out.println("link"+link.text());
				realURL = link.attr("href");
				// System.out.println("url"+realURL);
				newsList.add(new News(realURL, realTITLE, realCONTENT));

			}
			int idx = 0;
			for (Element e : blogContent) {

				realCONTENT = e.text();
				newsList.get(idx++).setContent(realCONTENT);
			}
			page += 10;
		}

		return new ResponseEntity<ArrayList<News>>(newsList, HttpStatus.OK);
	}

}