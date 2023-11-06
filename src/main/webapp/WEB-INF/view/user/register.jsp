<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>

<link rel="stylesheet" href="/css/user/user.css" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<main>
<div class="d-flex justify-content-center">
	<form action="/user/register" method="post" id="registrationForm">
	<h2 style = "text-align: center; margin-top:5%;">회원가입</h2>
	
	<div class="register-body">
	
		<label>아이디:</label>		
		<div class="form-inline" >
		<input type="text" name="username" class="form-control mb-2 mr-sm-2" id="usernameInput" placeholder="사용할 아이디 입력">
		<input type="hidden" name="firstUsername" id="firstUsernameInput" value="">
		<div id="usernameResult"></div>
		</div>
		
		<div class="register-button" >
		<button type="button" id="checkUsernameButton" class="btn  mb-2" style="color: white;">중복확인</button>
		</div>
		
		<br>
		
		<div id="usernameResult"></div>
				 
		<label>비밀번호:</label>
		<div class="form-inline" >
		<input type="password" name="password" id="passwordInput" class="form-control mb-2 mr-sm-2" placeholder="사용할 비밀번호 입력">
		<input type="hidden" name="firstPassword" id="firstPasswordInput" value="">
		</div>
		
		 <div class="register-button" >
			<button type="button" id="checkUserPasswordButton" class="btn  mb-2" style="color: white;">확인</button>
		</div>
		
		<br>
		
		<label>이메일:</label>
		<div class="form-inline" >
		<input type="text" name="email" id="emailInput" class="form-control mb-2 mr-sm-2" placeholder="사용할 이메일 주소 입력">
		</div>
		
		<div class="register-button" >

		<button type="button" id="sendCodeButton" class="btn  mb-2" style="color: white;">코드전송</button>
		</div>
		
			<br>
		<div id="emailResult"></div>
		
		<label>인증 코드:</label> 
		<div class="form-inline" >
		<input type="text" name="code" class="form-control mb-2 mr-sm-2" placeholder="인증 코드 입력" id="codeInput">
		</div>
		
		<div class="register-button" >
		<button type="button" id="verifyCodeButton" class="btn  mb-2" style="color: white;">인증하기</button>
		 </div>
		 
		 	<br>
		<label>이름:</label>
		<div class="form-inline" >
		<input type="text" name="fullname" class="form-control mb-2 mr-sm-2" placeholder="성함 입력" id="fullnameInput">
		</div>
		
		<label>전화번호:</label>
		<div class="form-inline" >
		<input type="text" name="phoneNumber" class="form-control mb-2 mr-sm-2" placeholder="핸드폰 번호 입력" id="phoneNumberInput">
		</div>
		<br>
		<div id="register-final">
		<input type="submit" value="가입하기" class="btn " id="register-final-button">
		</div>		
		</div>
	</form>
</div>
	<div id="result"></div>
</main>


<script src='/js/user/register.js'></script>
<%@ include file="/WEB-INF/view/footer.jsp" %>