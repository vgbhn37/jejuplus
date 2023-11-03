/**
 * 
 */


/* 지도 */
let container = document.getElementById('map'); // 지도를 담을 영역의 DOM
let locations = []; // 선택한 장소 정보를 담을 배열
let distances = []; // 거리 정보를 담을 배열
let markers = []; // 마커 배열
let distance; // 하루 일정 동안 총 직선거리
let polyline; // 지도에 표시할 선
let isFirst = true; // 지도에 처음 선을 그리는지 여부 (컨텐츠를 처음 일정에 담았는 지)
let options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(33.5056848111, 126.4960226206), //지도의 중심좌표.
	level: 8
	//지도의 레벨(확대, 축소 정도)
};

let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

/* 검색 */
let category; // 검색 카테고리
let keyword; // 검색 키워드

/* 페이지네이션 */
const tabOutput = document.getElementById('tab-output'); // 맵 하단 navs를 클릭했을때 결과를 출력할 div
// 리스트의 페이지 저장 (기본값 1)
let allPage = 1;
let attractionPage = 1;
let accomodationPage = 1;
let shoppingPage = 1;
let restaurantPage = 1;
let favoritePage = 1;
let searchPage = 1;



/* 페이지 저장 및 변경(탭 내 이동 및 페이지 이동 시) */
function changePage(e) {
	let label = document.getElementById('label').value;

	switch (label) {
		case 'all':
			allPage = e.target.getAttribute('data-page');
			break;
		case 'attraction':
			attractionPage = e.target.getAttribute('data-page');
			break;
		case 'accomodation':
			accomodationPage = e.target.getAttribute('data-page');
			break;
		case 'shopping':
			shoppingPage = e.target.getAttribute('data-page');
			break;
		case 'restaurant':
			restaurantPage = e.target.getAttribute('data-page');
			break;
		case 'favorite':
			favoritePage = e.target.getAttribute('data-page');
			break;
		default:
			allPage = 1;
			break;
	}

	showList(label);
}

/* 컨텐츠 리스트 보여주기 */
function showList(label) {

	let url = "";
	switch (label) {
		case 'all':
			url = "/schedule/show-list/all?page=" + allPage;
			break;
		case 'attraction':
			url = "/schedule/show-list/attraction?page=" + attractionPage;
			break;
		case 'accomodation':
			url = "/schedule/show-list/accomodation?page=" + accomodationPage;
			break;
		case 'shopping':
			url = "/schedule/show-list/shopping?page=" + shoppingPage;
			break;
		case 'restaurant':
			url = "/schedule/show-list/restaurant?page=" + restaurantPage;
			break;
		case 'favorite':
			url = "/schedule/show-list/favorite?page=" + favoritePage;
			break;
		default:
			url = "/schedule/show-list/all?page=1";
			break;
	}

	fetch(url).then(res => {
		if (!res.ok) {
			throw new Error("네트워크 응답 오류");
		}
		return res.text();
	})
		.then(data => {

			tabOutput.innerHTML = data;
			//location.href="#";

		}).catch((error) => {
			console.error(error);
		});

}

/* 검색 창 띄우기 */
function showSearchWindow() {

	fetch('/schedule/call-search')
		.then(res => {
			if (!res.ok) {
				throw new Error('네트워크 응답 오류');
			}
			return res.text();
		}).then(data => {
			tabOutput.innerHTML = data;
		}).catch((error) => {
			console.error(error);
		});

}

/* 검색 카테고리와 검색어 저장 (검색 탭 내 페이지 이동 시 카테고리와 검색어가 초기화되지 않게) */
function setSearch() {
	category = document.getElementById('search-select').value;
	keyword = document.getElementById('search-input').value;

	searchList(1);
}

/* 검색 결과 출력 */
function searchList(page) {

	const searchOutput = document.getElementById('search-output');

	let url = "/schedule/search-list?category=" + category + "&keyword=" + keyword + "&page=" + page;

	fetch(url).then(res => {
		if (!res.ok) {
			throw new Error('네트워크 응답 오류');
		}
		return res.text();
	}).then(data => {
		searchOutput.innerHTML = data;
	})
}


/* 선택한 컨텐츠 리스트에 추가 */
function addList(idValue, titleValue, region1Value, region2Value, labelValue, mapX, mapY) {
	for (let i = 0; i < locations.length; i++) {
		if (locations[i].id === idValue) {
			alert("이미 일정에 있는 장소입니다.");
			return;
		}
	}

	let item = {
		id: idValue,
		title: titleValue,
		region1: region1Value,
		region2: region2Value,
		label: labelValue,
		memo: "",
		x: mapX,
		y: mapY
	};

	locations.push(item);
	printList();
	if (!isFirst) {
		polyline.setMap(null);
	}
	drawLine(locations);
}

