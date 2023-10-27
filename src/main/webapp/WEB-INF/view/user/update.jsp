<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/header.jsp" %>

<!-- header.jsp  -->
<main>
<div>
    <h2>내정보</h2>
    <h5>어서오세요 환영합니다</h5>
	<form action="/user/userUpdate/${userDetailDto.userId}" method="post">	
				<label for="username">Id :</label>    
                <input type="text" id="username" class="form-control" 
                name="username" value="${userDetailDto.username}" ><br>
                
                <button id="password-update-btn">비밀번호 변경</button><br>
                
                <label for="email">Email :</label>    
                <input type="text" id="email" class="form-control" 
                name="username" value="${userDetailDto.email}" ><br>	
                
                <label for="fullname">Name :</label>    
                <input type="text" id="fullname" class="form-control"
                name="fullname" value="${userDetailDto.fullname}" >	<br>
                
                <label for="phoneNumber">PhoneNumber :</label>    
                <input type="text" id="phoneNumber" class="form-control"
                name="phoneNumber" value="${userDetailDto.phoneNumber}" >	<br>
                
                <button id="">수정하기</button>	
	</form>
</div>
</main>
<!-- footer.jsp  -->
<%@ include file="/WEB-INF/view/footer.jsp" %>