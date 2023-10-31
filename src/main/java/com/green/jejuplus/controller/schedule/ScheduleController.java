package com.green.jejuplus.controller.schedule;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.schedule.ScheduleService;
import com.green.jejuplus.util.Define;
import com.green.jejuplus.util.Pagination;
import com.green.jejuplus.util.PagingDto;

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
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		Schedule schedule = scheduleService.findScheduleById(scheduleId);
		PagingDto paging = new PagingDto();
		Pagination pagination = new Pagination();
		pagination.setPaging(paging);
		int total =  scheduleService.countList("all", user);
		pagination.setArticleTotalCount(total);
		
		model.addAttribute(pagination);
		
		List<Contents> list = scheduleService.findAllList(paging);

		model.addAttribute("schedule", schedule);
		model.addAttribute("list", list);
		model.addAttribute("label", "all");

		return "/schedule/edit";
	}

	@GetMapping("/show-list/{label}")
	public String contentList(@ModelAttribute("paging")PagingDto paging,@PathVariable("label") String label,
			@RequestParam(value = "page", required = false, defaultValue = "1")int page, Model model, HttpServletRequest request) {
		
		// 주소창에 직접 입력시 오류 발생
		if (request.getHeader("REFERER") == null) {
			throw new CustomException("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
		}
		
		User user = (User) session.getAttribute(Define.PRINCIPAL);
		Pagination pagination = new Pagination();
		paging.setPage(page);
		pagination.setPaging(paging);
		int total =  scheduleService.countList(label, user);
		pagination.setArticleTotalCount(total);
		List<Contents> list = scheduleService.findListByLabel(label,user,paging);
		model.addAttribute("list",list);
		model.addAttribute("pagination", pagination);
		model.addAttribute("label", label);

		return "/schedule/contentsList";
	}
	
	@GetMapping("/call-search")
	public String callSearch(HttpServletRequest request) {
		if (request.getHeader("REFERER") == null) {
			throw new CustomException("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
		}
		
		return "/schedule/searchWindow";
	}
	
	@GetMapping("/search-list")
	public String searchList(@ModelAttribute("paging")PagingDto paging, @RequestParam("category") String category, 
			@RequestParam("keyword") String keyword, @RequestParam(value = "page", required = false, defaultValue = "1")int page, 
			HttpServletRequest request, Model model) {
		// 주소창에 직접 입력시 오류 발생
		if (request.getHeader("REFERER") == null) {
			throw new CustomException("잘못된 접근입니다.", HttpStatus.BAD_REQUEST);
		}
	
		Pagination pagination = new Pagination();
		paging.setPage(page);
		pagination.setPaging(paging);
		int total = scheduleService.countSearchList(category,keyword);
		pagination.setArticleTotalCount(total);
		List<Contents> list = scheduleService.findContentsBySearch(category, keyword, paging);
		model.addAttribute("list",list);
		model.addAttribute("pagination", pagination);
		
		
		return "/schedule/searchList";
	}
	
}
