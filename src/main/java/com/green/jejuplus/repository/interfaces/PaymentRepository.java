package com.green.jejuplus.repository.interfaces;

import org.apache.ibatis.annotations.Mapper;

import com.green.jejuplus.dto.payment.PaymentDTO;

@Mapper
public interface PaymentRepository {
	
	public int insert(PaymentDTO paymentDTO);
}
