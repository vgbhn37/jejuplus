package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.jejuplus.dto.MainRestaurantDto;

@Mapper
public interface MainRepository {

	List<MainRestaurantDto> findRestaurantAll();

	List<MainRestaurantDto> findPlaceAll();

}
