<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
	<div class="container">
		<a class="navbar-brand" href="/">HappyHouse</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			Menu <i class="fas fa-bars ms-1"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
				<c:if test="${empty userinfo }">
					<li class="nav-item" id="login"><a class="nav-link"
						href="${root }/user/login">Login</a></li>
					<li class="nav-item" id="register"><a class="nav-link"
						href="${root }/user/register">Register</a></li>
				</c:if>
				<c:if test="${not empty userinfo }">
					<li class="nav-item"><a class="nav-link">${userinfo.userName }님
							환영합니다.</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${root }/user/logout">Logout</a></li>
				</c:if>
				<li class="nav-item"><a class="nav-link"
					href="${root }/house/detail">Find</a></li>
				<li class="nav-item" id="member"><a class="nav-link"
					href="${root }/crawling">News</a></li>
				<li class="nav-item" id="member"><a class="nav-link"
					href="${root }/dustInfo">Dust</a></li>
			</ul>
		</div>
	</div>
</nav>