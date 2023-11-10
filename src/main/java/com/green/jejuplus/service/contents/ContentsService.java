package com.green.jejuplus.service.contents;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.green.jejuplus.handler.exception.CustomRestfulException;
import com.green.jejuplus.repository.interfaces.ContentsRepository;
import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Favorite;
import com.green.jejuplus.util.PagingDto;

@Service
public class ContentsService {
	
	@Autowired
	private ContentsRepository contentsRepository;

	@Transactional
	public List<TouristAreaListDto> findTouristArea( PagingDto paging) {
	List<TouristAreaListDto> list = contentsRepository.findTouristAreaList(paging);
        StringBuilder sb = new StringBuilder();
        for (TouristAreaListDto contents : list) {
            String tag = contents.getTag();
            String[] tags = tag.split(",");
            for (String tagElement : tags) {
                sb.append("#").append(tagElement).append(" ");
            }
            String result = sb.substring(0, sb.length() - 1);
            contents.setTag(result);
            sb.setLength(0);
        }
		return list;
	}
	
	@Transactional
	public TouristAreaDetailDto touristAreaDetail(int contentsId) {
		TouristAreaDetailDto touristAreaDetailDto = contentsRepository.showTouristAreaDetail(contentsId);
        StringBuilder sb = new StringBuilder();
        String tag = touristAreaDetailDto.getTag();
        String[] tags = tag.split(",");
        for (String tagElement : tags) {
            sb.append("#").append(tagElement).append(" ");
        }
        String result = sb.substring(0, sb.length() - 1);
        touristAreaDetailDto.setTag(result);
        sb.setLength(0);
		return touristAreaDetailDto;
	}

	@Transactional
	public List<RestaurantListDto> findRestaurant(String contentsLabel) {
		List<RestaurantListDto> list = contentsRepository.findRestaurantList(contentsLabel);
        StringBuilder sb = new StringBuilder();
        for (RestaurantListDto contents : list) {
            String tag = contents.getTag();
            String[] tags = tag.split(",");
            for (String tagElement : tags) {
                sb.append("#").append(tagElement).append(" ");
            }
            String result = sb.substring(0, sb.length() - 1);
            contents.setTag(result);
            sb.setLength(0);
        }
		return list;
	}
	
	@Transactional
	public RestaurantDetailDto restaurantDetail(int contentsId) {
		RestaurantDetailDto restaurantDetailDto = contentsRepository.showRestaurantDetail(contentsId);
        StringBuilder sb = new StringBuilder();
        String tag = restaurantDetailDto.getTag();
        String[] tags = tag.split(",");
        for (String tagElement : tags) {
            sb.append("#").append(tagElement).append(" ");
        }
        String result = sb.substring(0, sb.length() - 1);
        restaurantDetailDto.setTag(result);
        sb.setLength(0);
		return restaurantDetailDto;
	}
	
	@Transactional
	public List<LodgingListDto> findLodging(String contentsLabel) {
		List<LodgingListDto> list = contentsRepository.findLodgingList(contentsLabel);
        StringBuilder sb = new StringBuilder();
        for (LodgingListDto contents : list) {
            String tag = contents.getTag();
            String[] tags = tag.split(",");
            for (String tagElement : tags) {
                sb.append("#").append(tagElement).append(" ");
            }
            String result = sb.substring(0, sb.length() - 1);
            contents.setTag(result);
            sb.setLength(0);
        }
		return list;
	}

	@Transactional
	public LodgingDetailDto lodgingDetail(int contentsId) {
		LodgingDetailDto lodgingDetailDto = contentsRepository.showLodgingDetail(contentsId);
        StringBuilder sb = new StringBuilder();
        String tag = lodgingDetailDto.getTag();
        String[] tags = tag.split(",");
        for (String tagElement : tags) {
            sb.append("#").append(tagElement).append(" ");
        }
        String result = sb.substring(0, sb.length() - 1);
        lodgingDetailDto.setTag(result);
        sb.setLength(0);
		return lodgingDetailDto;
	}
	
	@Transactional
	public List<ShoppingListDto> findShopping(String contentsLabel) {
		List<ShoppingListDto> list = contentsRepository.findShoppingList(contentsLabel);
        StringBuilder sb = new StringBuilder();
        for (ShoppingListDto contents : list) {
            String tag = contents.getTag();
            String[] tags = tag.split(",");
            for (String tagElement : tags) {
                sb.append("#").append(tagElement).append(" ");
            }
            String result = sb.substring(0, sb.length() - 1);
            contents.setTag(result);
            sb.setLength(0);
        }
		return list;
	}
	
	@Transactional
	public ShoppingDetailDto shoppingDetail(int contentsId) {
		ShoppingDetailDto shoppingDetailDto = contentsRepository.showShoppingDetail(contentsId);
        StringBuilder sb = new StringBuilder();
        String tag = shoppingDetailDto.getTag();
        String[] tags = tag.split(",");
        for (String tagElement : tags) {
            sb.append("#").append(tagElement).append(" ");
        }
        String result = sb.substring(0, sb.length() - 1);
        shoppingDetailDto.setTag(result);
        sb.setLength(0);
		return shoppingDetailDto;
	}

	@Transactional
	public List<FavoriteDto> selectFavotiteList(Integer userId) {
		return contentsRepository.selectFavoriteList(userId);
	}

	public int countTouristArea() {

		return contentsRepository.countTouristArea();
	}

}