/* 일정 리스트 출력 */
function printList() {
	const output = document.getElementById('list-output');
	output.innerHTML = '';
	locations.forEach((item, index) => {
		const itemDiv = document.createElement('div');
		itemDiv.classList.add('list-card');
		itemDiv.setAttribute("draggable", true);
		itemDiv.addEventListener('dragstart', handleDragStart);
		itemDiv.addEventListener('dragenter', handleDragEnter);
		itemDiv.addEventListener('dragover', handleDragOver);
		itemDiv.addEventListener('dragleave', handleDragLeave);
		itemDiv.addEventListener('drop', handleDrop);
		itemDiv.addEventListener('dragend', handleDragEnd);
		output.appendChild(itemDiv);

		const rowDiv = document.createElement('div');
		rowDiv.classList.add('row');
		itemDiv.appendChild(rowDiv);

		const colDiv2 = document.createElement('div');
		colDiv2.classList.add('col-2');
		rowDiv.appendChild(colDiv2);

		const circle = document.createElement('div');
		circle.classList.add('circle');
		circle.textContent = index + 1;
		colDiv2.appendChild(circle);

		const colDiv7 = document.createElement('div');
		colDiv7.classList.add('col-7');
		colDiv7.textContent = item.title;
		rowDiv.appendChild(colDiv7);

		const colDiv3 = document.createElement('div');
		colDiv3.classList.add('col-3');
		rowDiv.appendChild(colDiv3);

		const memoIcon = document.createElement('img');
		memoIcon.src = '/images/schedule/writing.png';
		memoIcon.classList.add('item-btn');
		memoIcon.addEventListener('click', () => {
			openModal(item.title, index);
		});
		colDiv3.appendChild(memoIcon);

		const binIcon = document.createElement('img');
		binIcon.src = '/images/schedule/bin.png';
		binIcon.classList.add('item-btn');
		binIcon.addEventListener('click', (index) => {
			removeList(index);
		});
		colDiv3.appendChild(binIcon);

		const itemIdData = document.createElement('input');
		itemIdData.setAttribute('type', 'hidden');
		itemIdData.value = item.id;
		itemDiv.appendChild(itemIdData);


		if (index > 0) {
			const distance = document.createElement('div');
			distance.classList.add('distance');
			const distanceATag = document.createElement('a');
			distanceATag.setAttribute("href", "http://map.naver.com/index.nhn?slng=" + locations[index - 1].x + "&slat=" + locations[index - 1].y +
				"&stext=" + locations[index - 1].title + "&elng=" + item.x + "&elat=" + item.y + "&pathType=0&showMap=true&etext=" + item.title + "&menu=route");
			distanceATag.setAttribute("target", "_blank");
			distanceATag.textContent = getDistanceFromLatLonInKm(locations[index - 1].y, locations[index - 1].x, item.y, item.x).toFixed(2) + ' km';
			distance.appendChild(distanceATag);
			itemDiv.appendChild(distance);
		}

	});
}

/* 드래그 드랍 시 발생하는 이벤트 */

let dragSrcEl;

function handleDragStart(e) {
	dragSrcEl = this;
	e.dataTransfer.effectAllowed = 'move';
	e.dataTransfer.setData('text/html', this.innerHTML);
}

function handleDragEnter(e) {
	this.classList.add('over');
}

function handleDragOver(e) {
	if (e.preventDefault) {
		e.preventDefault();
	}
	e.dataTransfer.dropEffect = 'move';
	return false;
}

function handleDragLeave() {
	this.classList.remove('over');
}

function handleDrop(e) {
	if (dragSrcEl !== this) {
		const srcContent = dragSrcEl.innerHTML;
		dragSrcEl.innerHTML = this.innerHTML;
		this.innerHTML = srcContent;
	}
	return false;
}

// 드래그 앤 드랍이 완료 됐을 시 일정 리스트 수정, 지도, 마커 수정
function handleDragEnd() {
	let listItems = document.querySelectorAll('.list-card');
	let newArray = [];

	listItems.forEach(listItem => {
		listItem.classList.remove('over');
		const idDataElement = listItem.querySelector('input[type="hidden"]');
		const idData = idDataElement.value;
		locations.forEach(item => {
			if (item.id == idData) {
				newArray.push(item);
			}
		});
	});


	locations = [...newArray];
	deleteMarkers();
	polyline.setMap(null);
	drawLine(locations);
	printList();
}



/* 지도에 선 그리고 마커 표시 */
function drawLine(locations) {
	let linePath = [];
	for (let i = 0; i < locations.length; i++) {
		linePath.push(new kakao.maps.LatLng(locations[i].y, locations[i].x));
		setMarker(i, locations[i].y, locations[i].x);
	}

	// 지도에 표시할 선을 생성합니다
	polyline = new kakao.maps.Polyline({
		path: linePath, // 선을 구성하는 좌표배열 입니다
		strokeWeight: 3, // 선의 두께 입니다
		strokeColor: '#FFAE00', // 선의 색깔입니다
		strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
		strokeStyle: 'solid' // 선의 스타일입니다
	});

	if (isFirst) {
		isFirst = false;
	}
	// 지도에 선을 표시합니다 
	polyline.setMap(map);
}

