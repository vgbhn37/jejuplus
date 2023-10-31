package com.green.jejuplus.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ReviewDto {
	private Integer reviewId;
	private Integer contentsId;
	private Integer userId;
	private String reviewContent;
	private Timestamp createdAt;
	private Integer reviewRecommend;
}
