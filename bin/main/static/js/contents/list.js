let contentsList = {
	/* 컨텐츠 리스트의 페이지 변경 시 페이지 세팅*/
	changePage: function(e) {

		console.log(e.target.getAttribute('data-page'));
		location.href = '/contents/touristAreaList?page=' +e.target.getAttribute('data-page');

	}
}