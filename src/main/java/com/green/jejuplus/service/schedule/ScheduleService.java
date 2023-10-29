package com.green.jejuplus.service.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.repository.interfaces.ScheduleRepository;
import com.green.jejuplus.repository.model.Contents;

@Service
public class ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	public List<Contents> findAllList() {
		
		return scheduleRepository.findAllList();
		
	}
	
	public List<Contents> findListByLabel(String label){
		
		return scheduleRepository.findListByLabel(label);
	}
	
	public List<Contents> findMyFavoriteList(Integer userId){
		
		return scheduleRepository.findMyFavoriteList(userId);
	}
	
	public List<Contents> findContentsBySearchTitle(String search){
		
		return scheduleRepository.findContentsBySearchTitle(search);
	}
	
	
	
}
