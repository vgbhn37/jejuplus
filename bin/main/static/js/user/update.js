/**
 *  회원 수정
 */

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
		console.log(newEmail);
		
		if( !newEmail ){
			alert("이메일을 입력해주세요.");
		} else {
        // Check for email duplication
        $.get("/user/check-email", { email: newEmail }, function(response) {
			console.log(response);
            if (response.trim() === "duplicate") {
				
                alert("이미 누군가 사용 중인 이메일입니다.");
            } else {
                // Send a request to get the verification code
                $.post("/user/send-verification-code", { email: newEmail }, function(response) {
                    alert("인증 코드가 이메일로 전송되었습니다.");
                });
            }
        });
       }
    });

    // 인증코드 확인,이메일 업데이트
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