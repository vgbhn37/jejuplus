package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import com.green.jejuplus.dto.ShoppingDetailDto;
import com.green.jejuplus.dto.ShoppingListDto;
import com.green.jejuplus.dto.LodgingDetailDto;
import com.green.jejuplus.dto.LodgingListDto;
import com.green.jejuplus.dto.RestaurantDetailDto;
import com.green.jejuplus.dto.RestaurantListDto;
import com.green.jejuplus.dto.TouristAreaDetailDto;
import com.green.jejuplus.dto.TouristAreaListDto;

@Mapper
public interface ContentsRepository {
	
	public List<TouristAreaListDto> findTouristAreaList(String contentsLabel);
	public List<RestaurantListDto> findRestaurantList(String contentsLabel);
	public List<LodgingListDto> findLodgingList(String contentsLabel);
	public TouristAreaDetailDto showTouristAreaDetail(int contentsId);
	public RestaurantDetailDto showRestaurantDetail(int contentsId);
	public LodgingDetailDto showLodgingDetail(int contentsId);
	public List<ShoppingListDto> findShoppingList(String contentsLabel);
	public ShoppingDetailDto showShoppingDetail(int contentsId);


}
