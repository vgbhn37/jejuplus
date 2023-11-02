package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.green.jejuplus.dto.contents.FavoriteDto;
import com.green.jejuplus.dto.contents.LodgingDetailDto;
import com.green.jejuplus.dto.contents.LodgingListDto;
import com.green.jejuplus.dto.contents.RestaurantDetailDto;
import com.green.jejuplus.dto.contents.RestaurantListDto;
import com.green.jejuplus.dto.contents.ShoppingDetailDto;
import com.green.jejuplus.dto.contents.ShoppingListDto;
import com.green.jejuplus.dto.contents.TouristAreaDetailDto;
import com.green.jejuplus.dto.contents.TouristAreaListDto;

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
	public List<FavoriteDto> selectFavoriteList(@Param("userId") Integer userId);
}