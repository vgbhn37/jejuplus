/**
 * 스케쥴 리스트 노출 페이지
 */

let scheduleList = {

	init: function() {

		const scheModalBtn = document.getElementById('sche-modal');
		const scheAddBtn = document.getElementById('sche-add');
		scheModalBtn.addEventListener('click', () => {
			openModal();
		});
		scheAddBtn.addEventListener('click', () => {
			this.insertSche();
		});
	},
	/* 스케쥴 추가*/
	insertSche: function() {
		const title = document.getElementById('schedule-title');
		const startDate = document.getElementById('sche-start-date');
		const endDate = document.getElementById('sche-end-date');
		const userId = document.getElementById('user-id');

		let data = {
			userId: userId.value,
			title: title.value,
			startDate: startDate.value,
			endDate: endDate.value
		}

		let options = {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(data)
		}

		fetch('/schedule/insert', options)
			.then(res => {
				if (!res.ok) {
					throw new Error('네트워크 응답 오류');
				}
				return res.text();
			}).then(data => {
				location.href = '/schedule/detail/edit/' + data;
			}).catch(error => {
				alert(error.message);
			});
	}
}

scheduleList.init();

/* 모달 */
const modal = document.getElementById("myModal");// 모달 창
/* 모달창(메모창) 열기 */
function openModal() {
	modal.style.display = "block";
}

/* 모달 닫기 버튼 눌렀을 시 */
function closeModal() {
	modal.style.display = "none";
}