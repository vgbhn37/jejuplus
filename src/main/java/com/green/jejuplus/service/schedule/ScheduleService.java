package com.green.jejuplus.service.schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.schedule.ScheduleDetailDto;
import com.green.jejuplus.dto.schedule.ScheduleItemDto;
import com.green.jejuplus.handler.exception.CustomException;
import com.green.jejuplus.repository.interfaces.ScheduleRepository;
import com.green.jejuplus.repository.model.Contents;
import com.green.jejuplus.repository.model.Schedule;
import com.green.jejuplus.repository.model.User;
import com.green.jejuplus.util.Define;
import com.green.jejuplus.util.PagingDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ScheduleService {

	@Autowired
	ScheduleRepository scheduleRepository;

	public List<Contents> findAllList(PagingDto paging) {

		List<Contents> list = scheduleRepository.findAllList(paging);
		addHashTag(list);

		return list;

	}
	
	public List<Contents> findListByLabel(String label, User user, PagingDto paging) {
		
		List<Contents> list = null;
		
		log.info(label);
		switch (label) {

		case "attraction":
			list = scheduleRepository.findListByLabel(Define.ATTRACTION, paging);
			break;
		case "accomodation":
			list = scheduleRepository.findListByLabel(Define.ACCOMODATION, paging);
			break;
		case "shopping":
			list = scheduleRepository.findListByLabel(Define.SHOPPING, paging);
			break;
		case "restaurant":
			list = scheduleRepository.findListByLabel(Define.RESTAURANT, paging);
			break;
		case "favorite":
			list = scheduleRepository.findMyFavoriteList(user.getUserId(), paging);
			break;
		default:
			list = scheduleRepository.findAllList(paging);
			break;
		}	
		
		addHashTag(list);
		log.info(Integer.toString(list.size()));
		log.info(Integer.toString(user.getUserId()));
	
		
		return list;

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
		default:
			return scheduleRepository.findCountAll();
		}
	}

	public int countSearchList(String category, String keyword) {

		int count = 0;
		if (category.equals("title")) {
			count = scheduleRepository.findCountBySearchTitle(keyword);
		} else if (category.equals("tag")) {
			count = scheduleRepository.findCountBySearchTag(keyword);
		} else {
			throw new CustomException("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
		}
		return count;
	}

	public List<Contents> findContentsBySearch(String category, String search, PagingDto paging) {
		
		List<Contents> list = null;
		
		if (category.equals("title")) {
			list = scheduleRepository.findContentsBySearchTitle(search, paging);
		} else if (category.equals("tag")) {
			list =  scheduleRepository.findContentsBySearchTag(search, paging);
		} else {
			throw new CustomException("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
		}
		
		addHashTag(list);
		return list;

	}

	public Schedule findScheduleById(Integer scheduleId) {
		
		return scheduleRepository.findScheduleById(scheduleId);
	}
	
	public String insertScheduleDetail(List<ScheduleDetailDto> scheduleDetailDto) {
		
		for (ScheduleDetailDto dto : scheduleDetailDto) {
			int result = scheduleRepository.insertScheduleDetail(dto);
			if(result!= 1) {
				return "fail";
			}
		}
		
		return "success";
	}
	
	public void deleteScheduleDetailByDay(ScheduleDetailDto scheduleDetailDto) {
		
		scheduleRepository.deleteScheduleDetailByDay(scheduleDetailDto);
	}
	
	public List<ScheduleItemDto> requestList(Integer scheduleId, Integer itemDay){
		
		return scheduleRepository.findScheduleDetailByDay(scheduleId, itemDay);
	}
	
	//해쉬태그(#)를 붙여줌
	public void addHashTag(List<Contents> list) {

		StringBuilder sb = new StringBuilder();
		for (Contents contents : list) {
			String tag = contents.getTag();
			String[] tags = tag.split(",");
			for (String tagElement : tags) {
				sb.append("#").append(tagElement).append(" ");
			}
			String result = sb.substring(0, sb.length() - 1);
			contents.setTag(result);
			sb.setLength(0);
		}
	}

}
