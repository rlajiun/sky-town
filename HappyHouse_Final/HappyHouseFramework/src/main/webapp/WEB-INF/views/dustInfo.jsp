<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/navbar.jsp" %>
<!-- jQuery 스크립트 -->

<script type="text/javascript">

	// 아래의 body를 화면에 랜더링 한 후 무조건 1번 실행하도록 예약 걸어놓는 설정
	$(function() {
		$('#btn-search').click(function() {
			var area = $("#btn-search option:selected").val();
			$.ajax({
				//cors 에러남!
				//url:'https://openapi.naver.com/v1/search/shop?query='+$('#keyword').val(),
                //headers: {'X-Naver-Client-Id':client_id, 'X-Naver-Client-Secret': client_secret},
				url:'/ajax/dustInfo',
                success:function(result){
                	makeChart(result,area);
					console.log('공공 api 응답 완료');
					//console.log(result);
				},
				error:function(err){
					console.log('네이버 응답 에러!');
					console.log(err);
				}
			})
		})
	})
</script>
<script type="text/javascript">
let httpRequest;

/* 
function getWordCloud() {
  if (httpRequest.readyState == 4) {
    if (httpRequest.status == 200) {
      let word_list = [];
      let txt = httpRequest.responseText;
      console.log(txt);
      let datas = JSON.parse(txt);
      datas.forEach((data) => {
        let wc = new Object();
        wc.text = data.text;
        wc.weight = data.weight;
        wc.link = "javascript:updateCount('" + data.text + "');";
        word_list.push(wc);
      });
      $("#comments").empty();
      $("#comments").jQCloud(word_list, {
        autoResize: true,
        delay: 50
      });
      makeChart(word_list);
      //makeConcern(word_list);
    }
  }
} */

/* 미세먼지: 30
초미세먼지 농도: 18
오존(ppm)0.023
이산화질소농도(ppm)0.031
통합대기환경등급보통 */

function makeChart(datas,area) {
	let label = ["미세먼지", "초미세먼지", "오존", "이산화질소농도", "통합대기환경등급"];
	console.log("area"+area);
	let data = [];
	for(var i in datas){
		if(datas[i].msrste_NM == area){
			console.log(datas[i]);
			data.push(datas[i].pm10);
			data.push(datas[i].pm25);
			data.push(datas[i].o3*100);
			data.push(datas[i].no2*100);
			data.push(datas[i].pm10);
		}
	}
    
    let ctx = document.getElementById("myChart").getContext("2d");
    let myChart = new Chart(ctx, {
      type: "bar",
      data: {
        labels: label,
        datasets: [
          {
            label: "# of Votes",
            data: data,
            backgroundColor: [
              "rgba(255, 99, 132, 0.2)",
              "rgba(54, 162, 235, 0.2)",
              "rgba(255, 206, 86, 0.2)",
              "rgba(75, 192, 192, 0.2)",
              "rgba(153, 102, 255, 0.2)",
              "rgba(255, 159, 64, 0.2)"
            ],
            borderColor: [
              "rgba(255, 99, 132, 1)",
              "rgba(54, 162, 235, 1)",
              "rgba(255, 206, 86, 1)",
              "rgba(75, 192, 192, 1)",
              "rgba(153, 102, 255, 1)",
              "rgba(255, 159, 64, 1)"
            ],
            borderWidth: 1
          }
        ]
      },
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true
              }
            }
          ]
        }
      }
    });
  }
  
function makeConcern(datas) {
    let concern = ``;
    datas.forEach(data => {
      concern += `
      <div class="form-check">
        <label class="form-check-label">
          <input type="checkbox" name="concern" class="form-check-input" value="${data.text}">${data.text}
        </label>
      </div>
      `;
    });
    document.getElementById("concernDiv").innerHTML = concern;
  }

  function sendConcern() {
    let concerns = [];
    let checks = document.getElementsByName("concern");
    checks.forEach(ck => {
      if(ck.checked == true) {
        concerns.push(ck.value);
      }
    });
    let data = {"concerns" : concerns};
    console.log(data);
    $.ajax({
      url: "http://localhost:9999/startcamp/word/",
      type: "post",
      data: data
    });
    // sendRequest(`http://localhost:9999/startcamp/word`, data, null, "POST");
    document.location.reload(); // 부모창 새로고침.
    $("#concernModal").modal("hide"); // 모달창 닫기.
  }
</script>

<section class="page-section pt-5" id="services">
<div class="container">
	<h2>서울시 구 별 환경정보를 알려줍니다.</h2>
	<!-- <button id="btn-search">미세먼지 보기</button> -->
	<select id="btn-search">
		<option value="중구">중구</option>
		<option value="종로구">종로구</option>
		<option value="용산구">용산구</option>
		<option value="중구">은평구</option>
		<option value="종로구">서대문구</option>
		<option value="용산구">마포구</option>
		<option value="중구">광진구</option>
		<option value="종로구">성동구</option>
		<option value="용산구">동대문구</option>
	</select>
	
	<canvas id="myChart" width="400" height="300"></canvas>
	</div>
	</section>

<%@ include file="/WEB-INF/views/include/footer.jsp"%>