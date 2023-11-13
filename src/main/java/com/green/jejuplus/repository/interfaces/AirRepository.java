package com.green.jejuplus.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.green.jejuplus.repository.model.Air;

@Mapper
public interface AirRepository {
	
	public int insertAir(Air air);
	public Air selectAir(int airId);

}
