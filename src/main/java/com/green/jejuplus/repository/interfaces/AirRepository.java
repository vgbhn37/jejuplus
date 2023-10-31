package com.green.jejuplus.repository.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.jejuplus.repository.model.Air;


@Mapper
public interface AirRepository {
	
	public List<Air> findAll();
	
	public void insert(Air air);	// 데이터 저장
	
	public Air findById(int airId);	// 특정 ID에 해당하는 데이터 조회

	public void delete(Air air);	// db 삭제

	
	public List<Air> findByDepAirportNmAndArrAirportNm(String depAirportNm, String arrAirportNm);	// 특정 조건에 맞는 db 조회

}
