<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	#box {
		width:1200px;
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
		font-size: 80px;
		font-weight:bold;
		margin-top: 50px;
	}
	#introduction {
		color: orange;
		font-size: 35px;
		margin: auto;
	}
	#tag {
		font-size: 20px;
		color:grey;

	}
	#map{
		margin: auto;
		width: 1200px;
		height: 600px;
	}
	#infomation {
		margin: auto;
		width: 1200px;
		height: 100px;
		background-color:#f4f4f4;
		font-size: 20px;
	}
	.subTitle {
		margin: auto;
		width: 1200px;
		height: 30px;
		background: #fdf0e5;
		border: 1.5px solid orange;
		color: orange;
		font-size: 20px;
	}
	.icon {
		width: 60px;
	}
	#icon {
		width:1200px;
		font-size: 20px;
		margin: auto;

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
	    width: 1200px;
	    height: 150px;
	    padding: 10px;
	    box-sizing: border-box;
	    border: solid 1.5px #D3D3D3;
	    border-radius: 5px;
	    font-size: 16px;
	    resize: none;
	}
</style>
<body>
	<div>
		<div id="img-box">
		<img src="${touristAreaDetail.imgPath}" onerror="this.src='/images/NoImage.jpg'" id=imgPath>
		</div>
	</div>
	<div id="box">		
		<br>
		<div id="title">${touristAreaDetail.title}</div>
		<br><br>
		<div id="introduction">" ${touristAreaDetail.introduction} "</div>
		<br><br>
		<div id="tag">${touristAreaDetail.tag}</div>
		<br><br>
		<hr>
		<table id="icon">
			<tr>
				<td><img src="/images/좋아요.png" class="icon"></td>
				<td><a href="#insertReview"><img src="/images/리뷰.png" class="icon"></a></td>
				<td><img src="/images/저장.png" class="icon"></td>
			</tr>
			<tr>
				<td>추천하기</td>
				<td>리뷰쓰기</td>
				<td>일정추가</td>
			</tr>
		</table>
		<hr>
		<br><br>
		<div class="subTitle">상세정보</div>
		<br><br>
		<div>
			<div id=map>지도</div>
			<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=	23d4bf3926d523313e54a46d82cbb016"></script>
		</div>
		<div id="infomation">
			<div>${touristAreaDetail.title}</div>
			<div>주소 : ${touristAreaDetail.roadAddress}</div>
			<div>연락처 : ${touristAreaDetail.phoneNo}</div>
		</div>
		<br><br>
		<div class="subTitle">리뷰</div>
		<br><br>
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
			<input type="hidden" id="contentsId" value="${touristAreaDetail.contentsId}">
		</div>
		<input type="submit" value="등록" id="insertReviewBtn">
	</form>
	<div>
		<c:forEach var="review" items="${review}">
			<div><input type="hidden" value="${review.reviewId}" id="reviewId"></div>
			<div>${review.userId}</div>
			<div>${review.reviewContent}</div>
			<div>${review.createdAt}</div>
			<div>${review.reviewRecommend}</div>
			<input type="submit" value="수정" id="updateReviewBtn">
			<input type="submit" value="삭제" id="deleteReviewBtn">
		</c:forEach>
		<hr>
	</div>
</body>
<script>
	const container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	
	let options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng("${touristAreaDetail.latitude}", "${touristAreaDetail.longitude}"), //지도의 중심좌표.
		level: 7 //지도의 레벨(확대, 축소 정도)
	};
	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	// 마커가 표시될 위치
	var markerPosition  = new kakao.maps.LatLng("${touristAreaDetail.latitude}", "${touristAreaDetail.longitude}"); 

	// 마커를 생성
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	// 마커가 지도 위에 표시되도록 설정
	marker.setMap(map);
	
	const insertReviewBtn = document.getElementById("insertReviewBtn")
	let contentsId = document.getElementById("contentsId").value;

    // 버튼 클릭 이벤트 감지
    insertReviewBtn.addEventListener("click", () => {

        // 닉네임과 댓글 내용에 작성된 내용으로 객체를 생성
        // id 값으로 입력된 값을 가져옴
        const review = {
        	reviewContent: document.getElementById("reviewContent").value,
        	contentsId: document.getElementById("contentsId").value
        }
        
        const url = "/api/contents/touristAreaDetail/" + contentsId + "/review";

        fetch(url, {
            method: "POST", // POST 요청
            body: JSON.stringify(review),  // comment 객체를 JSON 형식으로 변환하여 보냄
            headers: {
                "Content-Type": "application/json", // body에 담긴 객체의 type이 무엇인지 명시
            }
        }).then(response => {
            // fetch 응답 처리
            // 응답이 돌아오는 경우에 대한 처리

            // http 응답 코드에 따른 메세지 출력
            const msg = (response.ok) ? "댓글이 등록되었습니다." : "댓글 등록 실패";
            alert(msg);

            // 현재 페이지 새로고침
            window.location.reload();
        });
    });
    
	const updateReviewBtn = document.getElementById("updateReviewBtn");
	updateReviewBtn.addEventListener("click", () => {
		
		const review = {
			userId: document.getElementById("userId").value,
			reviewId: document.getElementById("reviewId").value,
			contentsId: document.getElementById("contentsId").value
		}
		
		const url = "/api/review/"+ reviewId;
		fetch(url, {
			method: "PATCH",
			body: JSON.stringify(review),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(response => {
			const msg = (response.ok) ? "댓글이 수정되었습니다.": "댓글 수정 실패";
            alert(msg);
            
            window.location.reload();
		});
	})
	
	
	const deleteReviewBtn = document.getElementById("deleteReviewBtn");
	
	deleteReviewBtn.addEventListener("click", () => {
		

			const reviewId = document.getElementById("reviewId").value;
			console.log(reviewId);

			
			const url = "/api/review/" + reviewId;
			console.log(url);
			
			fetch(url, {
				method: "DELETE",
				headers: {
					"Content-Type": "application/json"
				}
			}).then(response => {
				const msg = (response.ok) ? "댓글이 삭제되었습니다.": "댓글 삭제 실패";
	            alert(msg);
	            
	            window.location.reload();
			});
		})
		
	
</script>
</html>