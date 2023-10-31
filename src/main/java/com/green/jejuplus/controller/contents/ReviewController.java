package com.green.jejuplus.controller.contents;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.green.jejuplus.dto.ReviewDto;
import com.green.jejuplus.service.ReviewService;



@RestController
@RequestMapping("/api")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private HttpSession session;
	
	@PostMapping("/contents/{contentsLabel}/{contentsId}/review")
	public ResponseEntity<ReviewDto> insertReview(@PathVariable String contentsLabel, @PathVariable Integer contentsId, @RequestBody ReviewDto reviewDto) {	
		
		reviewService.insertReview(1, reviewDto);
		
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
	
	@PatchMapping("/review/{reviewId}")
	public ResponseEntity<ReviewDto> updateReview(@PathVariable String reviewId, @RequestBody ReviewDto reviewDto) {
		
		reviewService.updateReview(reviewDto);
		return ResponseEntity.status(HttpStatus.OK).body(reviewDto);
		
	}
	
	@DeleteMapping("/review/{reviewId}")
	public ResponseEntity<Integer> delete(@PathVariable Integer reviewId) {
		reviewService.deleteReview(reviewId);
		
		return ResponseEntity.status(HttpStatus.OK).body(reviewId);
		
	}
}
