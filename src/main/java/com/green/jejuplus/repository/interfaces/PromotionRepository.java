package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.jejuplus.dto.admin.AdminPromotionDto;
import com.green.jejuplus.repository.model.Promotion;
import com.green.jejuplus.repository.model.PromotionImg;

@Mapper
public interface PromotionRepository {

	void savePromotion(Promotion promotion);

	void savePromotionImg(PromotionImg image);

	List<AdminPromotionDto> getPromotions();

	String getImageUrlsByPromotionId(int promotionId);

	List<AdminPromotionDto> searchPromotions(@Param("search") String search,@Param("pageSize") int pageSize,@Param("offset") int offset);

	int countPromotionsWithSearch(String search);


	List<AdminPromotionDto> findPromotions(@Param("promotionsPerPage")int promotionsPerPage,@Param("offset") int offset);

	int count();

}
