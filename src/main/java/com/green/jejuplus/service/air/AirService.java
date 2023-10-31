//package com.green.jejuplus.service.air;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.green.jejuplus.dto.air.OpenApiAirDTO;
//import com.green.jejuplus.repository.interfaces.AirRepository;
//import com.green.jejuplus.repository.model.Air;
//
//@Service
//public class AirService {
//	
//	@Autowired
//    private AirRepository airRepository;
//
//    public void saveAirData(OpenApiAirDTO openApiAirDTO) {
//        Air air = new Air();
//        air.setDepPlandTime(openApiAirDTO.getDepPlandTime());
//        air.setArrPlandTime(openApiAirDTO.getArrPlandTime());
//        air.setAirlineNm(openApiAirDTO.getAirlineNm());
//        air.setArrAirportNm(openApiAirDTO.getArrAirportNm());
//        air.setDepAirportNm(openApiAirDTO.getDepAirportNm());
//
//        airRepository.insert(air); // 데이터베이스에 저장
//        
//        System.out.println("데이터 저장 성공(AirService) : " + air);
//    }
//}
