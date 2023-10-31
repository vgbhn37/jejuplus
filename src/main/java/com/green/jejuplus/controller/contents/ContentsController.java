package com.green.jejuplus.controller.contents;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.jejuplus.dto.ShoppingDetailDto;
import com.green.jejuplus.dto.ShoppingListDto;
import com.green.jejuplus.dto.LodgingDetailDto;
import com.green.jejuplus.dto.LodgingListDto;
import com.green.jejuplus.dto.RestaurantDetailDto;
import com.green.jejuplus.dto.RestaurantListDto;
import com.green.jejuplus.dto.ReviewDto;
import com.green.jejuplus.dto.TouristAreaDetailDto;
import com.green.jejuplus.dto.TouristAreaListDto;
import com.green.jejuplus.service.ContentsService;
import com.green.jejuplus.service.ReviewService;


@Controller
@RequestMapping("/contents")
public class ContentsController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private ContentsService contentsService;
	@Autowired
	private ReviewService reviewService;

	
	// 관광지 리스트	
	@GetMapping("/touristAreaList")
	public String touristAreaList(Model model) {
		List<TouristAreaListDto> touristAreaList = contentsService.findTouristArea("관광지");
		model.addAttribute("touristAreaList", touristAreaList);
		return "contents/touristAreaList";
	}
	
	// 관광지 상세보기
	@GetMapping("/touristAreaDetail/{contentsId}")
	public String touristAreaDetail(@PathVariable("contentsId") int contentsId, Model model) {
		TouristAreaDetailDto touristAreaDetail = contentsService.touristAreaDetail(contentsId);
		List<ReviewDto> review = reviewService.showReview(contentsId);
		model.addAttribute("touristAreaDetail", touristAreaDetail);
		model.addAttribute("review",review);
		return "contents/touristAreaDetail";
	}
	
	// 맛집 리스트
	@GetMapping("/restaurantList")
	public String restaurantList(Model model) {
		List<RestaurantListDto> restaurantList = contentsService.findRestaurant("음식점");
		model.addAttribute("restaurantList", restaurantList);
		return "contents/restaurantList";
	}
	
	// 맛집 상세보기
	@GetMapping("/restaurantDetail/{contentsId}")
	public String restaurantDetail(@PathVariable("contentsId") int contentsId, Model model) {
		RestaurantDetailDto restaurantDetail = contentsService.restaurantDetail(contentsId);
		model.addAttribute("restaurantDetail", restaurantDetail);
		return "contents/restaurantDetail";
	}
	
	// 숙소 리스트
	@GetMapping("/lodgingList")
	public String lodgingList(Model model) {
		List<LodgingListDto> lodgingList = contentsService.findLodging("숙박");
		model.addAttribute("lodgingList", lodgingList);
		return "contents/lodgingList";
	}
	
	// 숙소 상세보기
	@GetMapping("/lodgingDetail/{contentsId}")
	public String lodgingDetail(@PathVariable("contentsId") int contentsId, Model model) {
		LodgingDetailDto lodgingDetail = contentsService.lodgingDetail(contentsId);	
		model.addAttribute("lodgingDetail", lodgingDetail);
		return "contents/lodgingDetail";
	}
	
	// 쇼핑 리스트
	@GetMapping("/shoppingList")
	public String shoppingList(Model model) {
		List<ShoppingListDto> shoppingList = contentsService.findShopping("쇼핑");
		model.addAttribute("shoppingList", shoppingList);
		return "contents/shoppingList";
	}
	
	// 쇼핑 상세보기
	@GetMapping("/shoppingDetail/{contentsId}")
	public String shoppingDetail(@PathVariable("contentsId") int contentsId, Model model) {
		ShoppingDetailDto shoppingDetail = contentsService.shoppingDetail(contentsId);		
		model.addAttribute("shoppingDetail", shoppingDetail);
		return "contents/shoppingDetail";
	}
	
}
