<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d5568461ac8305d5d4737b9523509aed"></script>
<link rel="stylesheet" href="/css/schedule/scheduleDetailEdit.css" />
<!-- ------------------------------------------------------------- -->


<div class="container">
	<div class="row">
		<div class="sche-left col-8">
			<div class="row">
				<div class="col-7">
					<h4>${schedule.title }</h4>
					<h5 style="color: #767676;">${schedule.startDate }~
						${schedule.endDate }</h5>
				</div>
				<div class="col-5">
					<label for="date">일정의 날짜를 선택해주세요! <input type="date"
						id="date" max="${schedule.endDate }" min="${schedule.startDate }"
						value="${schedule.startDate }" onchange="scheduleDetailEdit.changeDate()">
					</label>
				</div>
			</div>
			<div id="map" class="map" style="width: 90%; height: 500px;"></div>


			<ul class="nav justify-content-between">
				<li class="nav-item"><a class="nav-link"
					onclick="scheduleDetailEdit.printContentsList('all')">전체</a></li>
				<li class="nav-item"><a class="nav-link"
					onclick="scheduleDetailEdit.printContentsList('attraction')">관광지</a></li>
				<li class="nav-item"><a class="nav-link"
					onclick="scheduleDetailEdit.printContentsList('accomodation')">숙박</a></li>
				<li class="nav-item"><a class="nav-link"
					onclick="scheduleDetailEdit.printContentsList('shopping')">쇼핑</a></li>
				<li class="nav-item"><a class="nav-link"
					onclick="scheduleDetailEdit.printContentsList('restaurant')">음식점</a></li>
				<li class="nav-item"><a class="nav-link"
					onclick="scheduleDetailEdit.printContentsList('favorite')">찜한 곳</a></li>
				<li class="nav-item"><a class="nav-link"
					onclick="scheduleDetailEdit.printSearchWindow()">검색</a></li>
			</ul>
			<div id="tab-output">
			<%-- 	<c:forEach var="item" items="${list}">
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
									onclick="changePage(event)" data-page="${pagination.endPage+1}">Next</a></li>
							</c:if>
						</ul>
						<!-- 페이지 관련 버튼을 클릭 시 같이 숨겨서 보낼 값 -->
						<input type="hidden" id="label" name="label" value="${label}">
					</div>
				</div> --%>
			</div>


		</div>
		<div class="sche-right col-4">
			<div class="list-header row">
				<div id="day" style="height: 40px;" class="col-6">
					<span class="h3">DAY&nbsp;</span><span id="schedule-date"
						class="h3">1</span>
				</div>
				<div class="col-6" style="height: 40px;">
					<button id="sorting-btn" class="btn btn-orange" style="width: 58px; height: 38px;">정렬</button>
					<button id="saving-btn" class="btn btn-primary" onclick="saveSchedule()">저장</button>
				</div>
			</div>
			<div class=notice>
				※ 표시되는 거리는 직선거리 기준입니다.<br> ※ 거리를 클릭하시면 길찾기 페이지가 새 창으로 열립니다.
			</div>
			<div id="list-output" class="float-right">
				<p>정해진 일정이 없어요. 일정을 추가해보세요!</p>
			</div>
		</div>
	</div>
	<!-- 모달 창 -->
	<div id="myModal" class="modal">
		<div class="modal-content">
			<span class="close" id="closeModalButton" onclick="closeModal()">&times;</span>
			<h3 id="contents-title"></h3>
			<textarea id="contents-memo" placeholder="메모할 내용을 적어주세요."></textarea>
			<input type="hidden" id="contents-index" value="">
		</div>
	</div>
</div>



<script src = '/js/schedule/scheduleDetailEditRefactor.js'></script>



<!-- ------------------------------------------------------------- -->
<%@ include file="/WEB-INF/view/footer.jsp"%>