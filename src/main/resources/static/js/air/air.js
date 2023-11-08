/**
 * 2023-10-26
 * 강중현
 */


/* main 달력 start */
$(document).ready(function() {
	var depPlandTime_input = $('input[name="depPlandTime"]');
	var arrPlandTime_input = $('input[name="arrPlandTime"]');

	depPlandTime_input.daterangepicker({
		singleDatePicker: true,
		startDate: moment(),
		opens: 'center',
		autoApply: true,
		locale: {
			format: 'YYYY-MM-DD',  // "yyyy-mm-dd" 형식으로 설정
		},
		minDate: moment(), // 이전 날짜 선택 방지
	});

	arrPlandTime_input.daterangepicker({
		singleDatePicker: true,
		startDate: moment(),
		opens: 'center',
		autoApply: true,
		locale: {
			format: 'YYYY-MM-DD',  // "yyyy-mm-dd" 형식으로 설정
		},
		minDate: moment(), // 이전 날짜 선택 방지
	});

	// 가는날 선택 시 오는날과 연결
	depPlandTime_input.on('apply.daterangepicker', function(ev, picker) {
		arrPlandTime_input.data('daterangepicker').setStartDate(picker.startDate);
		arrPlandTime_input.data('daterangepicker').setEndDate(picker.startDate);
	});
});
/* main 달력 end */

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
	let passengerCountAdult = 1; // 성인 기본값 1로 설정
	let passengerCountChild = 0; // 어린이 기본값 0으로 설정

	// 초기 승객 수를 업데이트
	passengerCountElementsAdult.forEach((element) => {
		element.textContent = passengerCountAdult;
	});

	passengerCountElementsChild.forEach((element) => {
		element.textContent = passengerCountChild;
	});

	// 성인 승객 감소 버튼 클릭 시
	decreaseButtonsAdult.forEach((button, index) => {
		button.addEventListener('click', () => {
			if (passengerCountAdult > 1 || (passengerCountAdult === 1 && passengerCountChild > 0)) {
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
				alert('총 성인 및 어린이 승객 수는 6명을 초과할 수 없습니다.');
			}
		});
	});

	// 어린이 승객 감소 버튼 클릭 시
	decreaseButtonsChild.forEach((button, index) => {
		button.addEventListener('click', () => {
			if (passengerCountChild > 0 || (passengerCountChild === 0 && passengerCountAdult > 1)) {
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
				alert('총 성인 및 어린이 승객 수는 6명을 초과할 수 없습니다.');
			}
		});
	});

	// 총 승객 수 업데이트 함수
	function updateTotalPassengerCount() {
		const totalPassengerCount = getTotalPassengerCount();
		// 원하는 작업을 수행할 수 있습니다. 예를 들어, 6명 이상인 경우 필요한 조치를 취할 수 있습니다.
		// totalPassengerCount를 사용하여 원하는 작업을 수행하세요.
		if (totalPassengerCount < 1) {
			alert('적어도 1명의 성인 승객과 1명의 어린이 승객을 선택해야 합니다.');
		}
	}

	// 총 승객 수를 반환하는 함수
	function getTotalPassengerCount() {
		return passengerCountAdult + passengerCountChild;
	}
});


/* 탑승객 숫자 end */

/* 예약하기 버튼 */
function checkFlightSelection() {
	var depAirport = $('select[name="depAirportNm"]').val();
	var arrAirport = $('select[name="arrAirportNm"]').val();

	if (depAirport === '선택' || arrAirport === '선택') {
		alert('출발지와 도착지를 선택해주세요.');
	} else if (depAirport === arrAirport) {
		alert('출발지와 도착지가 동일합니다. 예약할 수 없습니다.');
	} else {
		// 출발지와 도착지가 유효하며 동일하지 않은 경우 예약 프로세스를 진행할 수 있습니다.
		// 여기에 필요한 예약 프로세스 코드를 추가하세요.
		// 예를 들어, 서버로 데이터를 전송하거나 예약 페이지로 이동하는 등의 작업을 수행할 수 있습니다.
	}
}

