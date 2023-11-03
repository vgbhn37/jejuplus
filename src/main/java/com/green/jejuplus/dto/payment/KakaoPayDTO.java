package com.green.jejuplus.dto.payment;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class KakaoPayDTO {

	//response
    private String tid, next_redirect_pc_url;
    private Date created_at;
	
}
