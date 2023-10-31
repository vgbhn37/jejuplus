package com.green.jejuplus.service.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.repository.interfaces.ScheduleRepository;
import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.util.Define;
import com.green.jejuplus.util.PagingDto;

@Service
public class ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;
	
	public List<Contents> findAllList(PagingDto paging) {
		
		List<Contents> list = scheduleRepository.findAllList(paging);
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
	
	public List<Contents> findListByLabel(String label,User user, PagingDto paging){
		
		switch (label) {

		case "attraction":
			return scheduleRepository.findListByLabel(Define.ATTRACTION, paging);
		case "accomodation":
			return scheduleRepository.findListByLabel(Define.ACCOMODATION, paging);
		case "shopping":
			return scheduleRepository.findListByLabel(Define.SHOPPING ,paging);
		case "restaurant":
			return scheduleRepository.findListByLabel(Define.RESTAURANT, paging);
		case "favorite":
			return scheduleRepository.findMyFavoriteList(user.getUserId(), paging);
		default :
			return scheduleRepository.findAllList(paging);
		}
		
	}
	
	public int countList(String label, User user) {
		
		switch (label) {

		case "attraction":
			return scheduleRepository.findCountByLabel(Define.ATTRACTION);
		case "accomodation":
			return scheduleRepository.findCountByLabel(Define.ACCOMODATION);
		case "shopping":
			return scheduleRepository.findCountByLabel(Define.SHOPPING);
		case "restaurant":
			return scheduleRepository.findCountByLabel(Define.RESTAURANT);
		case "favorite":
			return scheduleRepository.findCountByMyFavorite(user.getUserId());
		default :
			return scheduleRepository.findCountAll();
		}
	}
	
	
	public List<Contents> findContentsBySearchTitle(String search, PagingDto paging){
		
		return scheduleRepository.findContentsBySearchTitle(search,paging);
	}
	
	public Schedule findScheduleById(Integer scheduleId) {
		
		return scheduleRepository.findScheduleById(scheduleId);
	}
	
	
	
}
