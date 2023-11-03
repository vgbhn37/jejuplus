package com.green.jejuplus.repository.model;

import lombok.Data;

@Data
public class Air {
	private int airId;
	private int userId;
	private int paymentId;
	private String depPlandTime;
	private String arrPlandTime;
	private String airlineNm;
	private String arrAirportNm;
	private String depAirportNm;
}
