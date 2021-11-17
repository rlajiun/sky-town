<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
<!-- The Modal -->
<section class="page-section pt-5" id="services">
	<div class="container">

		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
					<div class="col-lg-6">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
							</div>
							<form class="user" action="${root }/user/login" method="post">
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="InputId" name="userId" aria-describedby="emailHelp"
										placeholder="Enter id..." value=${cookie.userId.value }>
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-user"
										name="userPwd" id="InputPassword" placeholder="Password">
								</div>
								<div id=msg class="form-group text-danger mb-2">${msg }</div>
								<div class="form-group">
									<div class="custom-control custom-checkbox small">
										<input type="checkbox" class="custom-control-input"
											id="customCheck" name="customCheck" value="saveok"
											<c:if test="${not empty cookie.userId.value }">checked</c:if>>
										<label class="custom-control-label" for="customCheck">Remember
											Me</label>
									</div>
								</div>
								<button type="submit" class="btn btn-primary btn-user btn-block"
									id="loginBtn">Login</button>
							</form>
							<hr>
							<div class="text-center">
								<a class="small" href="${root }/user/forgot-password">Forgot
									Password?</a>
							</div>
							<div class="text-center">
								<a class="small" href="${root }/user/register">Create an
									Account!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>