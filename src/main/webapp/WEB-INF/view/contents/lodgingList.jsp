<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	#img-box {
		width:400px;
		height:300px;
		overflow:hidden;
	}
	#img {
		width:100%;
		height:100%;
	    object-fit: cover;
	}
	#title {
		font-size: 35px;
	}
</style>
<body>
	<h1>숙소리스트 페이지</h1>
	<div>
		<c:forEach var="contents" items="${lodgingList}">
			<div id="img-box"><img src="${contents.thumbnailPath}" onerror="this.src='/images/NoImage.jpg'" id="img"></div>
			<div id="title">${contents.title}</div>
			<div>${contents.region1}>${contents.region2}</div>
			<div>${contents.tag}</div>
			<input type="button" onclick="location.href='/contents/lodgingDetail/${contents.contentsId}'" value="자세히보기">
		</c:forEach>
	</div>
</body>
</html>