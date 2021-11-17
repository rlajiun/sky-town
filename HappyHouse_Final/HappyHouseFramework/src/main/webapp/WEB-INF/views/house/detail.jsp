<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
<!-- Masthead-->
<header class="masthead datail-masthead">
	<div class="container">
		<div class="masthead-heading text-uppercase">주택 실거래 조회</div>
	</div>
</header>
<!-- Services-->
<section class="page-section pt-5" id="services">
	<div class="container">
		<div class="text-center">
			<h2 class="section-heading text-uppercase">주택 정보</h2>
		</div>
		<div class="text-center mb-2">
			<div class="form-group">
				<select class="btn btn-primary" id="sido">
					<option value="">시도</option>
					<option value="0">선택</option>
				</select> <select class="btn btn-primary" id="gugun">
					<option value="">구군</option>
					<option value="0">선택</option>
				</select> <select class="btn btn-primary" id="dong">
					<option value="">읍면동</option>
					<option value="0">선택</option>
				</select> <select class="btn btn-primary" id="price">
					<option value="">최대 가격</option>
					<option value="100000">100,000</option>
					<option value="200000">200,000</option>
					<option value="300000">300,000</option>
					<option value="400000">400,000</option>
					<option value="500000">500,000</option>
				</select>
			</div>
		</div>

		<div class="row my-4">
			<div class="col-lg-12">
				<div class="container p-0">
					<!-- <div
                class="list-group"
                id="list-house"
                style="overflow-y: scroll; max-height: 40rem"
              ></div> -->
					<table class="table mt-2">
						<colgroup>
							<col width="100">
							<col width="200">
							<col width="*">
							<col width="120">
							<col width="120">
						</colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>아파트이름</th>
								<th class="text-center">주소</th>
								<th>건축연도</th>
								<th>최근거래금액</th>
							</tr>
						</thead>
						<tbody id="searchResult"></tbody>
					</table>
					 <select class="btn btn-primary" id="unit" style="position: absolute;">
					<option value="5">5개씩 보기</option>
					<option value="10">10개씩 보기</option>
					<option value="15">15개씩 보기</option>
					<option value="20">20개씩 보기</option>
					<option value="40">40개씩 보기</option>
					<option value="60">60개씩 보기</option>
				</select>
					<nav aria-label="Page navigation">
						<ul class="pagination pagination-circle pg-blue justify-content-center" id="pagination">
						<li class="page-item"><a class="page-link">i</a></li>
						</ul>
					</nav>
				</div>
			</div>
			<div class="col-lg-12 my-3">
				<div id="map" style="width: 100%; height: 40rem"></div>
				<script type="text/javascript"
					src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fda17085e526213a297440847222d514&libraries=services"></script>
				<script type="text/javascript" src="${root }/resources/js/map.js"></script>
				<script type="text/javascript">
				function getInputValue(){
					var limitPrice = $('#inputId').val();
					//limitPrice 를 기준으로 그 아래 매물만 필터링 해서 정렬
					
		
					alert('최저가'+limitPrice);
				}
				
				let colorArr = ['table-primary','table-success','table-danger'];
				$(document).ready(function(){					
					$.get(root + "/map/sido"
						,function(data, status){
							$.each(data, function(index, vo) {
								$("#sido").append("<option value='"+vo.sidoCode+"'>"+vo.sidoName+"</option>");
							});
						}
						, "json"
					);
				});
				$(document).on("change", "#sido", function() {
					$.get(root + "/map/gugun"
							,{sido: $("#sido").val()}
							,function(data, status){
								$("#gugun").empty();
								$("#gugun").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#gugun").append("<option value='"+vo.gugunCode+"'>"+vo.gugunName+"</option>");
								});
							}
							, "json"
					);
				});
				$(document).on("change", "#gugun", function() {
					$.get(root + "/map/dong"
							,{gugun: $("#gugun").val()}
							,function(data, status){
								$("#dong").empty();
								$("#dong").append('<option value="0">선택</option>');
								$.each(data, function(index, vo) {
									$("#dong").append("<option value='"+vo.dongCode+"'>"+vo.dongName+"</option>");
								});
							}
							, "json"
					);
				});
				
				$(document).on("change", "#dong", function(){
					findApt(1, $('#price').val());
					findPage($('#unit').val());
				});
				
				$(document).on("change", "#unit", function(){
					findApt(1, $('#price').val());
					findPage($('#unit').val());
				});
				
				$(document).on("click", ".page-link", function(){
					findApt($(this).text(), $('#price').val());
				});
				
				function findPage(unit){
					$.get(root + "/map/page"
							,{dong: $("#dong").val(), price: $('#price').val()}
							,function(data, status){
								console.log(data);
								$("#pagination").empty();
								let str = `
									<li class="page-item"><a class="page-link">First</a></li>
									<li class="page-item"><a class="page-link"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											<span class="sr-only">Previous</span>
									</a></li>
								`;
								let page = data/unit;
								if(data%unit > 0) page++;
								for(let i = 1; i <= page; i++){
									str += `
										<li class="page-item"><a class="page-link">`+i+`</a></li>
									`;
								}
									str += `
										<li class="page-item"><a class="page-link" aria-label="Next">
										<span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
								</a></li>
								<li class="page-item"><a class="page-link">Last</a></li>
									`;
								$("#pagination").append(str);
							}
							, "json"
					);
				}
				
				function findApt(page, price){
					console.log(page);
					console.log(page*$('#unit').val());
					$.get(root + "/map/apt"
							,{dong: $("#dong").val(), cnt: $('#unit').val(), start: (page-1)*$('#unit').val(), price: price}
							,function(data, status){
								console.log(data);
								$("tbody").empty();
								$.each(data, function(index, vo) {
									console.log(data);
									let str = `
										<tr>
											<td>`+vo.aptCode+`</td>
											<td>`+vo.aptName+`</td>
											<td>`+vo.sidoName+" "+vo.gugunName+" "+vo.dongName+ " "+vo.jibun+`</td>
											<td>`+vo.buildYear+`</td>
											<td>`+vo.recentPrice+`</td>
										</tr>
									`;
									$("tbody").append(str);
								});
								displayMarkers(data);
							}
							, "json"
					);
				}
				
				//가격 필터링
				$(document).on("change", "#price", function() {
					findApt(1, $('#price').val());
					findPage($('#unit').val(), $("#price").val());
				});
				
				$(document).on("click", "#aptSearchBtn", function() {
					var param = {
							serviceKey:'d1Rx181izwjWsfI72cBRRZ648mlRP778AFOTWt/gwmGn5lz1OmJGGmbxejtDDXWjvJP8CdO1Th3fjy4zmYcVYg==',
							pageNo:encodeURIComponent('1'),
							numOfRows:encodeURIComponent('100'),
							LAWD_CD:encodeURIComponent($("#gugun").val()),
							DEAL_YMD:encodeURIComponent('202110')
					};
					$.get('http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'
							,param
							,function(data, status){
								console.log(data);
								var items = $(data).find('item');
								var jsonArray = new Array();
								items.each(function() {
									var jsonObj	= new Object();
									jsonObj.aptCode = $(this).find('일련번호').text();
									jsonObj.aptName = $(this).find('아파트').text();
									jsonObj.dongCode = $(this).find('법정동읍면동코드').text();
									jsonObj.buildYear = $(this).find('건축년도').text();
									jsonObj.jibun = $(this).find('지번').text();
									jsonObj.recentPirce = $(this).find('거래금액').text();
										
									jsonObj = JSON.stringify(jsonObj);
									//String 형태로 파싱한 객체를 다시 json으로 변환
									jsonArray.push(JSON.parse(jsonObj));
								});
								console.log(jsonArray);
								//displayMarkers(jsonArray);
							}
							, "xml"
					);
					//금액 필터링 
					
					
				});
				</script>
			</div>
		</div>
	</div>
</section>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>