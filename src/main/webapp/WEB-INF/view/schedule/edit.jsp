<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d5568461ac8305d5d4737b9523509aed"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/css/schedule/scheduleEdit.css" />
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
					대충 달력위치 <br> <br> <br> <br>
				</div>
			</div>
			<div id="map" class="map" style="width: 90%; height: 500px;"></div>


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
									onclick="changePage(event)" data-page="${pagination.endPage+1}">Next</a></li>
							</c:if>
						</ul>
						<!-- 페이지 관련 버튼을 클릭 시 같이 숨겨서 보낼 값 -->
						<input type="hidden" id="label" name="label" value="${label}">
					</div>
				</div>
			</div>


		</div>
		<div class="sche-right col-4 row">
			<div id="day" style="height: 40px;" class="col-6">
				<h3>DAY 1</h3>
			</div>
			<div class="col-6" style="height: 40px;">
				<button class="btn btn-orange" style="width: 58px; height: 38px;"
					onclick="sorting()">정렬</button>
				<button class="btn btn-primary" onclick="saveSchedule()">저장</button>
			</div>
			<div id="list-output">
				<div class=card>
					<div class="row">
						<div class="col-2">
							<div class=circle>1</div>
						</div>
						<div class="col-10">
							<div class="card-text">언양닭칼국수</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<script>
	
	const tabOutput = document.getElementById('tab-output'); // 맵 하단 navs를 클릭했을때 결과를 출력할 div
	
	let container = document.getElementById('map'); // 지도를 담을 영역의 DOM
	let markers = []; // 마커를 담을 배열
	let locations = []; // 선택한 장소 정보를 담을 배열
	let distances = []; // 거리 정보를 담을 배열
	let distance; // 하루 일정 동안 총 직선거리
	let polyline; // 지도에 표시할 선
	let isFirst = true; // 지도에 처음 선을 그리는지 여부
	
	// 리스트의 페이지 저장 (기본값 1)
	let allPage = 1;
	let attractionPage = 1;
	let accomodationPage = 1;
	let shoppingPage = 1;
	let restaurantPage = 1;
	let favoritePage = 1;
	let searchPage = 1;

	let options = { //지도를 생성할 때 필요한 기본 옵션
			center : new kakao.maps.LatLng(33.5056848111, 126.4960226206), //지도의 중심좌표.
			level : 8
		//지도의 레벨(확대, 축소 정도)
		};

	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	function showLocations(){
		locations.forEach(item=>{
			console.log(item.title);
		});
	}
	
	// 페이지 바꾸기
	function changePage(e){
		let label = document.getElementById('label').value;
	
		switch(label){
		case 'all' :
			allPage = e.target.getAttribute('data-page');
			break;
		case 'attraction' :
			attractionPage = e.target.getAttribute('data-page');
			break;
		case 'accomodation' :
			accomodationPage = e.target.getAttribute('data-page');
			break;
		case 'shopping' :
			shoppingPage = e.target.getAttribute('data-page');
			break;
		case 'restaurant' :
			restaurantPage = e.target.getAttribute('data-page');
			break;
		case 'favorite' :
			favoritePage = e.target.getAttribute('data-page');
			break;
		default :
			allPage = 1;
			break;
		}
		
		showList(label);
	}
	
	// 검색 창 띄우기
	function showSearchWindow(){
		
		fetch('/schedule/call-search')
		.then(res=>{
			if(!res.ok){
				throw new Error('네트워크 응답 오류');
			}
			return res.text();
		}).then(data=>{
			tabOutput.innerHTML = data;
		}).catch((error)=>{
			console.error(error);
		});
		
	}
	
	function setSearch(){
		let category = document.getElementById('search-select').value;
		let keyword = document.getElementById('search-input').value;
		
		searchList(category,keyword,1);
	}
	
	function searchList(page){
		
		const searchOutput = document.getElementById('search-output');
		
		let url = "/schedule/search-list?category="+ category + "&keyword=" keyword;
		
		fetch(url).then(res=>{
			if(!res.ok){
				throw new Error('네트워크 응답 오류');
			}
			return res.text();
		}).then(data=>{
			searchOutput.innerHTML = data;
		})
	}
	
	
	// 장소 리스트 보여주기
	function showList(label){
		
		let url = "";
		switch(label){
			case 'all' :
				url = "/schedule/show-list/all?page=" + allPage;
				break;
			case 'attraction' :
				url = "/schedule/show-list/attraction?page=" + attractionPage;
				break;
			case 'accomodation' :
				url = "/schedule/show-list/accomodation?page=" + accomodationPage;
				break;
			case 'shopping' :
				url = "/schedule/show-list/shopping?page=" + shoppingPage;
				break;
			case 'restaurant' :
				url = "/schedule/show-list/restaurant?page=" + restaurantPage;
				break;
			case 'favorite' :
				url = "/schedule/show-list/favorite?page=" + favoritePage;
				break;
			default :
				url = "/schedule/show-list/all?page=1";
				break;
		}
		
		fetch(url).then(res=>{
			if(!res.ok){
				throw new Error("네트워크 응답 오류");
			}
			return res.text();
		})
		.then(data=>{
			
			tabOutput.innerHTML = data;
			location.href="#";
		
		}).catch((error)=>{
			console.error(error);
		});
		
	}
	
	// 선택한 장소 리스트에 추가
	function addList(idValue,titleValue,region1Value,region2Value,labelValue,mapX,mapY){
		for (let i = 0 ; i < locations.length; i++) {
	        if (locations[i].id === idValue) {
	            alert("이미 일정에 있는 장소입니다.");
	            return;
	        }
	    }
		
		let item = {
			id : idValue,
			title : titleValue,
			region1 : region1Value,
			region2 : region2Value,
			label : labelValue,
			x : mapX,
			y : mapY
		};
		
		locations.push(item);
		printList();
		if(!isFirst){
			polyline.setMap(null);
		}
		drawLine(locations);
	}
	
	// 리스트 출력
	function printList(){
		const output = document.getElementById('list-output');
		output.innerHTML = '';
		locations.forEach(item=>{
			// 선택한 리스트 출력
			
			
		});
	}
	
	// 드래그 드랍시 발생하는 이벤트 
	
	
	
	// 지도에 선 그리기
	function drawLine(locations){
		let linePath = [];
		for (let i = 0; i < locations.length; i++) {
			linePath.push(new kakao.maps.LatLng(locations[i].y, locations[i].x));
		}
	
		// 지도에 표시할 선을 생성합니다
			polyline = new kakao.maps.Polyline({
			path : linePath, // 선을 구성하는 좌표배열 입니다
			strokeWeight : 3, // 선의 두께 입니다
			strokeColor : '#FFAE00', // 선의 색깔입니다
			strokeOpacity : 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
			strokeStyle : 'solid' // 선의 스타일입니다
		});
		
		if(isFirst){
			isFirst=false;
		}
		// 지도에 선을 표시합니다 
		polyline.setMap(map);
	}
	
	// 지도 중심 이동
	function panTo(location) {
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(location.y, location.x);
    
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);            
}        
	
	// 위도, 경도로 거리 계산
	function getDistanceFromLatLonInKm(lat1,lng1,lat2,lng2) {
	    function deg2rad(deg) {
	        return deg * (Math.PI/180)
	    }
	    var R = 6371; // Radius of the earth in km
	    var dLat = deg2rad(lat2-lat1);  // deg2rad below
	    var dLon = deg2rad(lng2-lng1);
	    var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
	    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    var d = R * c; // Distance in km
	    return d;
	}
	
	// 선택한 아이템들로 동선 짜기
	function sorting() {
		
		if(confirm('일정 내 첫번째 장소를 기준으로 일정의 순서가 변경됩니다.')){
			distances.length = 0; // distances 안에 있는 값을 비움
		    let tempArray = [...locations]; // locations를 깊은복사 한 배열 (배열의 원소를 빼주면서 계산하기 때문)
		    let sortedArray = []; // 정렬된 location들이 담길 배열
		    
		    let criteria = tempArray[0]; // 최단거리 찾기의 기준이 될 경위도 (최초는 첫 번째 장소가 된다.)
		    sortedArray.push(criteria);	// locations의 첫 번째 값을 기준으로 동선을 작성하기 때문에 첫 번째 값은 정렬된 배열의 첫 번째 값으로 고정
		    tempArray.splice(0,1); // 최단거리을 찾을 때 기준은 빠져야하기 때문에 제거
		    
		    while (tempArray.length > 0) {
		        
		        let minDistance = Number.MAX_VALUE; // 최단 거리
		        let minIndex = -1; // 최단 거리값을 가진 객체의 index
				
		        // 1. 기준의 경위도와 배열 안 요소의 경위도와 비교하여 거리를 구한다.
		        // 2. 배열 길이만큼 for 반복문을 돌며 (직선 기준) 최단 거리일 경우, 최단 거리와 그 인덱스를 저장한다.
		        // 3. for 반복문이 끝나면 최단거리를 저장하고 최단거리를 도출하던 location을 기준으로 정하고 tempArray에서 삭제한다. (기준을 제외하고 거리를 구해야하므로) 
		        for (let i = 0; i < tempArray.length; i++) {
		            const distance = getDistanceFromLatLonInKm(criteria.y, criteria.x, tempArray[i].y, tempArray[i].x); // 1
		            // 2
		            if (distance < minDistance) {
		                minDistance = distance;
		                minIndex = i;
		            }
		        }
		        // 3
		        if (minIndex !== -1) {
		            sortedArray.push(tempArray[minIndex]);
		            criteria = tempArray[minIndex];
		            tempArray.splice(minIndex, 1);
		        } else {
		            break;
		        }
		    }
			
		    for(let k = 0 ; k < sortedArray.length; k++){
		    	console.log(sortedArray[k]);
		    }
		    
			if(!isFirst){
				polyline.setMap(null);
			}
		    drawLine(sortedArray); // 정렬된 배열으로 선을 그림
		    panTo(sortedArray[0]); // 일정의 시작지점을 지도의 중앙으로
		    locations=[...sortedArray]; // locations에 정렬된 Array복사
		 
		}
		
	}
		
	// 일정 저장
	function saveSchedule(){
		alert('저장');
	}
	
	
	
	
</script>

<!-- ------------------------------------------------------------- -->
<%@ include file="/WEB-INF/view/footer.jsp"%>