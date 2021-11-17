<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
<!-- Compiled and minified CSS -->

<link
    href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
    rel="stylesheet">

<link href='https://fonts.googleapis.com/css?family=Anton'
    rel='stylesheet' type='text/css'>

<link href='https://fonts.googleapis.com/css?family=Neucha'
    rel='stylesheet' type='text/css'>

<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
<!-- Compiled and minified JavaScript -->

<script
    src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


<section class="page-section pt-5" id="services">
    <div class="container">
        <h2>실시간 부동산 뉴스 TOP 10</h2>



        <c:forEach var="url" items="${urls}" varStatus="status" begin="14"
            end="24" >
            <!-- end="${titles.size()}" >-->
            
            <div class="card">
                <div class="card-image waves-effect waves-block waves-light">
                    <img class="activator" src="img/office.png">
                </div>
                <div class="card-content">
                    <span class="card-title activator grey-text text-darken-4">${titles[status.index]}<i
                        class="material-icons right"><b>사진클릭</b></i></span>
                    <p>
                        <a href="${url}">뉴스페이지로 이동하려면 여기를 클릭하시오</a>
                    </p>
                </div>
                <div class="card-reveal">
                    <span class="card-title grey-text text-darken-4">Card Title<i
                        class="material-icons right">close</i>
                    </span>
                    <p>${contents[status.index]}</p>
                </div>
            </div>
            
        </c:forEach>


    </div>
</section>


<%@ include file="/WEB-INF/views/include/footer.jsp"%>