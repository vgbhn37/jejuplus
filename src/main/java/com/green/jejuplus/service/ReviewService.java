package com.green.jejuplus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.ReviewDto;
import com.green.jejuplus.repository.interfaces.ContentsRepository;
import com.green.jejuplus.repository.interfaces.ReviewRepository;


@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ContentsRepository contentsRepository;

	public List<ReviewDto> showReview(int contentsId) {
		List<ReviewDto> review = reviewRepository.findReviewList(contentsId);
		return review;
	}
	
	public void insertReview(Integer userId, ReviewDto reviewDto) {
		ReviewDto dto = reviewDto;
		dto.setUserId(userId);
		int result = reviewRepository.insertReview(dto);
	}

	public void updateReview(ReviewDto reviewDto) {

	}

	public void deleteReview(Integer reviewId) {
		reviewRepository.deleteReview(reviewId);
	}
}
