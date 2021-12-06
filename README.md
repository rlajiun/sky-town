# Sky Town🏡
> 공공데이터를 활용해 편리하게 원하는 집을 찾는 웹 프로젝트  


## Introduction
### Main Features
- 개인화 매물 추천
- 그룹별 아파트 시세 시각화

### Others
- 챗봇
- 소셜 로그인
- 부동산 뉴스 크롤링
- 실시간 미세먼지 정보 시각화
- 구독 매물 메일 알림 서비스
- AWS 서비스를 이용한 DB 서버 구축
- 스케줄러로 인한 최신정보 업데이트 관리

### Technologies used
|Tech|Stack|
|-|-|
|**Language**|Java, JavaScript|
|**Backend**|Spring Boot, MyBatis|
|**Frontend**|Vue.js|
|**Database**|MySQL|
|**Server**|AWS EC2|
|**DevOps**|Git|

### Developed by
|Name|Responsibility|
|:-:|:-|
|[Jiun Kim](https://github.com/rlajiun)|- 그룹별 아파트 시세 시각화 <br> - 키워드 기반 필터링 <br> - 아파트 상세 정보 조회 <br> - Q&A 게시판 <br> - 스케줄러 구현 <br> - DB 서버 구축 <br> - 데이터 파싱&핸들러 구현|
|[Yein Song](https://github.com/Semy-sudo)|- 네이버 소셜 로그인&회원가입 <br> - 챗봇 <br> - 매물 개인화 추천 <br> - 추천 매물 이메일 알림 <br> - 부동산 뉴스 크롤링 <br> - 미세먼지 시각화|

<br>

# Tech Specification
## Requirements
<table><thead><tr><th>번호</th><th>주요 기능</th><th>상세 기능</th><th>기능 설명</th><th>개발 상태</th></tr></thead><tbody><tr><td>1</td><td rowspan="8">회원</td><td>로그인</td><td></td><td>완료</td></tr><tr><td>2</td><td>회원가입</td><td></td><td>완료</td></tr><tr><td>3</td><td>비밀번호 찾기</td><td></td><td></td></tr><tr><td>4</td><td>소셜 로그인</td><td>네이버 소셜 로그인</td><td>완료</td></tr><tr><td>5</td><td>비밀번호 암호화</td><td>DB에 저장할 때</td><td></td></tr><tr><td>6</td><td>마이페이지</td><td>사용자 정보 조회</td><td></td></tr><tr><td>7</td><td>관심 정보/지역</td><td></td><td></td></tr><tr><td>8</td><td>최근 본 매물</td><td></td><td></td></tr><tr><td>9</td><td rowspan="3">지도</td><td>그룹별 시세 시각화</td><td>지역별 지도 클러스터링 + 시세 표시</td><td>완료</td></tr><tr><td>10</td><td>편의 시설 위치 제공</td><td>어린이집, 공원, 놀이터 등 아이들을 위한 편의 시설 위치 제공</td><td></td></tr><tr><td>11</td><td>인구 흐름 시각화</td><td></td><td></td></tr><tr><td>12</td><td rowspan="4">아파트</td><td>아파트 상세 정보 조회</td><td>아파트 시세, 최근 실거래가, 주변 편의시설 등 정보 제공</td><td>완료</td></tr><tr><td>13</td><td>페이징</td><td>무한 스크롤 적용</td><td></td></tr><tr><td>14</td><td>아파트 공유하기</td><td></td><td></td></tr><tr><td>15</td><td>동네정보 조회</td><td>각 법정동 카페 수, 편의점 수, 슈퍼 수 등 동네정보</td><td></td></tr><tr><td>16</td><td rowspan="5">검색</td><td>그룹별 필터링</td><td>시,구,동 별 기반 아파트 매물 필터링</td><td>완료</td></tr><tr><td>17</td><td>가격 기반 필터링</td><td></td><td></td></tr><tr><td>18</td><td>키워드 기반 필터링</td><td></td><td>완료</td></tr><tr><td>19</td><td>자녀 수 기반 필터링</td><td>자녀 수를 기반으로 집 면적에 따른 추천</td><td></td></tr><tr><td>20</td><td>매물 추천 서비스</td><td>추천 후 이메일 전송 - 관심 지역의 좋은 매물이 나오면 날려주기<br>고객이 본 매물 데이터 기반으로, 유사한 매물 추천</td><td>완료</td></tr><tr><td>21</td><td rowspan="2">리뷰</td><td>리뷰 등록</td><td>아파트별 거주민 리뷰 서비스</td><td></td></tr><tr><td>22</td><td>리뷰 정렬</td><td>최신순, 평점높은순 등 정렬</td><td></td></tr><tr><td>23</td><td rowspan="3">게시판</td><td>QnA</td><td></td><td>완료</td></tr><tr><td>24</td><td>공지사항</td><td></td><td></td></tr><tr><td>25</td><td>커뮤니티</td><td></td><td></td></tr><tr><td>26</td><td rowspan="6">기타</td><td>챗봇</td><td>Chatbot을 직접 만든것은 아니라 Api를 통해 받아오는 형식으로 구성 - 네이버 챗봇 활용 </td><td>완료</td></tr><tr><td>27</td><td>미세먼지 정보 시각화</td><td>서울시 미세먼지정보 동별 시각화</td><td>완료</td></tr><tr><td>28</td><td>뉴스기사 크롤링</td><td>부동산 뉴스기사 top10</td><td>완료</td></tr><tr><td>29</td><td>광고 서비스</td><td></td><td></td></tr><tr><td>30</td><td>주시자 수 표시</td><td>해당 매물이 실시간으로 몇명이 보고있는지 알려주는 기능</td><td></td></tr><tr><td>31</td><td>실시간 상담</td><td>전문가들과 연결해 실시간으로 상담 할 수 있는 기능</td><td></td></tr></tbody></table>

## ERD
![erd](https://user-images.githubusercontent.com/54028476/144899041-5d13a365-2583-4229-9b05-e73874572ad1.png)

## Package Structure
```
Skytown
    └─com
        └─ssafy
            └─happyhouse
                │  HappyhouseApplication.java
                │  ServletInitializer.java
                │  
                ├─apt
                │  │  AptController.java
                │  │  
                │  ├─model
                │  │  │  Apt.java
                │  │  │  AptAvg.java
                │  │  │  AptDeal.java
                │  │  │  AptInfo.java
                │  │  │  AptInfoBasic.java
                │  │  │  
                │  │  ├─mapper
                │  │  │      AptMapper.java
                │  │  │      
                │  │  └─service
                │  │          AptService.java
                │  │          AptServiceImpl.java
                │  │          
                │  └─util
                │          AptDealSaxHandler.java
                │          AptDealSaxParser.java
                │          AptDetailSaxHandler.java
                │          AptInfoSaxHandler.java
                │          AptSaxHandler.java
                │          AptSaxParser.java
                │          
                ├─config
                │      DatabaseConfig.java
                │      SchedulerConfig.java
                │      SwaggerConfig.java
                │      WebSocketConfig.java
                │      
                ├─email
                │  │  EmailController.java
                │  │  SMTPAuthenticator.java
                │  │  
                │  └─model
                │      │  Mail.java
                │      │  
                │      ├─mapper
                │      │      AptDetailMapper.java
                │      │      
                │      └─service
                │              AptDetail.java
                │              MailHandler.java
                │              MailService.java
                │              MailServiceImpl.java
                │              
                ├─map
                │  │  MapController.java
                │  │  
                │  └─model
                │      │  Zone.java
                │      │  ZoneChild.java
                │      │  
                │      ├─mapper
                │      │      MapMapper.java
                │      │      
                │      └─service
                │              MapService.java
                │              MapServiceImpl.java
                │              
                ├─qna
                │  │  AnswerController.java
                │  │  QuestionController.java
                │  │  
                │  └─model
                │      │  Answer.java
                │      │  Question.java
                │      │  
                │      ├─mapper
                │      │      AnswerMapper.java
                │      │      QuestionMapper.java
                │      │      
                │      └─service
                │              AnswerService.java
                │              AnswerServiceImpl.java
                │              QuestionService.java
                │              QuestionServiceImpl.java
                │              
                ├─recommend
                │  │  ItemSimilarityRecommendController.java
                │  │  UserBasedRecommendCsvGrandeController.java
                │  │  UserByAptScoreController.java
                │  │  
                │  └─model
                │      │  RecommendInfo.java
                │      │  ReommendAptInfo.java
                │      │  
                │      ├─mapper
                │      │      AptScoreMapper.java
                │      │      RecommendMapper.java
                │      │      
                │      └─service
                │              AptScoreService.java
                │              AptScoreServiceImpl.java
                │              RecommendService.java
                │              RecommendServiceImpl.java
                │              
                ├─user
                │  │  NaverLoginController.java
                │  │  UserController.java
                │  │  
                │  └─model
                │      │  UnAuthorizedException.java
                │      │  User.java
                │      │  
                │      ├─mapper
                │      │      UserMapper.java
                │      │      
                │      └─service
                │              JwtService.java
                │              JwtServiceImpl.java
                │              UserService.java
                │              UserServiceImpl.java
                │              
                └─util
                    │  ChatController.java
                    │  DustController.java
                    │  NewsController.java
                    │  Util.java
                    │  
                    └─model
                            Category.java
                            Dust.java
                            HouseInfoDto.java
                            News.java
```
## View Structure

<br>

# Web Page

[![시연_동영상](https://user-images.githubusercontent.com/54028476/144907343-954063ae-ff25-4483-995c-406d9a648e23.png)](https://youtu.be/amfXnoPyJTA)
