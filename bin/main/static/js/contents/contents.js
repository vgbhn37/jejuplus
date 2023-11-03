let contents = {
	version: 1,
	init: function() {
		const unfavorite = document.querySelector("#unfavorite");
		if (unfavorite != null) {
			unfavorite.addEventListener("click", () => {
				this.favorite();
			});
		}

		const favorite = document.querySelector("#favorite");

		if (favorite != null) {
			favorite.addEventListener("click", () => {
				this.unfavorite();
			});
		}
		const unrecommend = document.querySelector("#unrecommend");
		if (unrecommend != null) {
			unrecommend.addEventListener("click", () => {
				this.recommend();
			});
		}

		const recommend = document.querySelector("#recommend");

		if (recommend != null) {
			recommend.addEventListener("click", () => {
				this.unrecommend();
			});
		}

		document.querySelector("#insertReviewBtn").addEventListener("click", () => {
			this.insertReview();
		});
		
		const deleteReviewBtn = document.querySelector("#deleteReviewBtn");

		if (deleteReviewBtn != null) {
			document.querySelector("#deleteReviewBtn").addEventListener("click", () => {
				this.deleteReview();
			});
		}
	},
	favorite: function() {
		let favorite = {
			userId: document.getElementById("userId").value,
			contentsId: document.getElementById("contentsId").value,
			contentsLabel: document.getElementById("contentsLabel").value
		};

		fetch("/api/contents/"+ favorite.contentsLabel + "/" + favorite.contentsId + "/favorite", {
			method: "POST",
			body: JSON.stringify(favorite),
			headers: {
				"Content-Type": "application/json",
			}
		}).then(response => {
			const msg = (response.ok) ? "찜이 등록되었습니다." : "찜 등록 실패";
			alert(msg);
			window.location.reload();
		});
	},

	unfavorite: function() {
		let unfavorite = {
			contentsId: document.getElementById("contentsId").value,
			contentsLabel: document.getElementById("contentsLabel").value
		};
		console.log(unfavorite.contentsId);
		fetch("/api/contents/"+ unfavorite.contentsLabel + "/" + unfavorite.contentsId + "/unfavorite", {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json",
			}
		}).then(response => {
			const msg = (response.ok) ? "찜이 삭제되었습니다." : "찜 삭제 실패";
			alert(msg);
			window.location.reload();
		});
	},
	
	recommend: function() {
		let recommend = {
			userId: document.getElementById("userId").value,
			contentsId: document.getElementById("contentsId").value,
			contentsLabel: document.getElementById("contentsLabel").value
		};

		fetch("/api/contents/"+ recommend.contentsLabel + "/" + recommend.contentsId + "/recommend", {
			method: "POST",
			body: JSON.stringify(recommend),
			headers: {
				"Content-Type": "application/json",
			}
		}).then(response => {
			const msg = (response.ok) ? "추천되었습니다." : "추천 등록 실패";
			alert(msg);
			window.location.reload();
		});
	},

	unrecommend: function() {
		let unrecommend = {
			contentsId: document.getElementById("contentsId").value
		};
		console.log(unrecommend.contentsId);
		fetch("/api/contents/"+ unrecommend.contentsLabel + "/" + unrecommend.contentsId + "/unrecommend", {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json",
			}
		}).then(response => {
			const msg = (response.ok) ? "추천이 취소되었습니다." : "추천 삭제 실패";
			alert(msg);
			window.location.reload();
		});
	},

	insertReview: function() {

		let review = {
			reviewStar: document.querySelector('input[name="reviewStar"]:checked').value,
			reviewContent: document.getElementById("reviewContent").value,
			contentsId: document.getElementById("contentsId").value,
			contentsLabel: document.getElementById("contentsLabel").value
		};
		console.log(contentsLabel);
		
		fetch("/api/contents/"+ review.contentsLabel + "/" + review.contentsId + "/review", {
			method: "POST",
			body: JSON.stringify(review),
			headers: {
				"Content-Type": "application/json",
			}
		}).then(response => {
			const msg = (response.ok) ? "댓글이 등록되었습니다." : "댓글 등록 실패";
			alert(msg);
			window.location.reload();
		});
	},
	deleteReview: function() {
		let reviewId = document.getElementById("reviewId").value;

		fetch("/api/review/" + reviewId, {
			method: "DELETE",
			headers: {
				"Content-Type": "application/json"
			}
		}).then(response => {
			const msg = (response.ok) ? "댓글이 삭제되었습니다." : "댓글 삭제 실패";
			alert(msg);

			window.location.reload();
		});
	},
}
contents.init();