// 지도 중심 이동
function panTo(location) {
	// 이동할 위도 경도 위치를 생성합니다 
	let moveLatLon = new kakao.maps.LatLng(location.y, location.x);

	// 지도 중심을 부드럽게 이동시킵니다
	// 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
	map.panTo(moveLatLon);
}

/* 지도 마커 표시 */
function setMarker(index, lat, lng) {
	let content = "<div class = 'circle' style='width :20px; height: 20px; text-align: center;'>" + (index + 1) + "</div>";


	// 커스텀 오버레이가 표시될 위치입니다 
	let position = new kakao.maps.LatLng(lat, lng);

	// 커스텀 오버레이를 생성합니다
	let customOverlay = new kakao.maps.CustomOverlay({
		position: position,
		content: content
	});

	markers.push(customOverlay); // 마커 배열에 저장 (추후 삭제를 위함)

	customOverlay.setMap(map);
}

/* 지도 마커 삭제 */
function deleteMarkers() {
	markers.forEach(marker => {
		marker.setMap(null);
	});
	markers.length = 0;
}

/* 위도, 경도로 거리 계산 */
function getDistanceFromLatLonInKm(lat1, lng1, lat2, lng2) {
	function deg2rad(deg) {
		return deg * (Math.PI / 180)
	}
	let R = 6371; // Radius of the earth in km
	let dLat = deg2rad(lat2 - lat1);  // deg2rad below
	let dLon = deg2rad(lng2 - lng1);
	let a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	let c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	let d = R * c; // Distance in km
	return d;
}

/* 선택한 컨텐츠들로 동선 짜기 */
function sorting() {

	if (confirm('일정 내 첫번째 장소를 기준으로 일정의 순서가 변경됩니다.')) {
		distances.length = 0; // distances 안에 있는 값을 비움
		let tempArray = [...locations]; // locations를 깊은복사 한 배열 (배열의 원소를 빼주면서 계산하기 때문)
		let sortedArray = []; // 정렬된 location들이 담길 배열

		let criteria = tempArray[0]; // 최단거리 찾기의 기준이 될 경위도 (최초는 첫 번째 장소가 된다.)
		sortedArray.push(criteria);	// locations의 첫 번째 값을 기준으로 동선을 작성하기 때문에 첫 번째 값은 정렬된 배열의 첫 번째 값으로 고정
		tempArray.splice(0, 1); // 최단거리을 찾을 때 기준은 빠져야하기 때문에 제거

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

		for (let k = 0; k < sortedArray.length; k++) {
			console.log(sortedArray[k]);
		}

		if (!isFirst) {
			polyline.setMap(null);
		}
		drawLine(sortedArray); // 정렬된 배열으로 선을 그림
		panTo(sortedArray[0]); // 일정의 시작지점을 지도의 중앙으로
		locations = [...sortedArray]; // locations에 정렬된 Array복사
		printList(); // 정렬된 일정을 출력

	}
}

/* 일정 리스트에서 삭제 */
function removeList(index) {

	locations.splice(index, 1);
	deleteMarkers();
	polyline.setMap(null);
	if (locations.length !== 0) {
		drawLine(locations);
	}
	printList();
}

/* 날짜 변경 시 */
function changeDate() {
	const dateInput = document.getElementById('date');
	const scheduleDate = document.getElementById('schedule-date');
	const output = document.getElementById('list-output');
	if (confirm('저장되지 않은 일정은 삭제됩니다. 날짜를 바꾸시겠습니까?')) {
		deleteMarkers();
		if (!isFirst) {
			polyline.setMap(null);
		}
		isFirst = true;
		locations.length = 0;
		let startDate = new Date(dateInput.getAttribute("min"));
		let seletedDate = new Date(dateInput.value);
		let diff = Math.abs(startDate.getTime() - seletedDate.getTime());
		diff = Math.ceil(diff / (1000 * 60 * 60 * 24));
		scheduleDate.textContent = diff + 1;
		
	};
}


/* 모달 */
const modal = document.getElementById("myModal");// 모달 창
/* 모달창(메모창) 열기 */
function openModal(title, index) {
	modal.style.display = "block";
	let contentsTitle = document.getElementById('contents-title');
	let contentsIndex = document.getElementById('contents-index');
	let contentsMemo = document.getElementById('contents-memo');
	contentsMemo.value = locations[index].memo; // 해당 컨텐츠의 메모내용
	contentsTitle.textContent = title; //해당 컨텐츠(장소)의 이름
	contentsIndex.value = index;

}

/* 모달 닫기 버튼 눌렀을 시 */
function closeModal() {
	locations[document.getElementById('contents-index').value].memo = document.getElementById('contents-memo').value;
	document.getElementById('contents-memo').value = '';
	document.getElementById('contents-index').value = "";
	modal.style.display = "none";
}

// 일정 저장
function saveSchedule() {
	alert('저장');
}

