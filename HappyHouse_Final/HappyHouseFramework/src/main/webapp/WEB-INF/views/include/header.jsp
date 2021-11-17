<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>HappyHouse</title>
<!-- Favicon-->
<link rel="icon" type="${root }/resources/image/x-icon"
	href="${root }/resources/assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css" />
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700"
	rel="stylesheet" type="text/css" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${root }/resources/css/styles.css" rel="stylesheet" />

<script src="${root }/resources/vendor/jquery/jquery.min.js"></script>
<link href="${root }/resources/css/sb-admin-2.min.css" rel="stylesheet" />
<link href="${root }/resources/css/apt.css" rel="stylesheet" />

<!-- 합쳐지고 최소화된 최신 CSS -->
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->

<!-- data 시각화 (wordcloud) -->
<script src="${root}/resources/js/jqcloud.min.js"></script>
<link rel="stylesheet" href="${root}/resources/js/jqcloud.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!-- data 시각화 (wordcloud) -->
<script type="text/javascript" src="${root}/resources/js/httpRequest.js"></script>
</head>
<body id="page-top">