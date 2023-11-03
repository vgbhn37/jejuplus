<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp"%>

<link rel="stylesheet" href="/css/user/user.css" />
<!-- header.jsp  -->
<style>
.userUpdate-body{
width:600px;
}

.userUpdate-body > label {
margin-top: 7%;
}
.userUpdate-body > .form-inline input[type='text'],.form-inline input[type='password'] {
  width:100%;
}

.userUpdate-body > button {
width:60%;
height:60px;
margin-left:20%;
margin-top: 5%;
}
.userUpdate-body > .email-password-btn {
float:right;
}

.userUpdate-body > .email-password-btn {
float:right;
}
.userUpdate-body > #verificationCodeSection > label {
margin-top: 7%;
}
.userUpdate-body > #verificationCodeSection > .form-inline input[type='text'] {
  width:100%;
}
.userUpdate-body > #verificationCodeSection >  .email-password-btn {
float:right;
}
#user-delete-location-btn{
margin-left:42%;
}
#user-delete-location-btn:hover{
text-decoration: underline;
cursor : pointer;
font-weight:bold;
}
</style>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



<main>
	
		<div class="d-flex justify-content-center">
		<form id="registrationForm" action="/user/userUpdate/${userDetailDto.userId}" method="post">
		
		<h2 style = "text-align: center; margin-top:5%;">내 정보</h2>
		
		<div class="userUpdate-body">
			<input type="hidden" id="userId" class="form-control" name="userId" value="${userDetailDto.userId}"> 
			
			
			<label for="username">Id:</label> 
			<div class="form-inline" >
			<input type="text" id="username" class="form-control" name="username" value="${userDetailDto.username}">
			</div>
			
		
			
			<label for="fullname">Name :</label> 
			<div class="form-inline" >			
			<input type="text" id="fullname" class="form-control" name="fullname" value="${userDetailDto.fullname}">
			</div> 
			
			<label for="phoneNumber">PhoneNumber :</label> 
			<div class="form-inline" >
			<input type="text" id="phoneNumber" class="form-control" name="phoneNumber" value="${userDetailDto.phoneNumber}"> 
			</div>
			
		
			 
		
			<label for="orgemail">Email:</label> 
			<div class="form-inline" >
			<input type="text" id="email" class="form-control mb-2 mr-sm-2" name="email" readonly value="${userDetailDto.email}">
			</div>
			
			<div class="email-password-btn">
			<button type="button" id="updateEmailButton" class="btn bg-warning mb-2">이메일 변경</button>
			</div>
									
			<div id="verificationCodeSection" style="display: none;">			
				<label for="newemail">Email:</label>
				
				<div class="form-inline" >
				<input type="text" id="newEmail" class="form-control mb-2 mr-sm-2" name="newEmail" value="${userDetailDto.email}" >
				</div>
				<div class="email-password-btn">
				<button type="button" id="sendCodeButton"  class="btn bg-warning mb-2">코드 전송</button>
				</div>
				
				
				
				<label>인증 코드: </label>
				
				<div class="form-inline" >
				<input type="text" name="code" class="form-control mb-2 mr-sm-2">
				</div>
				
				<div class="email-password-btn">
				<button type="button" id="verifyCodeButton"  class="btn bg-warning mb-2">인증하기</button>
				</div>
			</div>	
			
			
			<label>비밀번호: </label>
			<div class="form-inline" >
			<input type="text" id="password-text" class="form-control mb-2 mr-sm-2" name="password-text" readonly value="***********">
			</div>
			
			<div class="email-password-btn">
			<button id="password-update-btn" class="btn bg-warning mb-2">비밀번호 변경</button>		
			</div>
		
			
			<div class="form-inline" >
			<div id="password-update-section" style="display: none;">
			<input type="password" id="current-password" placeholder="현재 비밀번호" class="form-control mb-2 mr-sm-2">			
			<input type="password" id="new-password" placeholder="새로운 비밀번호" class="form-control mb-2 mr-sm-2">
			<div id="confirm-password-update-btn" class="btn bg-warning mb-2">비밀번호 변경</div>
			<hr class="my-2">
			</div>
			</div>
		
		
						<button id="update-user-Button" class="btn bg-warning mb-2">수정하기</button>
		
			
			<div>
			<div onclick="window.location.href='/user/userDelete'" class="btn btn-red mb-2" id="user-delete-location-btn">회원 탈퇴</div>
			</div>
			</div>
			
		</form>
		
	</div>
	
</main>
<script>

// 이메일 부분
// This block is for the email update part
$(document).ready(function() {
    var originalEmail = $("input[name='email']").val();

    $("#updateEmailButton").click(function() {
        // Hide the read-only email input and show the email update section
        $("#email").hide();
        $("label[for='orgemail']").hide();
        $("#updateEmailButton").hide();
        $("#verificationCodeSection").show();
    });

    // Button to send verification code
    $("#sendCodeButton").click(function() {
        var newEmail = $("input[name='newEmail']").val();

        // Check for email duplication
        $.get("/user/check-email", { email: newEmail }, function(response) {
            if (response === "duplicate") {
                alert("이미 누군가 사용 중인 이메일입니다.");
            } else {
                // Send a request to get the verification code
                $.post("/user/send-verification-code", { email: newEmail }, function(response) {
                    alert("인증 코드가 이메일로 전송되었습니다.");
                });
            }
        });
    });

    // Button to verify the code and update the email
    $("#verifyCodeButton").click(function() {
        var email = $("input[name='newEmail']").val();
        var code = $("input[name='code']").val();
        var userId = $("input[name='userId']").val();

        // Send a request to verify the code
        $.post("/user/verify-code", { email: email, code: code, userId: userId }, function(response) {
            if (response === "Verification successful") {
                alert("변경이 성공적으로 완료되었습니다.");
                $.post("/user/update-email/" + userId, { email: email }, function(updateResponse) {
                    emailVerified = true;
                    window.location.href = "/user/userUpdate/" + userId;
                });
            } else {
                alert("인증 실패");
            }
        });
    });
});

// 비밀번호 부분
$(document).ready(function() {
    // 비밀번호 변경 버튼 클릭 시
    $("#password-update-btn").click(function(event) {
        // 기본 동작 중단 (페이지 리로드 방지)
        event.preventDefault();
        $("#password-update-btn").hide();
        $("#password-update-section").show();
    });

    $("#confirm-password-update-btn").click(function() {
        var currentPassword = $("#current-password").val();
        var newPassword = $("#new-password").val();
        var userId = $("input[name='userId']").val();
        
        var requestData = {
            currentPassword: currentPassword,
            newPassword: newPassword
        };
        
        console.log("비동기 함수 시작");
        $.ajax({
            type: 'POST',
            url: "/user/check-update-password/" + userId,
            data: JSON.stringify(requestData),
            contentType: "application/json",
            dataType: "json",
            success: function(response) {
                if (response.status === "PasswordUpdated") {
                    alert("비밀번호가 성공적으로 변경되었습니다.");
                    window.location.href="/user/userUpdate/" + userId
                } else if (response.status === "UserNotFound") {
                	alert("비밀번호를 찾을 수 없습니다.");
                } else {
                	alert("비밀번호 업데이트 실패");
                }
            }
        });
        console.log("비동기 함수 끝");
    });
});

// 수정하기버튼
$("#update-user-Button").click(function() {
    alert("수정이 완료되었습니다.");
});

 </script>
<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/footer.jsp"%>