/* 결제버튼 start */
// 카카오페이 외 라디오 버튼 클릭 안됨
//$(document).ready(function() {
//	// 결제 수단 라디오 버튼이 변경될 때 이벤트를 설정합니다.
//	$('input[name="payment"]').on('change', function() {
//		var selectedPayment = $(this).val();
//
//		// 만약 선택한 결제 수단이 '카카오페이'가 아닌 경우 알림을 표시
//		if (selectedPayment !== '5') { // '5'는 카카오페이의 value 값
//			alert('선택한 결제 수단은 현재 사용 불가능합니다. 다른 결제 수단을 선택해 주세요.');
//			// 라디오 버튼을 초기 상태로 되돌림
//			$('input[name="payment"][value="5"]').prop('checked', true); // 카카오페이를 선택한 상태로 되돌립니다.
//		}
//	});
//
//	// 결제하기 버튼 클릭 시 폼을 제출합니다.
//	//$('.btnOrder').on('click', function() {
//	// 여기에서 폼을 제출하는 로직을 작성하면 됩니다.
//	// 예를 들어, 폼의 id가 'paymentForm'라면:
//	// $('#paymentForm').submit();
//	//});
//});

// 라디오 버튼 클릭은 되지만 결제하기 누르면 경고창 발생
$(document).ready(function() {
	// 결제하기 버튼 클릭 시 알림창을 표시합니다.
	$('.btnOrder').on('click', function() {
		var selectedPayment = $('input[name="payment"]:checked').val();

		// 만약 선택한 결제 수단이 '카카오페이'가 아닌 경우 알림을 표시합니다.
		if (selectedPayment !== '5') { // '5'는 카카오페이의 value 값입니다.
			alert('선택한 결제 수단은 현재 사용 불가능합니다. 다른 결제 수단을 선택해 주세요.');
		} else {
			// 여기에서 폼을 제출하는 로직을 작성하면 됩니다.
			// 예를 들어, 폼의 id가 'paymentForm'라면:
			// $('#paymentForm').submit();
		}
	});
});
/* 결제버튼 end */

/* 왕복 편도 라디오 버튼 start */
$(document).ready(function() {
	// 왕복 라디오 버튼 선택 시
	$('#round-trip').on('change', function() {
		if ($(this).is(':checked')) {
			// 왕복 선택 시 가는날과 오는날 모두 활성화
			$('input[name="depPlandTime"]').prop('disabled', false);
			$('input[name="arrPlandTime"]').prop('disabled', false);
			// 오는날 레이블과 입력 필드 보이기
			$('.input-group-m2').show();
		}
	});

	// 편도 라디오 버튼 선택 시
	$('#oneway').on('change', function() {
		if ($(this).is(':checked')) {
			// 편도 선택 시 가는날만 활성화, 오는날 비활성화
			$('input[name="depPlandTime"]').prop('disabled', false);
			$('input[name="arrPlandTime"]').prop('disabled', true);
			// 오는날 레이블과 입력 필드 숨기기
			$('.input-group-m2').hide();
		}
	});
});

/* 왕복 편도 라디오 버튼 end */

/*  */
$(document).ready(function() {
	// "구매자와 동일" 라디오 버튼 클릭 시
	$('#same').click(function() {
		if (this.checked) {
			const customerName = $('input[name=customer-name]').val();
			const customerPhone = $('input[name=customer-phone]').val();
			const customerEmail = $('input[name=customer-email]').val();

			$('input[name=username]').val(customerName);
			$('input[name=phone]').val(customerPhone);
			$('input[name=email]').val(customerEmail);
		}
	});

	$('#new').click(function() {
		if (this.checked) {
			// 탑승객 1 정보 초기화
			$('input[name=username]').val("");
			$('input[name=phone]').val("");
			$('input[name=email]').val("");
		}
	});
});



