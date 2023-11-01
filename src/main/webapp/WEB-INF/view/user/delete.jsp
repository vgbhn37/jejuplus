<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp"%>
<link rel="stylesheet" href="/css/user/user.css" />
<!-- header.jsp  -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- start main.jsp -->
<style>
.delete-user-body{
width:600px;
margin-top:10%;

}
.delete-user-body > #delete-user-btn{
float:right;
margin-bottom:20%;
}

</style>
<main>
<div class="d-flex justify-content-center">

   <form action="/user/userDelete" id="delete-user" method="post">

   
   <h2 style = "text-align: center; margin-top:5%;">회원 탈퇴</h2>
		
	<div class="delete-user-body">
   
    <div class="form-group">
    	<label for="username">아이디:</label>
        <input type="text" id="username" class="form-control" placeholder="아이디 입력" name="username"><br>
    </div>
        
        <div class="form-group">
        <label for="password">비밀번호:</label>
        <input type="password" id="delete-user-epassword" class="form-control mb-2 mr-sm-2" placeholder="비밀번호 입력" name="password">
		</div>
		
		<br>
		<br>
    <button type="submit" class="btn bg-warning mb-2" id="delete-user-btn">회원탈퇴</button>
</div>
</form>
</div>
    
 
</main>

<script type="text/javascript">
$("#delete-user-btn").click(function() {
	alert("탈퇴가 완료되었습니다.");
}
</script>
<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/footer.jsp"%>