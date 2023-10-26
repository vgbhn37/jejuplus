package com.green.jejuplus.controller.air;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AirController {

	// main page (강중현)
	@GetMapping("/air/index")
	public String index() {
		return "air/index";
	}
	
	// 예약 및 결제 페이지
	@GetMapping("/air/booking")
	public String booking() {
		return "air/booking";
	}
	
	// 결제 완료 페이지
	@GetMapping("/air/bookingcomplete")
	public String bookingcomplete() {
		return "air/bookingcomplete";
	}
	
	
}
