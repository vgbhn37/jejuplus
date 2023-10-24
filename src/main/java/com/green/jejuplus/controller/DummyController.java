package com.green.jejuplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DummyController {

	// 더미 파일임 추후 제거 요망
	@GetMapping("/test")
	public String test() {
		return "main"; 
	}
}
