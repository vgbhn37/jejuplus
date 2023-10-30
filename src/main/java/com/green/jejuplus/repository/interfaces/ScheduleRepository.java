package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;

@Mapper
public interface ScheduleRepository {

	public List<Contents> findAllList();
	public List<Contents> findListByLabel(String label);
	public List<Contents> findMyFavoriteList(Integer userId);
	public List<Contents> findContentsBySearchTitle(String search);
	public List<Contents> findContentsBySearchTag(String search);
	public Schedule findScheduleById(Integer scheduleId);
}
