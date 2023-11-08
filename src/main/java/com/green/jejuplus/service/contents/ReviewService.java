package com.green.jejuplus.service.contents;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.contents.ReviewDto;
import com.green.jejuplus.repository.interfaces.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	
	public List<ReviewDto> showReview(int contentsId) {
		List<ReviewDto> review = reviewRepository.findReviewList(contentsId);
		for (ReviewDto dto : review) {
			dto.setUsername(reviewRepository.findUsernameByUserId(dto.getUserId()));
		}
		return review;
	}
	
	public void insertReview(Integer userId, ReviewDto reviewDto) {
		ReviewDto dto = reviewDto;
		dto.setUserId(userId);
		reviewRepository.insertReview(dto);
	}

	public void updateReview(Integer userId, ReviewDto reviewDto) {
		ReviewDto dto = reviewDto;
		dto.setUserId(userId);
		reviewRepository.updateReview(dto);
		
	}

	public void deleteReview(Integer reviewId) {
		reviewRepository.deleteReview(reviewId);
	}


}
