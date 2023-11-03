package com.green.jejuplus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.green.jejuplus.service.payment.KakaoPayService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/payment")
public class PaymentController {


	@Autowired
	private KakaoPayService kakaoPayService;
	
	
	@GetMapping("/kakaopay")
	public String kakaopay() {
		
		return "payment/kakaopay";
	}
	
	@PostMapping("/kakaoPay")
    public String kakaoPay() {
		System.out.println("kakaoPay post...");
        
        return "redirect:" + kakaoPayService.kakaoPayReady();
 
    }
	
	@GetMapping("/kakaopaysuccess")
    public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        System.out.println("kakaoPaySuccess get...");
        System.out.println("kakaoPaySuccess pg_token : " + pg_token);
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);
        
        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token));
        
    }
}






