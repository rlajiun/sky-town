<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ include file="/WEB-INF/views/include/navbar.jsp"%>
<script>
$(function(){
	var isId = false;
	// 아이디 중복검사
	$("#NewId").keyup(function () {
		var ckid = $("#NewId").val();
		if(ckid.length < 6 || ckid.length > 16) {
			$("#idresult").text("아이디는 6자이상 16자이하입니다.").removeClass('text-primary').removeClass('text-danger').addClass('text-dark');
			isId = false;
		} else {
            $.ajax({
            	url: '${root}/user/idcheck',
            	data: {'ckid': ckid},
              	type: 'GET',
              	dataType: 'json',
              	success: function (response) {
              		console.log(response);
                	var cnt = response;
                	if(cnt == 0) {
                		$("#idresult").text(ckid + "는 사용가능합니다.").removeClass('text-dark').removeClass('text-danger').addClass('text-primary');
                		isId = true;
                	} else {
                		$("#idresult").text(ckid + "는 사용할 수 없습니다.").removeClass('text-dark').removeClass('text-primary').addClass('text-danger');
                		isId = false;
                	}
              	}, 
              	error: function(request, status, error) {
              		console.log("status : " + request.status + "\tmsg : " + error);
              	}
			});
		}
	});
	
	$('#registerBtn').on('click', function(e){
		let ck = false;
		// 아이디 검사
		if (!$("#NewId").val()) {
            $('#id-msg').removeAttr('hidden');
            ck = false;
        } else if (!isId) {
        	$('#id-msg').attr('hidden', 'hidden');
            $('#idck-msg').removeAttr('hidden');
            ck = false;
        } else {
        	$('#idck-msg').attr('hidden', 'hidden');
        	ck = true;
        }
		
		// 패스워드 검사
		if (!$("#NewPassword").val()) {
            $('#pw-msg').removeAttr('hidden');
            ck = false;
        } else if ($("#NewPassword").val() != $("#RepeatPassword").val()) {
        	$('#pw-msg').attr('hidden', 'hidden');
            $('#rpw-msg').removeAttr('hidden');
            ck = false;
        } else {
        	$('#pw-msg').attr('hidden', 'hidden');
        	ck = true;
        }
		
		// 이름 검사
		if (!$("#NewName").val()) {
            $('#name-msg').removeAttr('hidden');
            ck = false;
        } else {
        	$('#name-msg').attr('hidden', 'hidden');
        	ck = true;
        }
		
		// 이메일 검사
		if (!$("#NewEmail").val()) {
            $('#email-msg').removeAttr('hidden');
            ck = false;
        } else {
        	$('#email-msg').attr('hidden', 'hidden');
        	ck = true;
        }
		
		if (ck) {
            $("#memberform").attr("action", "${root}/user/register").submit();
		}
	})
})
</script>
<section class="page-section pt-5" id="services">
	<div class="container">
		<div class="card o-hidden border-0 shadow-lg my-5">
			<div class="card-body p-0">
				<!-- Nested Row within Card Body -->
				<div class="row">
					<div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
					<div class="col-lg-7">
						<div class="p-5">
							<div class="text-center">
								<h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
							</div>
							<form method="post" class="user" id="memberform" action="">
								<div class="form-group">
									<input type="text" class="form-control form-control-user" 
										id="NewId" placeholder="Id" name="userId" />
									<div id="idresult" class="mt-1"></div>
									<div id=id-msg class="form-group text-danger mb-2" hidden>아이디를
										입력하세요</div>
									<div id=idck-msg class="form-group text-danger mb-2" hidden>아이디를
										확인하세요</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-6 mb-3 mb-sm-0">
										<input type="password" class="form-control form-control-user"
											id="NewPassword" placeholder="Password" name="userPwd" />
									</div>
									<div class="col-sm-6">
										<input type="password" class="form-control form-control-user"
											id="RepeatPassword" placeholder="Repeat Password" />
									</div>
								<div id=pw-msg class="form-group text-danger mb-2" hidden>비밀번호를
									입력하세요</div>
								<div id=rpw-msg class="form-group text-danger mb-2" hidden>비밀번호를
									다시 입력하세요</div>
								</div>
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="NewName" placeholder="Name" name="userName" />
								<div id=name-msg class="form-group text-danger mb-2" hidden>이름을
									입력하세요</div>
								</div>
								<div class="form-group">
									<input type="text" class="form-control form-control-user"
										id="NewEmail" placeholder="Email" name="email" />
								<div id=email-msg class="form-group text-danger mb-2" hidden>이메일을
									입력하세요</div>
								</div>
								<button type="button" id="registerBtn"
									class="btn btn-primary btn-user btn-block">Register
									Account</button>
							</form>
							<hr />
							<div class="text-center">
								<a class="small" href="${root }/user/forgot-password">Forgot
									Password?</a>
							</div>
							<div class="text-center">
								<a class="small" href="${root }/user/login">Already have an
									account? Login!</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>