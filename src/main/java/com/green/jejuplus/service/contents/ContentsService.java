package com.green.jejuplus.service.contents;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.contents.FavoriteDto;
import com.green.jejuplus.dto.contents.LodgingDetailDto;
import com.green.jejuplus.dto.contents.LodgingListDto;
import com.green.jejuplus.dto.contents.RestaurantDetailDto;
import com.green.jejuplus.dto.contents.RestaurantListDto;
import com.green.jejuplus.dto.contents.ShoppingDetailDto;
import com.green.jejuplus.dto.contents.ShoppingListDto;
import com.green.jejuplus.dto.contents.TouristAreaDetailDto;
import com.green.jejuplus.dto.contents.TouristAreaListDto;
import com.green.jejuplus.repository.interfaces.ContentsRepository;

@Service
public class ContentsService {
	
	@Autowired
	private ContentsRepository contentsRepository;

	@Transactional
	public List<TouristAreaListDto> findTouristArea(String contentsLabel) {
		List<TouristAreaListDto> list = contentsRepository.findTouristAreaList(contentsLabel);
		return list;
	}
	
	@Transactional
	public TouristAreaDetailDto touristAreaDetail(int contentsId) {
		TouristAreaDetailDto touristAreaDetailDto = contentsRepository.showTouristAreaDetail(contentsId);
		return touristAreaDetailDto;
	}

	@Transactional
	public List<RestaurantListDto> findRestaurant(String contentsLabel) {
		List<RestaurantListDto> list = contentsRepository.findRestaurantList(contentsLabel);
		return list;
	}
	
	@Transactional
	public RestaurantDetailDto restaurantDetail(int contentsId) {
		RestaurantDetailDto restaurantDetailDto = contentsRepository.showRestaurantDetail(contentsId);
		return restaurantDetailDto;
	}
	
	@Transactional
	public List<LodgingListDto> findLodging(String contentsLabel) {
		List<LodgingListDto> list = contentsRepository.findLodgingList(contentsLabel);
		return list;
	}

	@Transactional
	public LodgingDetailDto lodgingDetail(int contentsId) {
		LodgingDetailDto lodgingDetailDto = contentsRepository.showLodgingDetail(contentsId);
		return lodgingDetailDto;
	}
	
	@Transactional
	public List<ShoppingListDto> findShopping(String contentsLabel) {
		List<ShoppingListDto> list = contentsRepository.findShoppingList(contentsLabel);
		return list;
	}
	
	@Transactional
	public ShoppingDetailDto shoppingDetail(int contentsId) {
		ShoppingDetailDto shoppingDetailDto = contentsRepository.showShoppingDetail(contentsId);
		return shoppingDetailDto;
	}

	@Transactional
	public List<FavoriteDto> selectFavotiteList(Integer userId) {
		return contentsRepository.selectFavoriteList(userId);
	}
}
