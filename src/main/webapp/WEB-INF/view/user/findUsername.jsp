<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp"%>
<!-- header.jsp  -->
<link rel="stylesheet" href="/css/user/user.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.find-username-body{
width:600px;
margin-top:20%;
}

.find-username-body > input[type='text'] {
  width:100%;
}
.find-username-body > button {
width:60%;
height:50px;
margin-top:10%;
margin-bottom: 40%;
margin-left:20%;
}

</style>
<!-- start main.jsp -->
<main>
<div class="d-flex justify-content-center">
		

		<form action="/user/find-username" method="post">
		<h2 style = "text-align: center; margin-top:5%;">아이디 찾기</h2>
		
		<div class="find-username-body">
			<div class="form-group">
				<label for="email">이메일 주소:</label> 
				<input type="text" id="email" class="form-control mb-2 mr-sm-2" placeholder="이메일 주소 입력" name="email">
			</div>
			
			<button type="submit" class="btn bg-warning btn-lg" id="find-user-Button">아이디 찾기</button>
			</div>
		</form>


	</div>

	<!-- end of main.jsp -->

</main>
<script type="text/javascript">
$(document).ready(function() {
	$("#find-user-Button").click(function() { 
        event.preventDefault();

        var email = $("#email").val();

        $.ajax({
            type: 'POST',
            url: '/user/find-username',
            data: email,
            contentType: 'application/json',
            dataType: 'text',
            success: function(response) {
                alert("입력하신 이메일에서 아이디를 확인해주세요");
            },
            error: function(xhr, status, error) {
                alert('사용자를 찾을 수 없습니다.' );
            }
        });
    });
});
</script>
<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/footer.jsp"%>