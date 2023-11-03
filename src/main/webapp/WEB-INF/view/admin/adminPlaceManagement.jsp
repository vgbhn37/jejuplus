<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="author" content="Hau Nguyen">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Tables</title>

<!-- Fontfaces CSS-->
<link href="/css/font-face.css" rel="stylesheet" media="all">
<link href="/vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link href="/vendor/font-awesome-5/css/fontawesome-all.min.css"
	rel="stylesheet" media="all">
<link href="/vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">

<!-- Bootstrap CSS-->
<link href="/vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet"
	media="all">

<!-- Vendor CSS-->
<link href="/vendor/animsition/animsition.min.css" rel="stylesheet"
	media="all">
<link
	href="/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css"
	rel="stylesheet" media="all">
<link href="/vendor/wow/animate.css" rel="stylesheet" media="all">
<link href="/vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet"
	media="all">
<link href="/vendor/slick/slick.css" rel="stylesheet" media="all">
<link href="/vendor/select2/select2.min.css" rel="stylesheet"
	media="all">
<link href="/vendor/perfect-scrollbar/perfect-scrollbar.css"
	rel="stylesheet" media="all">

<!-- Main CSS-->
<link href="/css/theme.css" rel="stylesheet" media="all">
<!-- Jquery JS-->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.js"></script>
<style type="text/css">/* Chart.js */
@
-webkit-keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
@
keyframes chartjs-render-animation {
	from {opacity: 0.99
}

to {
	opacity: 1
}

}
.chartjs-render-monitor {
	-webkit-animation: chartjs-render-animation 0.001s;
	animation: chartjs-render-animation 0.001s;
}

.pagination {
	margin-top: 20px;
	text-align: center;
}

.pagination a {
	text-decoration: none;
	display: inline-block;
	padding: 10px 15px;
	margin: 5px;
	background-color: #007BFF;
	color: #fff;
	border-radius: 5px;
}

