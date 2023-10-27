package com.green.jejuplus.dto.air;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenApiAirDTO {

	private int airId;
	private int userId;
	private int paymentId;
    
    private String depPlandTime;
    private String arrPlandTime;
    
    private String airlineNm;
    
    private String depAirportId;
    private String arrAirportId;
}
