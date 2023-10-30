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
		width:1633px;
		height:500px;
		overflow:hidden;
	}
	#imgPath {
		width:100%;
		height:100%;
	    object-fit: cover;
	}
	#map{
		width: 500px;
		height: 400px;
	}
</style>
<body>
	<h1>쇼핑상세보기 페이지</h1>
	<div>
		<table>
			<tbody>				
				<tr><div id="title">${shoppingDetail.title}</div></tr>
				<tr>
					<div id="img-box">
					<img src="${shoppingDetail.imgPath}" onerror="this.src='/images/NoImage.jpg'" id="imgPath">
					</div>
				</tr>
				<tr>
					<td>저장하기</td>
					<td>일정추가</td>
					<td>리뷰쓰기</td>
				</tr>
				<tr>
					<td><div id=map>지도</div></td>
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	23d4bf3926d523313e54a46d82cbb016"></script>
				</tr>
				<tr>
					<td>주소 : ${shoppingDetail.roadAddress}</td>
					<td>${shoppingDetail.phoneNo}</td>
				</tr>
				<tr>
					<td>${shoppingDetail.introduction}</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
<script>
	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	
	let options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng("${shoppingDetail.latitude}", "${shoppingDetail.longitude}"), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};
	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
</script>
</html>