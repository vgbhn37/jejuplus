package com.green.jejuplus.controller.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.service.schedule.ScheduleService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
	
	@Autowired
	ScheduleService scheduleService;
	

	@GetMapping("/list")
	public String list() {
		
		return "/schedule/list";
	}
	
	@GetMapping("/edit")
	public String edit(Model model) {
		
		List<Contents> list = scheduleService.findAllList();
		model.addAttribute("list",list);
	
		return "/schedule/edit";
	}

	
}
