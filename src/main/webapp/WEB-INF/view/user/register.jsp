<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<main>


	<form action="/user/register" method="post" id="registrationForm">
	
		아이디: <input type="text" name="username" id="usernameInput">
		<button type="button" id="checkUsernameButton">아이디 중복 확인</button>
		<div id="usernameResult"></div>
		 
		비밀번호: <input type="password" name="password" id="passwordInput">
		<button type="button" id="checkUserPasswordButton">비밀번호 확인</button><br>
		
		 
		이름: <input type="text" name="fullname"><br>
		 
		이메일: <input type="text" name="email" id="emailInput">
		<button type="button" id="checkEmailButton">이메일 중복 확인</button>
		<button type="button" id="sendCodeButton">코드 전송</button>
		<div id="emailResult"></div>
		
		 인증 코드: <input type="text" name="code">
		<button type="button" id="verifyCodeButton">인증하기</button><br>
		 
		전화번호<input type="text" name="phoneNumber"><br>

		<input type="submit" value="가입하기">
	</form>





	<div id="result"></div>
</main>
	<script>
    // 코드 전송 버튼 클릭 시
    $("#sendCodeButton").click(function() {
        var email = $("input[name='email']").val();
        
        // 이메일을 서버로 전송하고 인증 코드를 요청
        $.post("/user/send-verification-code", { email: email }, function(response) {
            alert("인증 코드가 이메일로 전송되었습니다.");
        });
    });

    $("#verifyCodeButton").click(function() {
        var email = $("input[name='email']").val();
        var code = $("input[name='code']").val();

        // 이메일과 코드를 서버로 전송하여 인증
        $.post("/user/verify-code", { email: email, code: code }, function(response) {
            if (response === "Verification successful") {
                alert("인증이 성공적으로 완료되었습니다.");
            } else {
                alert("인증 실패");
            }
        });
    });
       
        // 가입하기 버튼 클릭 시
        $("#registrationForm").submit(function(event) {
            event.preventDefault(); // 폼 제출 방지
	
            var formData = $(this).serialize();
                              
             $.post("/user/register", formData, function(response) {
                if (response === "Registration successful") {
                    alert("가입이 성공적으로 완료되었습니다.");
                    window.location.href = "/user/sign-in"; // 로그인 페이지로 이동
                } else {
                    alert("가입 실패: " + response); // 실패 메시지를 표시
                }
            })
            .fail(function(jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 400) {
                    alert("아이디 또는 이메일을 확인해주세요."); // 400 (Bad Request) 오류 메시지
                } else {
                    alert("가입에 실패했습니다. 다시 시도해주세요."); // 다른 오류의 경우 메시지
                }
            });
                                  
            
        });
        
        // 아이디 중복 체크
        $("#checkUsernameButton").click(function() {
            var username = $("#usernameInput").val();
           
            var maxUsernameLength = 15; // 원하는 최대 글자수
            
            var minUsernameLength = 4;

            if (username.length > maxUsernameLength) {
                alert("아이디는 " + maxUsernameLength + "자 이하여야 합니다.");
                event.preventDefault(); // 폼 제출 방지
                return;
                
            } else if( username.length < minUsernameLength ) {
            	alert("아이디는 " + minUsernameLength + "자 이상이여야 합니다.");
                event.preventDefault(); // 폼 제출 방지
                return;
            }
            
            
            	$.get("/user/check-username", { username: username }, function(response) {
                alert(response);
            });
            
        });
        
     // 비밀번호 체크
        $("#checkUserPasswordButton").click(function() {
            var password = $("#passwordInput").val();
           
            var maxUsernameLength = 15; // 원하는 최대 글자수
            
            var minUsernameLength = 4;

            if (password.length > maxUsernameLength) {
                alert("비밀번호는 " + maxUsernameLength + "자 이하여야 합니다.");
                $("#passwordInput").val(""); // 입력 초기화
                $("#passwordInput").focus();
                return;
                
            } else if( password.length < minUsernameLength ) {
            	alert("비밀번호는 " + minUsernameLength + "자 이상이여야 합니다.");
            	 $("#passwordInput").val(""); // 입력 초기화
                 $("#passwordInput").focus();
                return;
            } else {
            	alert("사용가능한 비밀번호입니다.")
            }
            
            
            	
        });
        
        

        // 이메일 중복 체크
        $("#checkEmailButton").click(function() {
            var email = $("#emailInput").val();
            $.get("/user/check-email", { email: email }, function(response) {
 
                alert(response);
            });
            
        });
        
        
        
    
     
    
</script>
<%@ include file="/WEB-INF/view/footer.jsp" %>