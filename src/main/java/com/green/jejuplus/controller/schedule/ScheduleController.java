package com.green.jejuplus.controller.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;
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
	
	@GetMapping("/edit/{scheduleId}")
	public String edit(@PathVariable("scheduleId")Integer scheduleId ,Model model) {
		Schedule schedule = scheduleService.findScheduleById(scheduleId);
		List<Contents> list = scheduleService.findAllList();
		
		model.addAttribute("schedule", schedule);
		model.addAttribute("list",list);
	
		return "/schedule/edit";
	}

	
}
