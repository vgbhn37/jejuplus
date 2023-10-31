package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;
import com.green.jejuplus.util.PagingDto;

@Mapper
public interface ScheduleRepository {
	
	//select
	public List<Contents> findAllList(PagingDto paging);
	public List<Contents> findListByLabel(@Param("label")String label, @Param("paging")PagingDto paging);
	public List<Contents> findMyFavoriteList(@Param("userId") Integer userId, @Param("paging")PagingDto paging);
	public List<Contents> findContentsBySearchTitle(@Param("search") String search, @Param("paging") PagingDto paging);
	public List<Contents> findContentsBySearchTag(@Param("search") String search, @Param("paging") PagingDto paging);
	public Schedule findScheduleById(Integer scheduleId);
	public int findCountByLabel(String label);
	public int findCountAll();
	public int findCountByMyFavorite(Integer userId);
	public int findCountBySearchTitle(String search);
	public int findCountBySearchTag(String search);
	
}
