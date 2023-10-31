package com.green.jejuplus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.ShoppingDetailDto;
import com.green.jejuplus.dto.ShoppingListDto;

import com.green.jejuplus.dto.LodgingDetailDto;
import com.green.jejuplus.dto.LodgingListDto;
import com.green.jejuplus.dto.RestaurantDetailDto;
import com.green.jejuplus.dto.RestaurantListDto;
import com.green.jejuplus.dto.TouristAreaDetailDto;
import com.green.jejuplus.dto.TouristAreaListDto;
import com.green.jejuplus.repository.interfaces.ContentsRepository;

@Service
public class ContentsService {
	
	@Autowired
	private ContentsRepository contentsRepository;

	public List<TouristAreaListDto> findTouristArea(String contentsLabel) {
		List<TouristAreaListDto> list = contentsRepository.findTouristAreaList(contentsLabel);
		return list;
	}
	public TouristAreaDetailDto touristAreaDetail(int contentsId) {
		TouristAreaDetailDto touristAreaDetailDto = contentsRepository.showTouristAreaDetail(contentsId);
		return touristAreaDetailDto;
	}

	public List<RestaurantListDto> findRestaurant(String contentsLabel) {
		List<RestaurantListDto> list = contentsRepository.findRestaurantList(contentsLabel);
		return list;
	}
	
	public RestaurantDetailDto restaurantDetail(int contentsId) {
		RestaurantDetailDto restaurantDetailDto = contentsRepository.showRestaurantDetail(contentsId);
		return restaurantDetailDto;
	}
	
	public List<LodgingListDto> findLodging(String contentsLabel) {
		List<LodgingListDto> list = contentsRepository.findLodgingList(contentsLabel);
		return list;
	}

	public LodgingDetailDto lodgingDetail(int contentsId) {
		LodgingDetailDto lodgingDetailDto = contentsRepository.showLodgingDetail(contentsId);
		return lodgingDetailDto;
	}
	
	public List<ShoppingListDto> findShopping(String contentsLabel) {
		List<ShoppingListDto> list = contentsRepository.findShoppingList(contentsLabel);
		return list;
	}
	
	public ShoppingDetailDto shoppingDetail(int contentsId) {
		ShoppingDetailDto shoppingDetailDto = contentsRepository.showShoppingDetail(contentsId);
		return shoppingDetailDto;
	}

}
