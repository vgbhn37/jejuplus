<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp"%>

<!-- header.jsp  -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<main>
	<div>
		<h2>내정보</h2>
		<h5>어서오세요 환영합니다</h5>
		<form id="registrationForm"
			action="/user/userUpdate/${userDetailDto.userId}" method="post">

			<input type="hidden" id="userId" class="form-control" name="userId"
				value="${userDetailDto.userId}"> <label for="username">Id
				:</label> <input type="text" id="username" class="form-control"
				name="username" value="${userDetailDto.username}"><br>

			<button id="password-update-btn">비밀번호 변경</button><br>
			
			<div id="password-update-section" style="display: none;">
			<input type="password" id="current-password" placeholder="현재 비밀번호">			
			<input type="password" id="new-password" placeholder="새로운 비밀번호">
			<button id="confirm-password-update-btn">비밀번호 변경</button>
			</div>
			
			<label for="email">Email:</label> <input type="text"
				id="email" class="form-control" name="email" readonly
				value="${userDetailDto.email}">
			<button type="button" id="updateEmailButton">이메일 변경</button>

			<div id="verificationCodeSection" style="display: none;">
				<label for="email">Email:</label> <input type="text" id="newEmail"
					class="form-control" name="newEmail" value="${userDetailDto.email}">
				<button type="button" id="checkEmailButton">중복체크</button>
				<button type="button" id="sendCodeButton">코드 전송</button>
				<br> 인증 코드: <input type="text" name="code">
				<button type="button" id="verifyCodeButton">인증하기</button>
			</div><br>
			
						
			<label for="fullname">Name :</label> <input type="text" id="fullname" class="form-control" name="fullname" value="${userDetailDto.fullname}">
			<br> 
			<label for="phoneNumber">PhoneNumber :</label> <input type="text" id="phoneNumber" class="form-control" name="phoneNumber" value="${userDetailDto.phoneNumber}"> 
			<br>

			<button id="update-user-Button">수정하기</button>
		</form>
	</div>
</main>
<script>

// 이메일 부분
$(document).ready(function() {
    var originalEmail = $("input[name='email']").val();

    $("#updateEmailButton").click(function() {
        // Hide the read-only email input and show the email update section
        $("#email").hide();
        $("#updateEmailButton").hide();
        $("#verificationCodeSection").show();
    });

    // 버튼 누르면 등장
    $("#sendCodeButton").click(function() {
        var newEmail = $("input[name='newEmail']").val();

        // 인증 코드(기존 이메일인지 아닌지)
        if (newEmail !== originalEmail) {
            // Send a request to get the verification code
            $.post("/user/send-verification-code", { email: newEmail }, function(response) {
                alert("인증 코드가 이메일로 전송되었습니다.");
            });
        } else {
            alert("이메일이 변경되지 않았습니다.");
        }
    });
    
 // 중복체크
    $("#checkEmailButton").click(function() {
    	 var email = $("input[name='newEmail']").val();
        $.get("/user/check-email", { email: email }, function(response) {
            alert(response);
        });
    });

    // 이메일 업데이트
    $("#verifyCodeButton").click(function() {
        var email = $("input[name='newEmail']").val();
        var code = $("input[name='code']").val();
		var userId = $("input[name='userId']").val();

        // Send a request to verify the code
        $.post("/user/verify-code", { email: email, code: code, userId: userId }, function(response) {
            if (response === "Verification successful") {
                alert("인증이 성공적으로 완료되었습니다.");
				console.log(userId);
				console.log(typeof userId);
                $.post("/user/update-email/" + userId, { email: email }, function(updateResponse) {
                    emailVerified = true;
                });
            } else {
                alert("인증 실패");
            }
        });
    });
});

$(document).ready(function() {
    // 비밀번호 변경 버튼 클릭 시
    $("#password-update-btn").click(function(event) {
        // 기본 동작 중단 (페이지 리로드 방지)
        event.preventDefault();
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

        $.ajax({
            type: 'POST',
            url: "/user/check-update-password/" + userId,
            data: JSON.stringify(requestData),
            contentType: "application/json",
            dataType: "json",
            success: function(response) {
            	console.log(response);
                if (response.status === "PasswordUpdated") {
                    console.log("비밀번호가 성공적으로 변경되었습니다.");
                } else if (response.status === "UserNotFound") {
                    console.log("비밀번호를 찾을 수 없습니다.");
                } else {
                    console.log("비밀번호 업데이트 실패");
                }
            }
        });
    });


});

// 수정하기버튼
$("#update-user-Button").click(function() {
    alert("수정이 완료되었습니다.");
});

 </script>
<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/footer.jsp"%>