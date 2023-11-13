package com.green.jejuplus.service.air;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.air.AirDTO;
import com.green.jejuplus.dto.payment.PaymentDTO;
import com.green.jejuplus.repository.interfaces.AirRepository;
import com.green.jejuplus.repository.model.Air;

@Service
public class AirService {
	
	@Autowired
	private AirRepository airRepository;
	
	@Transactional
	public void insertAir(AirDTO airDTO, int userId, PaymentDTO paymentDTO) {
		System.out.println("airDTO AirService start : " + airDTO);
		
		Air air = new Air();
		air.setUserId(userId);
		air.setPaymentId(airDTO.getPaymentId());
		air.setAirlineNm(airDTO.getAirlineNm());
		air.setDepPlandTime(airDTO.getDepPlandTime());
		air.setArrPlandTime(airDTO.getArrPlandTime());
		air.setDepAirportNm(airDTO.getDepAirportNm());
		air.setArrAirportNm(airDTO.getArrAirportNm());
		
		air.setDepPlandTime2(airDTO.getDepPlandTime2());
		air.setArrPlandTime2(airDTO.getArrPlandTime2());
		air.setDepAirportNm2(airDTO.getDepAirportNm2());
		air.setArrAirportNm2(airDTO.getArrAirportNm2());
		
		
		System.out.println("airDTO AirService end : " + airDTO);
		int resultAir = airRepository.insertAir(air);
	}
}
