package com.green.jejuplus.service.contents;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.contents.ReviewDto;
import com.green.jejuplus.repository.interfaces.ContentsRepository;
import com.green.jejuplus.repository.interfaces.ReviewRepository;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.util.Define;


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

	public void updateReview(Integer userId, ReviewDto reviewDto) {
		ReviewDto dto = reviewDto;
		dto.setUserId(userId);
		int result = reviewRepository.updateReview(dto);
		
	}

	public void deleteReview(Integer reviewId) {
		reviewRepository.deleteReview(reviewId);
	}


}
