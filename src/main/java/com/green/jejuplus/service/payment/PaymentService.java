package com.green.jejuplus.service.payment;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.jejuplus.dto.payment.PaymentDTO;
import com.green.jejuplus.repository.interfaces.PaymentRepository;
import com.green.jejuplus.repository.model.Payment;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Transactional
	public void insertPayment(PaymentDTO paymentDTO, int principalId) {
		System.out.println("[service] paymentDTO start : " + paymentDTO);
		
		Payment payment = new Payment();
		payment.setPaymentId(paymentDTO.getPaymentId());
		payment.setUserId(principalId);
		payment.setPgTid(paymentDTO.getPgTid());
		
		System.out.println("[service] paymentDTO end : " + paymentDTO);
		
		int result = paymentRepository.insert(paymentDTO);
		
		
	}
}
