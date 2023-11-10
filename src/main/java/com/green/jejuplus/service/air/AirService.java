package com.green.jejuplus.service.air;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.air.AirDTO;
import com.green.jejuplus.repository.interfaces.AirRepository;
import com.green.jejuplus.repository.model.Air;

@Service
public class AirService {
	
	@Autowired
	private AirRepository airRepository;
	
	@Transactional
	public void insertAir(AirDTO airDTO, int userId) {
		System.out.println("airDTO AirService start : " + airDTO);
		
		Air air = new Air();
		air.setUserId(userId);
		
		System.out.println("airDTO AirService end : " + airDTO);
	}
	
	
}
