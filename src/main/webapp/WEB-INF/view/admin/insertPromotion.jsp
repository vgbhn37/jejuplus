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
.button-container {
        text-align: right; /* 텍스트를 오른쪽으로 정렬 */
        margin-top: 10px; /* 필요한 경우 상단 여백 추가 */
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
			<div class="menu-sidebar__content js-scrollbar1 ps"
				style="background-color: #E2E2E2;">
				<nav class="navbar-sidebar">
					<ul class="list-unstyled navbar__list">

						<li class="active"><a href="/admin/adminUserManagement">
								<i class="fas fa-table"></i>User
						</a></li>
						<li class="active"><a href="/admin/adminPromotionManagement">
								<i class="fas fa-table"></i>Promotion
						</a></li>
						<li class="active"><a href="/admin/insertPromotion"> <i
								class="fas fa-table"></i>Promotion Write
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

							<h1>광고 작성 페이지</h1>
						</div>
					</div>
				</div>
			</header>
			<!-- END HEADER DESKTOP-->
			<!-- MAIN CONTENT-->
			<div class="main-content" style="background-color: white;">
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
								<h3 class="title-5 m-b-35">Writer Promation</h3>

								<div>

									<form action="/admin/insertPromotion" method="post"
										enctype="multipart/form-data" id="myForm">
										<div class="form-group">
											<label for="title">제목:</label> <input type="text"
												name="title" id="titleField" class="form-control"
												placeholder="제목을 입력하세요">
										</div>
										<div class="form-group">
											<label for="introduce">소개:</label> <input type="text"
												name="introduce" id="introduceField" class="form-control"
												placeholder="간단한 소개글을 입력하세요(45자 이내)">
										</div>
										<div class="form-group">
											<label for="content">본문:</label>
											<textarea id="content" name="content" rows="6"
												class="form-control" placeholder="내용을 입력하세요"></textarea>
										</div>
										<div class="form-group">
											<label for="images">대표사진 업로드:</label> <input type="file"
												name="images" id="imageField" multiple
												class="form-control-file">
										</div>
										<div class="form-group">
											<label for="images">이미지 업로드:</label> <input type="file"
												name="images" id="imageField" multiple
												class="form-control-file">
										</div>
										<div class="form-group">
											<label for="images">이미지 업로드:</label> <input type="file"
												name="images" id="imageField" multiple
												class="form-control-file">
										</div>
										<div class="button-container">
											<button type="submit" class="btn btn-primary">올리기</button>
										</div>
									</form>





								</div>





							</div>
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

	<script>
document.querySelector('#myForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const formData = new FormData(this);

    fetch('/admin/insertPromotion', {
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (response.ok) {
            // Form submission was successful
            alert('광고가 업로드 되었습니다.');
            window.location.href='/admin/adminPromotionManagement';
        } else {
            // Form submission failed
            alert('작성하지 않은 곳이 있는지 다시 한번 확인해주세요');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
});


</script>


</body>
</html>