.pagination span {
	display: inline-block;
	padding: 10px 15px;
	margin: 5px;
	background-color: #ccc;
	color: #000;
	border-radius: 5px;
}
</style>
</head>
<body class="animsition" style="animation-duration: 900ms; opacity: 1;">
	<div class="page-wrapper">
		<!-- HEADER MOBILE-->
		<header class="header-mobile d-block d-lg-none">
			<div class="header-mobile__bar">
				<div class="container-fluid">
					<div class="header-mobile-inner">
						<a class="logo" href="index.html"> <img
							src="/images/icon/logo.png" alt="JejuPlusAdmin">
						</a>
						<button class="hamburger hamburger--slider" type="button">
							<span class="hamburger-box"> <span class="hamburger-inner"></span>
							</span>
						</button>
					</div>
				</div>
			</div>
			<nav class="navbar-mobile">
				<div class="container-fluid">
					<ul class="navbar-mobile__list list-unstyled">
						<li class="has-sub">
						<li><a href="table.html"> <i class="fas fa-table"></i>Tables
						</a></li>

					</ul>
					</li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- END HEADER MOBILE-->

		<!-- MENU SIDEBAR-->
		<aside class="menu-sidebar d-none d-lg-block">
			<div class="logo">
				<a href="#"> <img src="/images/icon/logo.png" alt="Cool Admin">
				</a>
			</div>
			<div class="menu-sidebar__content js-scrollbar1 ps">
				<nav class="navbar-sidebar">
					<ul class="list-unstyled navbar__list">

						<li class="active"><a href="/admin/adminUserManagement">
								<i class="fas fa-table"></i>User
						</a></li>
						<li class="active"><a href="/admin/adminPlaceManagement">
								<i class="fas fa-table"></i>Place
						</a></li>

					</ul>
				</nav>
				<div class="ps__rail-x" style="left: 0px; bottom: 0px;">
					<div class="ps__thumb-x" tabindex="0"
						style="left: 0px; width: 0px;"></div>
				</div>
				<div class="ps__rail-y" style="top: 0px; right: 0px;">
					<div class="ps__thumb-y" tabindex="0"
						style="top: 0px; height: 0px;"></div>
				</div>
			</div>
		</aside>
		<!-- END MENU SIDEBAR-->

		<!-- PAGE CONTAINER-->
		<div class="page-container">
			<!-- HEADER DESKTOP-->
			<header class="header-desktop">
				<div class="section__content section__content--p30">
					<div class="container-fluid">
						<div class="header-wrap">
							<p>Jeju Plus</p>
						</div>
					</div>
				</div>
			</header>
			<!-- END HEADER DESKTOP-->
			<!-- MAIN CONTENT-->
			<div class="main-content">
				<div class="section__content section__content--p30">
					<div class="container-fluid">

						<div class="row">
							<div class="col-lg-6">
								<!-- USER DATA-->

								<!-- END USER DATA-->
							</div>
							<div class="col-lg-6">
								<!-- TOP CAMPAIGN-->

								<!--  END TOP CAMPAIGN-->
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<!-- DATA TABLE -->
								<h3 class="title-5 m-b-35">User table</h3>

								<!--
								<form class="form-header" action="" method="POST">

									<input class="" type="text" name="search" placeholder="">
									<button class="au-btn--submit" type="submit">
										<i class="zmdi zmdi-search"></i>
									</button>

								</form>
								-->

								<div class="table-responsive table-responsive-data2">
									<table class="table table-data2">
										<ul class="nav justify-content-between">
											<li class="nav-item"><a class="nav-link"
												onclick="showList('all')">전체</a></li>
											<li class="nav-item"><a class="nav-link"
												onclick="showList('attraction')">관광지</a></li>
											<li class="nav-item"><a class="nav-link"
												onclick="showList('accomodation')">숙박</a></li>
											<li class="nav-item"><a class="nav-link"
												onclick="showList('shopping')">쇼핑</a></li>
											<li class="nav-item"><a class="nav-link"
												onclick="showList('restaurant')">음식점</a></li>
											<li class="nav-item"><a class="nav-link"
												onclick="showList('favorite')">찜한 곳</a></li>
											<li class="nav-item"><a class="nav-link"
												onclick="showSearchWindow()">검색</a></li>
										</ul>

										<div id="tab-output">
											<c:forEach var="item" items="${list}">
												<div class="card flex-row">
													<div class="card-header border-0">
														<img src="${item.thumbnailPath}" class="thumbnail"
															onerror="this.src='/images/NoImage.jpg'">
													</div>
													<div class="card-body p-3">
														<h4 class="card-title">${item.title}</h4>
														<p class="card-text">${item.region1 }>${item.region2}</p>
														<p class="card-text tag">${item.tag }</p>
														<button class="btn btn-orange float-right"
															onclick="addList(${item.contentsId},'${item.title}','${item.region1}','${item.region2}','${item.contentsLabel}',${item.longitude},${item.latitude})">일정추가</button>
													</div>
												</div>
											</c:forEach>

											<div class="paging">
												<div class="text-center clearfix">
													<ul class="pagination" id="pagination">
														<c:if test="${pagination.prev}">
															<li class="page-item"><a class="page-link"
																onclick="changePage(event)"
																data-page="${pagination.beginPage-1}">Prev</a></li>
														</c:if>
														<c:forEach var="num" begin="${pagination.beginPage}"
															end="${pagination.endPage}">
															<li
																class="${pagination.paging.page == num ? 'page-item active' : ''}"><a
																class="page-link" onclick="changePage(event)"
																data-page="${num}">${num}</a></li>
														</c:forEach>

														<c:if test="${pagination.next}">
															<li class="page-item"><a class="page-link"
																onclick="changePage(event)"
																data-page="${pagination.endPage+1}">Next</a></li>
														</c:if>
													</ul>
													<!-- 페이지 관련 버튼을 클릭 시 같이 숨겨서 보낼 값 -->
													<input type="hidden" id="label" name="label"
														value="${label}">
												</div>
											</div>
										</div>



										</tbody>
									</table>
								</div>



								<!-- END DATA TABLE -->
							</div>
						</div>

						<div class="pagination" id="pagination"
							style="justify-content: center;">
							<c:if test="${page > 1}">
								<a href="?page=${page - 1}">이전</a>
							</c:if>

							<c:forEach begin="1" end="${totalPages}" var="pageNumber">
								<c:choose>
									<c:when test="${pageNumber == page}">
										<span>${pageNumber}</span>
									</c:when>
									<c:otherwise>
										<a href="?page=${pageNumber}">${pageNumber}</a>
									</c:otherwise>
								</c:choose>
							</c:forEach>

							<c:if test="${page < totalPages}">
								<a href="?page=${page + 1}">다음</a>
							</c:if>
						</div>

						<div class="row">
							<div class="col-md-12">
								<div class="copyright">
									<p>
										Copyright © 2018 Colorlib. All rights reserved. Template by <a
											href="https://colorlib.com">Colorlib</a>.
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(document)
				.ready(
						function() {
							// Handle the "change-level" button click
							$(".change-level")
									.on(
											"click",
											function() {
												// Get the user's level ID
												var username = $(this).closest(
														"tr").find(
														"#usernameInput").val();
												var levelId = $(this).data(
														"target").split("-")[1];

												// Show the modal for the specific user
												$(
														'#changeLevelModal-'
																+ username)
														.modal('show');

												// Set the username in the modal for reference
												$(
														'#changeLevelModal-'
																+ levelId)
														.find("#usernameInput")
														.val(username);

												// Call the changeUserLevel function with the retrieved values
											});
						});

		function changeUserLevel(levelId, newLevelId, username) {
			// Get the existing levelId and username
			var existingLevelId = levelId; // 이제는 사용하지 않아도 될 수 있습니다.

			// Make an AJAX request to update the user's level
			$.ajax({
				type : 'POST',
				url : '/admin/updateUserLevel', // 서버의 업데이트 엔드포인트에 맞게 업데이트해야 합니다.
				data : {
					levelId : existingLevelId, // 필요하다면 기존 레벨도 전달
					newLevelId : newLevelId,
					username : username
				},
				success : function(data) {
					console.log(username);
					console.log(newLevelId);
					console.log(data.result);
					if (data.result === "success") {
						alert("사용자 권한이 변경되었습니다.");
						// 모달 창 내에 새로운 levelId를 업데이트합니다.
						$("#userLevel-" + username).text(newLevelId);
						// 모달 창을 닫습니다.
						$("#changeLevelModal-" + username).modal("hide");
					} else {
						alert("권한 변경에 실패했습니다.");
					}
				},
				error : function(xhr) {
					console.log(username);
					alert("서버에서 오류가 발생했습니다.");
					$("#changeLevelModal-" + username).modal("hide");
				}
			});
		}

		// 삭제
		$(document)
				.ready(
						function() {
							// 버튼 클릭 이벤트 처리
							$(".delete-user")
									.on(
											"click",
											function() {
												var username = $(this).closest(
														"tr").find(
														"#usernameInput").val();
												console.log(username);

												if (confirm("삭제하시겠습니까?")) {
													$
															.ajax({
																type : 'POST',
																url : '/admin/adminUserDelete/'
																		+ username,
																data : {
																	"username" : username
																},
																dataType : 'json',
																success : function(
																		data) {
																	if (data
																			&& data.result === "success") {
																		alert("사용자 정보가 삭제되었습니다.");

																	} else {
																		alert("서버에서 빈 응답이 돌아왔습니다. 삭제에 실패했습니다.");
																	}
																},
																error : function(
																		xhr) {
																	alert("삭제에 실패했습니다.");
																}
															});
												} else {
													// 사용자가 취소한 경우 아무 작업도 필요하지 않습니다.
												}
											});
						});
	</script>



	<!-- Bootstrap JS-->
	<script src="/vendor/bootstrap-4.1/popper.min.js"></script>
	<script src="/vendor/bootstrap-4.1/bootstrap.min.js"></script>
	<!-- Vendor JS       -->
	<script src="/vendor/slick/slick.min.js">
		
	</script>
	<script src="/vendor/wow/wow.min.js"></script>
	<script src="/vendor/animsition/animsition.min.js"></script>
	<script
		src="/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
		
	</script>
	<script src="/vendor/counter-up/jquery.waypoints.min.js"></script>
	<script src="/vendor/counter-up/jquery.counterup.min.js">
		
	</script>
	<script src="/vendor/circle-progress/circle-progress.min.js"></script>
	<script src="/vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
	<script src="/vendor/chartjs/Chart.bundle.min.js"></script>
	<script src="/vendor/select2/select2.min.js">
    </script>

	<!-- Main JS-->
	<script src="/js/main.js"></script>

</body>
</html>