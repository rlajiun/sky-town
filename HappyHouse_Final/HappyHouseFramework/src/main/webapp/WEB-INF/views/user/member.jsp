<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<style>
.container {
	width: 385px;
	line-height: 50px;
	margin: 40px auto;
}

h5 {
	text-align: center;
}

h5 span {
	color: teal;
}

.login {
	background-color: rgb(000, 153, 255);
	color: white;
	border-radius: 5px;
	border: 0;
	padding: 10px;
	text-align: center;
}

#signup {
	background-color: white;
	color: teal;
	border: 0;
	font-size: 17px;
}

p {
	text-align: center;
}

i {
	color: lightgray;
}

#imail {
	position: absolute;
	top: 130px;
	margin: 0 355px;
}

#ipw {
	position: absolute;
	top: 180px;
	margin: 0 355px;
}

input {
	border: 1px solid lightgray;
	border-radius: 3px;
}
</style>
<title>Happy House</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
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
<link href="css/styles.css" rel="stylesheet" />

<script src="vendor/jquery/jquery.min.js"></script>
<link href="css/sb-admin-2.min.css" rel="stylesheet" />
</head>
<body id="page-top">
	<c:if test="${deleteResult == 1 }">
		<script>
			alert("탈퇴가 완료되었습니다.");
			location.href = "index.jsp";
		</script>
	</c:if>
	
	<%@ include file="header.jsp" %>
	<!-- Masthead-->
	<header class="masthead datail-masthead">
		<div class="container">
			<div class="masthead-heading text-uppercase">회원 정보 관리</div>
		</div>
	</header>
	<!-- Services-->
	<section class="page-section pt-5" id="services">
		<div class="container">
			<div class="text-center">
				<h2 class="section-heading text-uppercase">내 정보 관리</h2>
				<c:if test="${checkResult == null }">
					<form action="UserServlet" method="post">
						<input type="hidden" name="cmd" value="check"> <input
							type="text" placeholder="아이디 입력" name="Id" required
							style="height: 30px; width: 200px" /><br> <input
							type="submit" value="내 정보 보기" class="login">
					</form>
				</c:if>
				<c:if test="${checkResult != null }">
					<div class="row my-4">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>FirstName</th>
									<th>LastName</th>
									<th>Id</th>
									<th>RegisterDate</th>
									<th>Member Info Delete</th>
									<th>Member Info Modify</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="fn">${checkResult.userFirstName}</td>
									<td class="ln">${checkResult.userLastName}</td>
									<td class="em">${checkResult.userId}</td>
									<td class="em">${checkResult.userSince}</td>
									<td class="em">
										<form action="UserServlet" method="post">
											<input type="hidden" name="cmd" value="delete">
											<input type="password" name="Password"
												placeholder="비밀번호를 입력하세요"><input
												type="submit" value="delete" class="btn btn-danger">
										</form>
									</td>
									<td class="em">
									<a href="update.jsp"><button class="btn btn-danger" type="button">Modify</button></a>
									</td>
									
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>

			</div>



			
		</div>
	</section>
	<!-- Footer-->
	<footer class="footer py-4">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-4 text-lg-start">Copyright &copy; Your
					Website 2021</div>
				<div class="col-lg-4 my-3 my-lg-0">
					<a class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-twitter"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-facebook-f"></i></a> <a
						class="btn btn-dark btn-social mx-2" href="#!"><i
						class="fab fa-linkedin-in"></i></a>
				</div>
				<div class="col-lg-4 text-lg-end">
					<a class="link-dark text-decoration-none me-3" href="#!">Privacy
						Policy</a> <a class="link-dark text-decoration-none" href="#!">Terms
						of Use</a>
				</div>
			</div>
		</div>
	</footer>

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<!-- * *                               SB Forms JS                               * *-->
	<!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
	<!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<script src="js/member.js"></script>
</body>
</html>
