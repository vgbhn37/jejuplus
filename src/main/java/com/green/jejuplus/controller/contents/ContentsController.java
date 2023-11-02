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

import com.green.jejuplus.dto.contents.FavoriteDto;
import com.green.jejuplus.dto.contents.FavoriteRequestDto;
import com.green.jejuplus.dto.contents.LodgingDetailDto;
import com.green.jejuplus.dto.contents.LodgingListDto;
import com.green.jejuplus.dto.contents.RestaurantDetailDto;
import com.green.jejuplus.dto.contents.RestaurantListDto;
import com.green.jejuplus.dto.contents.ReviewDto;
import com.green.jejuplus.dto.contents.ShoppingDetailDto;
import com.green.jejuplus.dto.contents.ShoppingListDto;
import com.green.jejuplus.dto.contents.TouristAreaDetailDto;
import com.green.jejuplus.dto.contents.TouristAreaListDto;
import com.green.jejuplus.repository.model.Favorite;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.service.contents.ContentsService;
import com.green.jejuplus.service.contents.FavoriteService;
import com.green.jejuplus.service.contents.ReviewService;
import com.green.jejuplus.util.Define;


@Controller
@RequestMapping("/contents")
public class ContentsController {
	
	@Autowired
	private HttpSession session;
	@Autowired
	private ContentsService contentsService;
	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FavoriteService favoriteServie;

	
	// 관광지 리스트	
	@GetMapping("/touristAreaList")
	public String touristAreaList(Model model) {
		List<TouristAreaListDto> touristAreaList = contentsService.findTouristArea("관광지");
		model.addAttribute("touristAreaList", touristAreaList);
		return "contents/touristAreaList";
	}
	
	// 관광지 상세보기
	@GetMapping("/touristAreaDetail/{contentsId}")
	public String touristAreaDetail(@PathVariable("contentsId") int contentsId, Model model, FavoriteRequestDto favoriteRequestDto) {
		User principal = (User) session.getAttribute(Define.PRINCIPAL);
		TouristAreaDetailDto touristAreaDetail = contentsService.touristAreaDetail(contentsId);
		List<ReviewDto> review = reviewService.showReview(contentsId);
		
		if (principal != null) {
			boolean isFavorite = favoriteServie.selectFavorite(principal.getUserId(), contentsId);
			model.addAttribute("isFavorite", isFavorite);
		}

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
