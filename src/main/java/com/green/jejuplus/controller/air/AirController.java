package com.green.jejuplus.controller.air;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.jejuplus.dto.air.OpenApiAirDTO;
import com.green.jejuplus.service.air.OpenApiAirService;

@Controller
@RequestMapping("/air")
public class AirController {

	// main page (강중현)
	@GetMapping("/index")
	public String index() {
		return "air/index";
	}
	
	// 예약 및 결제 페이지
	@GetMapping("/booking")
	public String booking() {
		return "air/booking";
	}
	
//	@PostMapping("/booking")
//	public String bookingProc() {
//		
//		return "redirect:/air/booking";
//	}
	
	// 결제 완료 페이지
	@GetMapping("/bookingcomplete")
	public String bookingcomplete() {
		return "air/bookingcomplete";
	}
	
	// API
	@GetMapping("/api")
	public OpenApiAirDTO api(Model model) {
		try {
			String apiResponse = OpenApiAirService.OpenApiAir();
			ObjectMapper objectMapper = new ObjectMapper();	// Jackson 라이브러리를 사용하여 JSON 파싱
			return objectMapper.readValue(apiResponse, OpenApiAirDTO.class);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
}
