package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.jejuplus.dto.ReviewDto;

@Mapper
public interface ReviewRepository {

	List<ReviewDto> findReviewList(int contentsId);

	int insertReview(ReviewDto reviewDto);
	
	void deleteReview(Integer reviewId);
	

}
