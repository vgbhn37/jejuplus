package com.green.jejuplus.repository.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Promotion {
	

	int promotionId;
	String title;
	String introduce;
	String content;
	String start_date;
	String end_date;
	

	
}
