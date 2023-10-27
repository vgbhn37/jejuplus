package com.green.jejuplus.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Autowired
	HttpSession session;
	
	// main page (강중현)
	@GetMapping("/main")
	public String main() {
		
		return "main";
	}
}
