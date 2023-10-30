package com.green.jejuplus.controller.schedule;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.schedule.ScheduleService;
import com.green.jejuplus.util.Define;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	HttpSession session;

	@GetMapping("/list")
	public String list() {

		return "/schedule/list";
	}

	@GetMapping("/edit/{scheduleId}")
	public String edit(@PathVariable("scheduleId") Integer scheduleId, Model model) {
		Schedule schedule = scheduleService.findScheduleById(scheduleId);
		List<Contents> list = scheduleService.findAllList();

		model.addAttribute("schedule", schedule);
		model.addAttribute("list", list);

		return "/schedule/edit";
	}

	@GetMapping("/show-list/{label}")
	@ResponseBody
	public List<Contents> listByLabel(@PathVariable("label") String label) {
		
		User user = (User) session.getAttribute(Define.PRINCIPAL);

		switch (label) {

		case "attraction":
			return scheduleService.findListByLabel(Define.ATTRACTION);
		case "accomodation":
			return scheduleService.findListByLabel(Define.ACCOMODATION);
		case "shopping":
			return scheduleService.findListByLabel(Define.SHOPPING);
		case "restaurant":
			return scheduleService.findListByLabel(Define.SHOPPING);
		case "favorite":
			return scheduleService.findMyFavoriteList(user.getUserId());
		default :
			return scheduleService.findAllList();
		}
		
	}

}
