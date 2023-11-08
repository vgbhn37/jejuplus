<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/header.jsp"%>
<style>
	body{
		background-color: white;
	}
	#favoriteArea {
		font-size: 40px;
		font-weight: bold;
		text-align: center;
		margin: 20px;
	}
	#container {
		width: 1000px;
		margin: 0 auto;
	}
	#box{
		background-color:#f4f4f4;
		float: left;
		margin: 10px;
		text-align : center;
	}
	#label{
		width:300px;
		background-color:#f37021;
		color: white;
		font-weight:bold;
	}
	#img-box {
		width:300px;
		height:150px;
		overflow:hidden;
	}
	#img {
		width:100%;
		height:100%;
	    object-fit: cover;
	}
	#content-box {
		margin-top: 10px;
		margin-bottom: 10px;
		float:left;
		width:300px;
	
	}
	#title {
		font-size: 20px;
		font-weight: bold;
	}
	#location {
		width:300px;
		font-size: 15px;
	}
	
</style>
<body>
	<div id="favoriteArea">찜한여행</div>
	<hr>
	<br><br>
	<div id="container">
		<c:forEach var="contents" items="${favoriteList}">
			<div id="box" onclick="location.href='/contents/touristAreaDetail/${contents.contentsId}'">
				<div id="label">${contents.contentsLabel}</div>
				<div id="img-box"><img src="${contents.thumbnailPath}" onerror="this.src='/images/NoImage.jpg'" id="img"></div>
				<div id="content-box">
					<div id="title">${contents.title}</div> 
					<div id="location">${contents.region1} > ${contents.region2}</div>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
<script>


</script>
<%@ include file="/WEB-INF/view/footer.jsp"%>