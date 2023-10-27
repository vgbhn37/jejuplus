<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>제주 플러스</title>
<!-- headfoot css -->
<link rel="stylesheet" href="/css/headfoot.css" />



<!-- bootstrap -->

<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>


<!-- google font -->

<!-- fontawesome -->
<script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc"
	crossorigin="anonymous"></script>
</head>

<body>
	<!-- header -->
	<header>
		<nav>
			<div class="logo">
				<a href="#"> <img src="/images/logo_sample.jpg" alt="로고" />
				</a>
			</div>
		
			<ul class="menu">
				<li><a href="#">항공권</a></li>
				
				<c:choose>
				<c:when test="${principal == null}">
				<li><a href="/user/register">회원가입</a></li>
				<li><a href="/user/sign-in">로그인</a></li>
				</c:when>
				
				 <c:otherwise>
				 
			    	<input type="hidden" id="is-login" value='${principal ne null}'>
				<li><a href="/user/userUpdate/${principal.userId}">내정보</a></li>
				<li><a href="/user/logout">로그아웃</a></li>
				
				
				<c:if test="${principal.levelId >= 2 }">
			    	<div><a class="common-black-font" href="/admin/">관리자페이지</a></div>
			    	</c:if>
			    	</c:otherwise>
			    	</c:choose>
			    	
				<li><a href="#">여행 정보</a></li>
				<div class="app">
					<li><a href="#" id="toggleButton">날씨</a></li>
					<div class="sidebar">
						<div id="ww_6b8b1bb7b5dc0" v="1.3" loc="id"
							a='{"t":"horizontal","lang":"ko","sl_lpl":1,"ids":["wl7410"],"font":"Arial","sl_ics":"one_a","sl_sot":"celsius","cl_bkg":"image","cl_font":"#FFFFFF","cl_cloud":"#FFFFFF","cl_persp":"#81D4FA","cl_sun":"#FFC107","cl_moon":"#FFC107","cl_thund":"#FF5722"}'>
							More forecasts: <a href="https://sharpweather.com/ko/seoul/"
								id="ww_6b8b1bb7b5dc0_u" target="_blank">날씨 시간별 서울</a>
						</div>
						
						<script async
							src="https://app2.weatherwidget.org/js/?id=ww_6b8b1bb7b5dc0"></script>
					</div>
				</div>
			</ul>
			
		</nav>
	</header>