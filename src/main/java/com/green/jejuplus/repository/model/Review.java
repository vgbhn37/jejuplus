package com.green.jejuplus.repository.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Review {
	private Integer reviewId;
	private Integer contentsId;
	private Integer userId;
	private String reviewContent;
	private Timestamp createdAt;
	private Integer reviewRecommend;
}
