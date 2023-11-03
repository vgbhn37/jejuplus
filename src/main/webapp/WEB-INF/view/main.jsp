<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<!-- main css -->

	
<link rel="stylesheet" href="/css/main.css" />

<!-- main -->
<main>

	<section class="hero">
		<h1>어디로 갈까요?</h1>
		<p>당신이 꿈꾸는 제주를 저렴하면서도 간편하고 풍성하게!</p>


	</section>

	<!-- 인기 투어 패키지 start -->
	<div class="main-packages-body">
		<!-- 왼쪽 화살표 버튼 -->
		<button class="slide-prev" id="slide-prev">
			<i class="bi bi-caret-left-fill"></i>
		</button>
		<section class="tour-packages">
			<div>
				<h2>맛집</h2>
			</div>

			<div class="carousel-container">
				<div class="packageContainer" id="multiple-items">
					<!-- 슬라이드 내용 -->
					<c:forEach var="restaurantDto" items="${restaurantDto}">
						<div class="package">
							<img src="${restaurantDto.imgPath}" alt="sample" />
							<h3>${restaurantDto.title}</h3>
							<p>${restaurantDto.tag}</p>
							<a href="#">자세히 보기</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
		<!-- 오른쪽 화살표 버튼 -->
		<button class="slide-next" id="slide-next">
			<i class="bi bi-caret-right-fill "></i>
		</button>
	</div>

	<!-- 인기 투어 패키지 start -->
	<div class="main-packages-body">
		<!-- 왼쪽 화살표 버튼 -->
		<button class="slide-prev" id="slide-prev-2">
			<i class="bi bi-caret-left-fill"></i>
		</button>
		<section class="tour-packages">
			<div>
				<h2>인기 관광지</h2>
			</div>

			<div class="carousel-container">
				<div class="packageContainer" id="multiple-items-2">
					<!-- 슬라이드 내용 -->
					<c:forEach var="palceDto" items="${palceDto}">
						<div class="package">
							<img src="${palceDto.imgPath}" alt="sample" />
							<h3>${palceDto.title}</h3>
							<p>${palceDto.tag}</p>
							<a href="#">자세히 보기</a>
						</div>
					</c:forEach>
				</div>
			</div>
		</section>
		<!-- 오른쪽 화살표 버튼 -->
		<button class="slide-next" id="slide-next-2">
			<i class="bi bi-caret-right-fill "></i>
		</button>
	</div>


	<!-- 인기 투어 패키지 end -->

	<!-- 인기 숙소 start -->

	<!-- 인기 숙소 end -->

	<!-- 맛집 추천 start -->

	<!-- 맛집 추천 end -->
</main>

<!-- js -->
<script>


$(document).ready(function() {
	// 맛집 슬라이더
    $('#multiple-items').slick({
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 2,
        autoplay : true,      // 자동 스크롤 사용 여부
        autoplaySpeed : 3000
    });
    $('#slide-prev').click(function() {
        $('#multiple-items').slick('slickPrev');
    });

    // "다음" 버튼 클릭 시 슬라이드 오른쪽으로 이동
    $('#slide-next').click(function() {
        $('#multiple-items').slick('slickNext');
    });
    
    
	// 관광지 슬라이더
    $('#multiple-items-2').slick({
        infinite: true,
        slidesToShow: 5,
        slidesToScroll: 2,
        autoplay : true,      // 자동 스크롤 사용 여부
        autoplaySpeed : 3000
    });
    $('#slide-prev-2').click(function() {
        $('#multiple-items-2').slick('slickPrev');
    });

    // "다음" 버튼 클릭 시 슬라이드 오른쪽으로 이동
    $('#slide-next-2').click(function() {
        $('#multiple-items-2').slick('slickNext');
    });
    
    
});




	</script>
<script src="../js/main.js"></script>
<%@ include file="/WEB-INF/view/footer.jsp"%>