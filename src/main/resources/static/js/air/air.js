/**
 * 2023-10-26
 * 강중현
 */

/* main 달력 start */
$(document).ready(function() {
	$(function() {
		$('input[name="daterange"]').daterangepicker({
			startDate: moment(),
			endDate: moment(),
			opens: 'center',
			autoApply: true,
			locale: {
				format: 'YYYY-MM-DD',
			},
			minDate: moment(), // 이전 날짜 선택 방지
		});
	});
});
/* main 달력 end */

/* 사이드바 토글 start */
$(document).ready(function() {
	$('#toggleButton').click(function() {
		$('.sidebar').toggleClass('show-sidebar');
	});
});
/* 사이드바 토글 end */

/* close start */
$(function() {
	$('input[name=dep-name]').keydown(function() {
		if ($(this).val().trim() != '') {
			$(this).next().css('display', 'block');
		} else {
			$(this).next().css('display', 'none');
		}
	});
	$('.fa-times-circle').click(function() {
		$(this).prev().val('');
		$(this).css('display', 'none');
	});
});
/* close end */

/* 탑승객 숫자 start */
document.addEventListener('DOMContentLoaded', function() {
	// 성인 승객 관련 요소
	const decreaseButtonsAdult = document.querySelectorAll('.cus-minus-adult');
	const increaseButtonsAdult = document.querySelectorAll('.cus-plus-adult');
	const passengerCountElementsAdult = document.querySelectorAll('.passengerCountAdult');

	// 어린이 승객 관련 요소
	const decreaseButtonsChild = document.querySelectorAll('.cus-minus-child');
	const increaseButtonsChild = document.querySelectorAll('.cus-plus-child');
	const passengerCountElementsChild = document.querySelectorAll('.passengerCountChild');

	// 초기 승객 수 설정
	let passengerCountAdult = 1;
	let passengerCountChild = 1;

	// 성인 승객 감소 버튼 클릭 시
	decreaseButtonsAdult.forEach((button, index) => {
		button.addEventListener('click', () => {
			if (passengerCountAdult > 0) {
				passengerCountAdult--;
				passengerCountElementsAdult[index].textContent = passengerCountAdult;
			}
			updateTotalPassengerCount();
		});
	});

	// 성인 승객 증가 버튼 클릭 시
	increaseButtonsAdult.forEach((button, index) => {
		button.addEventListener('click', () => {
			if (getTotalPassengerCount() < 6) {
				passengerCountAdult++;
				passengerCountElementsAdult[index].textContent = passengerCountAdult;
				updateTotalPassengerCount();
			} else {
				alert('성인 및 어린이 승객의 합은 6명을 초과할 수 없습니다.');
			}
		});
	});

	// 어린이 승객 감소 버튼 클릭 시
	decreaseButtonsChild.forEach((button, index) => {
		button.addEventListener('click', () => {
			if (passengerCountChild > 0) {
				passengerCountChild--;
				passengerCountElementsChild[index].textContent = passengerCountChild;
			}
			updateTotalPassengerCount();
		});
	});

	// 어린이 승객 증가 버튼 클릭 시
	increaseButtonsChild.forEach((button, index) => {
		button.addEventListener('click', () => {
			if (getTotalPassengerCount() < 6) {
				passengerCountChild++;
				passengerCountElementsChild[index].textContent = passengerCountChild;
				updateTotalPassengerCount();
			} else {
				alert('성인 및 어린이 승객의 합은 6명을 초과할 수 없습니다.');
			}
		});
	});

	// 총 승객 수 업데이트 함수
	function updateTotalPassengerCount() {
		const totalPassengerCount = getTotalPassengerCount();
		// 여기에서 필요한 작업을 수행하세요. 예를 들어, 6명을 초과하는지 확인하고 필요한 조치를 취할 수 있습니다.
		// totalPassengerCount를 사용하여 원하는 동작을 수행하세요.
		if (totalPassengerCount < 1) {
			alert('성인 및 어린이 승객 중 최소 1명 이상을 선택해야 합니다.');
		}
	}

	// 총 승객 수를 반환하는 함수
	function getTotalPassengerCount() {
		return passengerCountAdult + passengerCountChild;
	}
});
/* 탑승객 숫자 end */




