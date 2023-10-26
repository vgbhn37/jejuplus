<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/header.jsp"%>
<link rel="stylesheet" href="../../css/schedule/scheduleEdit.css" />
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d5568461ac8305d5d4737b9523509aed"></script>
<!-- ------------------------------------------------------------- -->


<div class="sche-container">
	<div class="sche-left">
		<div id="map" class="map" style="width: 700px; height: 560px;"></div>
		<button onclick="sorting()">딸깍</button>
	</div>
	<div class="sche-right">
		<div>
			CARDVIEW LIST
		</div>
	</div>
</div>



<script>

	let container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	let markers = [];
	let locations = new Array();
	let item1 = {
		x : 126.4947726967,
		y : 33.5071207433
	};
	let item2 = {
		x : 126.3576659720,
		y : 33.3481028325
	};
	let item3 = {
		x : 126.2353395628,
		y : 33.3898379598
	};
	let item4 = {
		x : 126.2686822387,
		y : 33.3390996509
	};
	let item5 = {
		x : 126.4286648899,
		y : 33.4924157657
	};

	locations.push(item1);
	locations.push(item2);
	locations.push(item3);
	locations.push(item4);
	locations.push(item5);

	let options = { //지도를 생성할 때 필요한 기본 옵션
		center : new kakao.maps.LatLng(33.5056848111, 126.4960226206), //지도의 중심좌표.
		level : 8
	//지도의 레벨(확대, 축소 정도)
	};

	let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	// 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다

	let linePath = [];
	for (let i = 0; i < locations.length; i++) {
		linePath.push(new kakao.maps.LatLng(locations[i].y, locations[i].x));
	}

	// 지도에 표시할 선을 생성합니다
	let polyline = new kakao.maps.Polyline({
		path : linePath, // 선을 구성하는 좌표배열 입니다
		strokeWeight : 3, // 선의 두께 입니다
		strokeColor : '#FFAE00', // 선의 색깔입니다
		strokeOpacity : 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		strokeStyle : 'solid' // 선의 스타일입니다
	});

	// 지도에 선을 표시합니다 
	polyline.setMap(map);
	
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
	
	// 정렬
	function sorting() {
	    let tempArray = [...locations];
	    let sortedArray = [];
	    let criteria = tempArray[0];
	    sortedArray.push(criteria);
	    tempArray.splice(0,1);
	    
	    while (tempArray.length > 0) {
	        
	        let minDistance = Number.MAX_VALUE;
	        let minIndex = -1;

	        for (let i = 0; i < tempArray.length; i++) {
	            const distance = getDistanceFromLatLonInKm(criteria.y, criteria.x, tempArray[i].y, tempArray[i].x);
	            if (distance < minDistance) {
	                minDistance = distance;
	                minIndex = i;
	            }
	        }
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
	    
	  
	    
	   let newline = new kakao.maps.Polyline({
			path : sortedArray, // 선을 구성하는 좌표배열 입니다
			strokeWeight : 3, // 선의 두께 입니다
			strokeColor : '#FFAE00', // 선의 색깔입니다
			strokeOpacity : 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
			strokeStyle : 'solid' // 선의 스타일입니다
		});
	    
	    polyline.setMap(null);
	    newline.setMap(map);
	}
	
	
</script>

<!-- ------------------------------------------------------------- -->
<%@ include file="/WEB-INF/view/footer.jsp"%>