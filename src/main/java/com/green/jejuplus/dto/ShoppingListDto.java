package com.green.jejuplus.dto;

import lombok.Data;

@Data
public class ShoppingListDto {
	private int contentsId;
	private String contentsLabel;
	private String thumbnailPath;
	private String title;
	private String region1;
	private String region2;
	private String tag;
	private int recommended;
}
