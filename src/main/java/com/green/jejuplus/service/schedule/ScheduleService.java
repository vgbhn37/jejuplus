package com.green.jejuplus.service.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.repository.interfaces.ScheduleRepository;
import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;

@Service
public class ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	public List<Contents> findAllList() {
		
		List<Contents> list = scheduleRepository.findAllList();
		StringBuilder sb = new StringBuilder();
		for (Contents contents : list) {
			String tag = contents.getTag();
			String[] tags = tag.split(",");
			for (String tagElement : tags) {
				sb.append("#").append(tagElement).append(" ");
			}
			String result = sb.substring(0, sb.length()-1);
			contents.setTag(result);
			sb.setLength(0);
		}
		
		return list;
		
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
	
	public Schedule findScheduleById(Integer scheduleId) {
		
		return scheduleRepository.findScheduleById(scheduleId);
	}
	
	
	
}
