<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/view/header.jsp"%>
<style>
	body{
		background-color:white;
	}
	#box {
		width:1000px;
		text-align: center;
		margin: auto;
	}
	#img-box {
		width:100%;
		height:300px;
		overflow:hidden;
	}
	#imgPath {
		width:100%;
		height:100%;
	    object-fit: cover;
	}
	#title {
		text-alight:center;
		font-size: 60px;
		font-weight:bold;
		margin-top: 50px;
	}
	#introduction {
		color: orange;
		font-size: 25px;
		margin: auto;
	}
	#tag {
		font-size: 18px;
		color:grey;

	}
	#map{
		margin: auto;
		width: 1000px;
		height: 600px;
	}
	#infomation {
		margin: auto;
		width: 1000px;
		height: 200px;
		background-color:#f4f4f4;
		font-size: 18px;
	}
	.subTitle {
		margin: auto;
		width: 1000px;
		height: 30px;
		background: #fdf0e5;
		border: 1.5px solid orange;
		color: orange;
		font-size: 15px;
		font-weight: bold;
	}
	.icon {
		width: 40px;
	}
	#icon {
		width:1000px;
		font-size: 15px;
		margin: auto;

	}
	.favorite_heart {
		width: 40px;
	}
	#insertReview{
		margin: auto;
		width: 1000px;
	}
	#insertReview fieldset{
	    display: inline-block;
	    direction: rtl;
	    border:0;
	}
	#insertReview fieldset legend{
	    text-align: right;
	}
	#insertReview input[type=radio]{
	    display: none;
	}
	#insertReview label{
	    font-size: 3em;
	    color: transparent;
	    text-shadow: 0 0 0 #f0f0f0;
	}
	#insertReview label:hover{
	    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
	}
	#insertReview label:hover ~ label{
	    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
	}
	#insertReview input[type=radio]:checked ~ label{
	    text-shadow: 0 0 0 rgba(250, 208, 0, 0.99);
	}
	#reviewContent {
	    width: 1000px;
	    height: 150px;
	    padding: 10px;
	    box-sizing: border-box;
	    border: solid 1.5px #D3D3D3;
	    border-radius: 5px;
	    font-size: 16px;
	    resize: none;
	}
	#reviewList {
		margin: auto;
		width: 1000px;
	}
	
	.yellowStar{
		color: rgba(250, 208, 0, 0.99);
		margin-left:-5px;
	}
	.greyStar {
		color: #f0f0f0;
		margin-left:-5px;
	}
	
	
