package com.green.jejuplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	// main page (강중현)
	@GetMapping("/main")
	public String main() {
		return "main";
	}
}
