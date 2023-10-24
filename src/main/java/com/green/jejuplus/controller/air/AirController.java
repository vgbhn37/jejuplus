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
}
