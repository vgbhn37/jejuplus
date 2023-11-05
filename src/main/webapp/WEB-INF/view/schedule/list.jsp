<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d5568461ac8305d5d4737b9523509aed"></script>
<link rel="stylesheet" href="/css/schedule/scheduleList.css" />

<!-- ------------------------------------------------------------- -->

<div class="container">
	<div class="list-header row">
		<div class="col-8">
			<h3>${principal.username} 님의 제주 여행</h3>
		</div>
		<div class="col-4 float-right">
			<button id="sche-modal" class="btn btn-primary">일정 등록</button>
		</div>
	</div>
	<div class="list-body">
		<c:forEach var="item" items="${scheduleList }">
			<div class="card">
				<div class="card-header"></div>
				<div class="card-body">
					<h5 class="card-title">
						<a href="/schedule/detail/edit/${ item.scheduleId}">${ item.title }</a>
					</h5>
					<p class="card-text">
						<img src="/images/schedule/list-calendar.png">
						${item.startDate } ~ ${item.endDate }
					</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<!-- 모달 창 -->
	<div id="myModal" class="modal">
		<div class="modal-content">
			<span class="close" id="closeModalButton" onclick="closeModal()">&times;</span>
			<h5>새 여행 일정 등록</h5>
			<input type="text" id="schedule-title"
				placeholder="이번 스케쥴의 이름을 입력해주세요."
				style="width: 457px; height: 50px; margin-bottom: 30px;" />
			<p>
				<img src='/images/schedule/calendar.png' id='sche-calendar'> <input
					type="text" id="sche-start-date" placeholder="시작일"
					style="width: 180px;" readonly> <input type="text"
					id="sche-end-date" placeholder="종료일"
					style="width: 180px; margin-left: 20px;" readonly>
			</p>
			<div class = "float-right">
			<button id="sche-add" class="btn btn-primary" style="width: 100px;">일정 추가</button>
			</div>
		</div>
	</div>
	<!-- 일정 추가 시 같이 서버에 넘길 로그인 된 회원 식별자값 -->
	<input type="hidden" id="user-id"  value="${principal.userId }">
</div>

<script src="/js/schedule/scheduleList.js"></script>
<script src="/js/schedule/scheduleListCalendar.js"></script>
<!-- ------------------------------------------------------------- -->
<%@ include file="/WEB-INF/view/footer.jsp"%>