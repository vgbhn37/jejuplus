<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>제주 플러스</title>




<!-- bootstrap -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<script type="text/javascript"
	src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<!-- google font -->

<!-- fontawesome -->
<script defer
	src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc"
	crossorigin="anonymous"></script>

<!-- 슬라이드토글 -->

<!-- headfoot css -->
<link rel="stylesheet" href="/css/headfoot.css" />
<!-- headfoot js -->
<script src="../js/headfoot.js"></script>
</head>


<body>
	<!-- header -->
	<header>
		<nav>
			<c:choose>
					<c:when test="${isMainPage}">
						<div class="logo">
							<a href="/main"> <img src="/images/제주플러스2.png" alt="로고" /></a>
						</div>
					</c:when>
					
					<c:otherwise>
						<div class="logo">				
							<a href="/main"> <img src="/images/제주플러스.png" alt="로고" /></a>
						</div>
					</c:otherwise>
			</c:choose>
			<ul class="menu">
				<li><a href="/air/index">항공권</a></li>
				<li><a href="#" id="myPageLink">마이페이지</a></li>
    
				<div id="hiddenMenu" style="display: none;">
					<c:choose>
						<c:when test="${principal == null}">
							<li><a href="/user/register" >회원가입</a></li>
							<li><a href="/user/sign-in" >로그인</a></li>
						</c:when>
						
						<c:otherwise>
							<input type="hidden" id="is-login" value='${principal ne null}'>
							<li><a href="/user/userUpdate/${principal.userId}">내 정보</a></li>
							<li><a href="/user/orderList/">구매 내역</a></li>
							<li><a href="/user/logout" >로그아웃</a></li>
							<c:if test="${principal.levelId >= 2}">
								<li><a class="common-black-font" href="/admin/adminUserManagement" >관리자</a></li>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
				
				<li><a href="/contents/touristAreaList">여행 정보</a></li>
				<li><a href="#" id="toggleButton">날씨</a></li>
				<div class="app">
					<div class="sidebar">
						<div id="ww_6b8b1bb7b5dc0" v="1.3" loc="id"
							a='{"t":"horizontal","lang":"ko","sl_lpl":1,"ids":["wl7410"],"font":"Arial","sl_ics":"one_a","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722"}'>
							More forecasts: <a href="https://sharpweather.com/ko/seoul/"
								id="ww_6b8b1bb7b5dc0_u" target="_blank">날씨 시간별 서울</a>
						</div>

						<script async
							src="https://app2.weatherwidget.org/js/?id=ww_6b8b1bb7b5dc0"></script>
<script>
    var myPageLink = document.getElementById("myPageLink");
    var hiddenMenu = document.getElementById("hiddenMenu");

    myPageLink.addEventListener("click", function () {
        if (hiddenMenu.style.display === "none") {
            hiddenMenu.style.display = "block";
        } else {
            hiddenMenu.style.display = "none";
        }
    });
</script>


					</div>
				</div>
			</ul>

		</nav>
	</header>