</style>
<body>
	<div>
		<div id="img-box">
		<img src="${shoppingDetail.imgPath}" onerror="this.src='/images/NoImage.jpg'" id=imgPath>
		</div>
	</div>
	<div id="box">		
		<br>
		<div id="title">${shoppingDetail.title}</div>
		<br><br>
		<div id="introduction">" ${shoppingDetail.introduction} "</div>
		<br><br>
		<div id="tag">${shoppingDetail.tag}</div>
		<div><input type="hidden" value="${shoppingDetail.contentsLabel}" id="contentsLabel"></div>
		<br><br>
		<hr><br>
		<table id="icon">
			<tr>
				<td>
					<c:if test="${principal != null}">
						<c:choose>
							<c:when test="${isRecommend}">
								<img src="/images/좋아요2.png" class="icon" id="recommend" >
							</c:when>
							<c:otherwise>
								<img src="/images/좋아요.png" class="icon" id="unrecommend">
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${principal == null}">
						<img src="/images/좋아요.png" class="icon">
					</c:if>
				</td>
				<td><a href="#insertReview"><img src="/images/리뷰.png" class="icon"></a></td>
				<td>
					<input type="hidden" id="userId" value="${principal.userId}">
					<c:if test="${principal != null}">
						<c:choose>
							<c:when test="${isFavorite}">
								<img src="/images/저장2.png" class="icon" id="favorite" >
							</c:when>
							<c:otherwise>
								<img src="/images/저장.png" class="icon" id="unfavorite">
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:if test="${principal == null}">
						<img src="/images/저장.png" class="favorite_heart">
					</c:if>
				</td>
			</tr>
			<tr>
				<td>추천하기</td>
				<td>리뷰쓰기</td>
				<td>저장하기</td>
			</tr>
		</table>
		<br><hr>
		<br><br>
		<div class="subTitle">상세정보</div>
		<br><br>
		<div>
			<div id=map>지도</div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	23d4bf3926d523313e54a46d82cbb016"></script>
		</div><br><br>
		<div id="infomation">
			<div>기본정보</div>
			<hr>
			<div>${shoppingDetail.title}</div>
			<div>주소 : ${shoppingDetail.roadAddress}</div>
			<div>연락처 : ${shoppingDetail.phoneNo}</div>
		</div>
		<br><br><br>
		<div class="subTitle">리뷰</div>
		<br>
	</div>
	<form class="mb-3" name=insertReview id="insertReview" method="post">
		<fieldset>
			<span class="text-bold">별점을 선택해주세요</span>
			<input type="radio" name="reviewStar" value="5" id="rate1"><label for="rate1">★</label>
			<input type="radio" name="reviewStar" value="4" id="rate2"><label for="rate2">★</label>
			<input type="radio" name="reviewStar" value="3" id="rate3"><label for="rate3">★</label>
			<input type="radio" name="reviewStar" value="2" id="rate4"><label for="rate4">★</label>
			<input type="radio" name="reviewStar" value="1" id="rate5"><label for="rate5">★</label>
		</fieldset>
		<div>
			<textarea id="reviewContent" placeholder="직접 경험한 솔직한 리뷰를 남겨주세요."></textarea>
		</div>
		<div>
			<input type="hidden" id="contentsId" value="${shoppingDetail.contentsId}">
		</div>
		<input type="button" value="등록" id="insertReviewBtn">
	</form>
	<br>
	<div id="reviewList">
		<c:forEach var="review" items="${review}">
			<table>
				<tr>
					<td>
						<div>
							<span class="${review.reviewStar>=1 ? "yellowStar" : "greyStar"}">★</span>
							<span class="${review.reviewStar>=2 ? "yellowStar" : "greyStar"}">★</span>
							<span class="${review.reviewStar>=3 ? "yellowStar" : "greyStar"}">★</span>
							<span class="${review.reviewStar>=4 ? "yellowStar" : "greyStar"}">★</span>
							<span class="${review.reviewStar>=5 ? "yellowStar" : "greyStar"}">★</span>
						</div>					
					</td>
					<td>
						<div><input type="hidden" value="${review.reviewId}" id="reviewId"></div>
						<div>${review.username}</div>
					</td>
					<td>
						<div>${review.createdAt}</div>
					</td>
					<td>
						<div>${review.reviewRecommend}</div>
					</td>
				</tr>
				<tr>
					<td>${review.reviewContent}</td>
					<c:if test="${review.userId eq principal.userId}">
						<td><input type="button" value="수정" id="updateReviewBtn"></td>
						<td><input type="button" value="삭제" id="deleteReviewBtn"></td>
					</c:if>
				</tr>
			</table>
		<br><hr><br>
		</c:forEach>
	</div>
</body>
<script>
	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	
	let options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng("${shoppingDetail.latitude}", "${shoppingDetail.longitude}"), //지도의 중심좌표.
		level: 7 //지도의 레벨(확대, 축소 정도)
	};
	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	// 마커가 표시될 위치
	var markerPosition  = new kakao.maps.LatLng("${shoppingDetail.latitude}", "${shoppingDetail.longitude}"); 

	// 마커를 생성
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정
	marker.setMap(map);
	
</script>
<script src="/js/contents/contents.js"></script>
<%@ include file="/WEB-INF/view/footer.jsp"%>