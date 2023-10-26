package com.green.jejuplus.controller.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@GetMapping("/list")
	public String list() {
		
		return "/schedule/list";
	}
	
	@GetMapping("/edit")
	public String edit() {
		
		return "/schedule/edit";
	}

	
}
