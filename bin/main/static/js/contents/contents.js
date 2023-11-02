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
			contentsId: document.getElementById("contentsId").value
		};

		fetch("/api/contents/touristAreaDetail/" + favorite.contentsId + "/favorite", {
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
			contentsId: document.getElementById("contentsId").value
		};
		console.log(unfavorite.contentsId);
		fetch("/api/favorite/touristAreaDetail/" + unfavorite.contentsId + "/unfavorite", {
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

	insertReview: function() {

		let review = {
			reviewStar: document.querySelector('input[name="reviewStar"]:checked').value,
			reviewContent: document.getElementById("reviewContent").value,
			contentsId: document.getElementById("contentsId").value
		};

		fetch("/api/contents/touristAreaDetail/" + review.contentsId + "/review", {
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
