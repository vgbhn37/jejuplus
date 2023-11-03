package com.green.jejuplus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.green.jejuplus.dto.MainRestaurantDto;
import com.green.jejuplus.service.MainService;

@Controller
public class MainController {

	@Autowired
	HttpSession session;
	
	@Autowired
	MainService mainservice;
	
	@GetMapping(value= {"main","/"})
	public String main(Model model) {
		List<MainRestaurantDto> restaurantDto = mainservice.findRestaurant();
		model.addAttribute("restaurantDto",restaurantDto);
		
		List<MainRestaurantDto> palceDto = mainservice.findPlace();
		model.addAttribute("palceDto",palceDto);
		return "/main";
	}
	
	
}
