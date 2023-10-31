<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	#touristArea {
		font-size: 50px;
		text-align: center;
		margin: 50px;
	}
	#container {
		width: 800px;
		height:30px;
		margin: 0 auto;

	}
	#container div {
		display: inline-block;
	}
	#img-box {
		float:left;
		width:400px;
		height:250px;
		overflow:hidden;
		border-radius: 10px;
	}
	#img {
		width:100%;
		height:100%;
	    object-fit: cover;
	}
	#content-box {
		margin-left:30px;
		margin-bottom:30px;
		padding-left: 10px;
		float:left;
		width:360px;
		height:250px;
	
	}
	#title {
		font-size: 30px;
		font-weight: bold;
	}
	#location {
		width:350px;
		font-size: 20px;
		margin-top: 10px;
	}
	#tag {
		width:350px;
		font-size: 15px;
		color:grey;
		margin-top: 10px;
	}
	.icon{
		width:20px;
		margin-right:5px;

	}
	#icon{
		margin-top: 10px;
		font-size: 15px;
	}
	#btn {
		width: 360px;
		height: 40px;
		background-color: orange;
		color: white;
		border: none;
		margin-top: 30px;
		font-size: 15px;
		font-weight: bold;
		
	}
	#btn:hover {
	    background-color: white;
	    color: orange;
	    border: solid 2px orange;
	    cursor: pointer;
	}
	
</style>
<body>
	<div id="touristArea">관광지</div>
	<hr>
	<br><br>
	<div id="container">
		<c:forEach var="contents" items="${touristAreaList}">
			<div id="img-box"><img src="${contents.thumbnailPath}" onerror="this.src='/images/NoImage.jpg'" id="img"></div>
			<div id="content-box">
				<div id="title">${contents.title}</div> 
				<div id="location">${contents.region1} > ${contents.region2}</div>
				<div id="tag">${contents.tag}</div>
				<div id="icon">
					<div><img src="/images/좋아요.png" class="icon">1234</div>
					<div><img src="/images/리뷰.png" class="icon">1234</div>
				</div>
				<div><input type="hidden" value="${contents.contentsLabel}"></div>
				<input type="button" onclick="location.href='/contents/touristAreaDetail/${contents.contentsId}'" value="자세히보기" id="btn">
			</div>
		</c:forEach>
	</div>
</body>
<script>


</script>
</html>