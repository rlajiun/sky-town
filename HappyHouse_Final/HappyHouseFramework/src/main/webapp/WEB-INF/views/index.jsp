<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
<!-- Masthead-->
<header class="masthead">
	<div class="container">
		<div class="masthead-subheading">Welcome To Our Site!</div>
		<div class="masthead-heading text-uppercase">It's Nice To Meet
			You</div>
		<a class="btn btn-primary btn-xl text-uppercase"
			href="${root }/house/detail">Find House</a>
	</div>
</header>
<!-- Services-->
<section class="page-section pb-0" id="services">
	<div class="container">

		<div class="text-center">
			<h2 class="section-heading text-uppercase">Map</h2>
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
					</select>
				</div>


			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<div id="map" style="width: 100%; height: 500px;"></div>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fda17085e526213a297440847222d514&libraries=services"></script>
			<script type="text/javascript" src="${root }/resources/js/map.js"></script>
			<script type="text/javascript">
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
				$(document).on("change", "#dong", function() {
					$.get(root + "/map/apt"
							,{dong: $("#dong").val()}
							,function(data, status){
								displayMarkers(data);
							}
							, "json"
					);
				});
				
				$(document).on("click", "#aptSearchBtn", function() {
					var param = {
							serviceKey:'d1Rx181izwjWsfI72cBRRZ648mlRP778AFOTWt/gwmGn5lz1OmJGGmbxejtDDXWjvJP8CdO1Th3fjy4zmYcVYg==',
							pageNo:encodeURIComponent('1'),
							numOfRows:encodeURIComponent('10'),
							LAWD_CD:encodeURIComponent($("#gugun").val()),
							DEAL_YMD:encodeURIComponent('202110')
					};
					$.get('http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'
							,param
							,function(data, status){
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
				});
				</script>

		</div>
	</div>
</section>
<!-- Team-->
<section class="page-section bg-light" id="team">
	<div class="container">
		<div class="text-center">
			<h2 class="section-heading text-uppercase">Our Amazing Team</h2>
			<h3 class="section-subheading text-muted">저희 팀을 소개합니다!!!</h3>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="team-member">
					<img class="mx-auto rounded-circle" src="${root }/resources/img/profile2.jpg"
						alt="..." />
					<h4>Kim Jiun</h4>
					<p class="text-muted">Member</p>
					<a class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-twitter"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-facebook-f"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-linkedin-in"></i></a>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="team-member">
					<img class="mx-auto rounded-circle" src="${root }/resources/img/profile1.jpg"
						alt="..." />
					<h4>Song Yein</h4>
					<p class="text-muted">Member</p>
					<a class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-twitter"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-facebook-f"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-linkedin-in"></i></a>
				</div>
			</div>
		</div>

	</div>
</section>
<!-- Contact-->
<section class="page-section" id="contact">
	<div class="container">
		<div class="text-center">
			<h2 class="section-heading text-uppercase">Contact Us</h2>
		</div>
		<!-- * * * * * * * * * * * * * * *-->
		<!-- * * SB Forms Contact Form * *-->
		<!-- * * * * * * * * * * * * * * *-->
		<!-- This form is pre-integrated with SB Forms.-->
		<!-- To make this form functional, sign up at-->
		<!-- https://startbootstrap.com/solution/contact-forms-->
		<!-- to get an API token!-->
		<form id="contactForm" data-sb-form-api-token="API_TOKEN">
			<div class="row align-items-stretch mb-5">
				<div class="col-md-6">
					<div class="form-group">
						<!-- Name input-->
						<input class="form-control" id="name" type="text"
							placeholder="Your Name *" data-sb-validations="required" />
						<div class="invalid-feedback" data-sb-feedback="name:required">
							A name is required.</div>
					</div>
					<div class="form-group">
						<!-- Email address input-->
						<input class="form-control" id="email" type="email"
							placeholder="Your Email *" data-sb-validations="required,email" />
						<div class="invalid-feedback" data-sb-feedback="email:required">
							An email is required.</div>
						<div class="invalid-feedback" data-sb-feedback="email:email">
							Email is not valid.</div>
					</div>
					<div class="form-group mb-md-0">
						<!-- Phone number input-->
						<input class="form-control" id="phone" type="tel"
							placeholder="Your Phone *" data-sb-validations="required" />
						<div class="invalid-feedback" data-sb-feedback="phone:required">
							A phone number is required.</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group form-group-textarea mb-md-0">
						<!-- Message input-->
						<textarea class="form-control" id="message"
							placeholder="Your Message *" data-sb-validations="required"></textarea>
						<div class="invalid-feedback" data-sb-feedback="message:required">
							A message is required.</div>
					</div>
				</div>
			</div>
			<!-- Submit success message-->
			<!---->
			<!-- This is what your users will see when the form-->
			<!-- has successfully submitted-->
			<div class="d-none" id="submitSuccessMessage">
				<div class="text-center text-white mb-3">
					<div class="fw-bolder">Form submission successful!</div>
					To activate this form, sign up at <br /> <a
						href="https://startbootstrap.com/solution/contact-forms">https://startbootstrap.com/solution/contact-forms</a>
				</div>
			</div>
			<!-- Submit error message-->
			<!---->
			<!-- This is what your users will see when there is-->
			<!-- an error submitting the form-->
			<div class="d-none" id="submitErrorMessage">
				<div class="text-center text-danger mb-3">Error sending
					message!</div>
			</div>
			<!-- Submit Button-->
			<div class="text-center">
				<button class="btn btn-primary btn-xl text-uppercase disabled"
					id="submitButton" type="submit">Send Message</button>
			</div>
		</form>
	</div>
</section>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>