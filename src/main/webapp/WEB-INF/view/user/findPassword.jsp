<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp"%>
<link rel="stylesheet" href="/css/user/user.css" />
<!-- header.jsp  -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- start main.jsp -->
<style>
.find-password-body{
width:600px;
margin-top:10%;

}

.find-password-body> .form-group > button {
float:right;
margin-bottom:20%;
}
.find-password-space {
  width: 4px;
  height: auto;
  display: inline-block;
}
.find-password-body> .form-group  > .find-passord-btn{
float:right;
margin-bottom:20%;
}

</style>
<main>
<div class="d-flex justify-content-center">

   <form id="reset-password-form">
   
   <h2 style = "text-align: center; margin-top:5%;">비밀번호 찾기</h2>
		
	<div class="find-password-body">
   
    <div class="form-group">
    <label for="username">아이디:</label>
        <input type="text" id="username" class="form-control" placeholder="아이디 입력" name="username"><br>
    </div>
        
        <div class="form-group">

        <label for="email">이메일 주소:</label>
        <input type="text" id="reset-password-email" class="form-control mb-2 mr-sm-2" placeholder="이메일 주소 입력" name="email">

        <button type="button" id="sendCodeButton" class="btn bg-warning mb-2">코드 전송</button>
		</div>
		
		<br>
		<br>
	<div class="form-group"> 
	   
    <label>인증 코드: </label>
    <input type="text" name="code" class="form-control mb-2 mr-sm-2" placeholder="인증 코드 입력">
    	
    <div class="find-passord-btn" >
    <button type="button" id="verifyCodeButton" class="btn bg-warning mb-2">인증하기</button>
    <div class="find-password-space"> </div>
    <button type="submit" class="btn bg-warning mb-2" id="reset-password-button">임시 비밀번호 발급</button>
	</div>
    </div>
    
   	<div class="form-group"> 


    </div>
    
    </div>
    </form>

    <div id="reset-password-message"></div>
</div>
</main>

<script type="text/javascript">
$(document).ready(function() {
    
    var codeVerified = false; // 인증 여부를 저장하기 위한 변수

    $("#sendCodeButton").click(function() {
        var username = $("input[name='username']").val();
        var email = $("input[name='email']").val();
        console.log(username);
        console.log(typeof username);
        
        $.ajax({
            type: 'POST',
            url: '/user/check-id-email',
            data: { username: username, email: email },
            dataType: 'text',
            success: function(response, textStatus, xhr) {
                if (xhr.status === 200) {
                	$.post("/user/send-verification-code", { email: email }, function(response) {
                        alert("인증 코드가 이메일로 전송되었습니다.");
                    });
                } 
            },
            error: function(xhr, status, error) {
                alert("아이디 또는 비밀번호가 일치하지않습니다.");
            }
        });
    });
        
     
    $("#verifyCodeButton").click(function() {
        var email = $("input[name='email']").val();
        var code = $("input[name='code']").val();

        // 이메일과 코드를 서버로 전송하여 인증
        $.post("/user/verify-code", { email: email, code: code }, function(response) {
            if (response === "Verification successful") {
                alert("인증이 성공적으로 완료되었습니다.");
                codeVerified = true; // 인증 완료
            } else {
                alert("인증 코드를 다시 한번 확인해주세요");
                codeVerified = false; // 인증 실패
            }
        });
    });
    
       
    $("#reset-password-form").submit(function(event) {
        event.preventDefault();

        var email = $("#reset-password-email").val();
        var messageContainer = $("#reset-password-message");

        if (codeVerified) { // 인증이 성공했을 때에만 임시 비밀번호 발급
            $.ajax({
                type: 'POST',
                url: '/user/reset-password',
                data: { email: email },
                dataType: 'text',
                success: function(response) {
                    alert(response);
                },
                error: function(xhr, status, error) {
                    alert("오류: " + error);
                }
            });
        } else {
            alert("인증되지 않았습니다.");
        }
    });
});
</script>
<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/footer.jsp"%>