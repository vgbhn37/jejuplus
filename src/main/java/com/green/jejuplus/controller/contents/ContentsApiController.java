package com.green.jejuplus.controller.contents;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.jejuplus.dto.contents.FavoriteRequestDto;
import com.green.jejuplus.dto.contents.ReviewDto;
import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.contents.ContentsService;
import com.green.jejuplus.service.contents.FavoriteService;
import com.green.jejuplus.service.contents.ReviewService;
import com.green.jejuplus.util.Define;



@RestController
@RequestMapping("/api")
public class ContentsApiController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	@Autowired
	private ContentsService contentsService;
	
	@Autowired
	private HttpSession session;
	
	// 찜 등록
	@PostMapping("/contents/{contentsLabel}/{contentsId}/favorite")
	public ResponseEntity<Integer> insertFavorite(@PathVariable String contentsLabel, @PathVariable Integer contentsId, @RequestBody FavoriteRequestDto favoriteRequestDto, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
	
		ResponseEntity<Integer> response = favoriteService.insertFavorite(principal.getUserId(), contentsId);

		if (contentsLabel.equals("관광지")) {
			contentsLabel = "touristAreaDetail";
		}
		if (contentsLabel.equals("음식점")) {
			contentsLabel = "restaurantDetail";
		}
		if (contentsLabel.equals("쇼핑")) {
			contentsLabel = "shoppingDetail";
		}
		if (contentsLabel.equals("숙박")) {
			contentsLabel = "lodgingDetail";
		}

		return response;
	}
	
	// 찜 삭제
	@DeleteMapping("/contents/{contentsLabel}/{contentsId}/unfavorite")
	public ResponseEntity<Integer> deleteFavorite(@PathVariable String contentsLabel, @PathVariable Integer contentsId, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		ResponseEntity<Integer> response = favoriteService.deleteFavorite(principal.getUserId(), contentsId);

		return response;		
	}
	
//	// 추천
//	@PostMapping("/contents/{contentsLabel}/{contentsId}/recommend")
//	public ResponseEntity<Integer> insertRecommend(@PathVariable String contentsLabel, @PathVariable Integer contentsId, @RequestBody Integer recommended) {
//		User principal = (User) session.getAttribute(Define.PRINCIPAL);
//		Contents contents = new Contents();
//		contents.setRecommended(recommended);
//		ResponseEntity<Integer> response = contentsService.insertRecommned(contentsId);
//		
//		if (contentsLabel.equals("관광지")) {
//			contentsLabel = "touristAreaDetail";
//		}
//		if (contentsLabel.equals("음식점")) {
//			contentsLabel = "restaurantDetail";
//		}
//		if (contentsLabel.equals("쇼핑")) {
//			contentsLabel = "shoppingDetail";
//		}
//		if (contentsLabel.equals("숙박")) {
//			contentsLabel = "lodgingDetail";
//		}
//		
//		return response;
//	}
//	
//	// 추천 취소
//	@DeleteMapping("/favorite/{contentsLabel}/{contentsId}/unfavorite")
//	public ResponseEntity<Integer> deleteRecommend(@PathVariable String contentsLabel, @PathVariable Integer contentsId) {
//		User principal = (User) session.getAttribute(Define.PRINCIPAL);
//		ResponseEntity<Integer> response = contentsService.deleteRecommend(contentsId);
//		
//		return response;		
//	}
	
	// 리뷰 등록
	@PostMapping("/contents/{contentsLabel}/{contentsId}/review")
	public ResponseEntity<ReviewDto> insertReview(@PathVariable String contentsLabel, @PathVariable Integer contentsId, @RequestBody ReviewDto reviewDto, Model model) {	
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		reviewService.insertReview(principal.getUserId(), reviewDto);
		
		if (contentsLabel.equals("관광지")) {
			contentsLabel = "touristAreaDetail";
		}
		if (contentsLabel.equals("음식점")) {
			contentsLabel = "restaurantDetail";
		}
		if (contentsLabel.equals("쇼핑")) {
			contentsLabel = "shoppingDetail";
		}
		if (contentsLabel.equals("숙박")) {
			contentsLabel = "lodgingDetail";
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(reviewDto);
	}
	
	// 리뷰 삭제 
	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<Integer> delete(@PathVariable Integer reviewId, Model model) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		reviewService.deleteReview(reviewId);
		
		return ResponseEntity.status(HttpStatus.OK).body(reviewId);	
	}
}
