package com.green.jejuplus.handler;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.green.jejuplus.handler.exception.CustomException;

/**
 *  예외 발생 시 (Json, Xml)
 *  데이터를 가공해서 내려 줄 수 있다.
 *   
 */
@RestControllerAdvice // IoC 대상 + Aop 대상
@Order(1)
public class MyUserExceptionHandler {

	@ExceptionHandler(Exception.class)
	public void exception(Exception e) {
		System.out.println("-----예외 발생-----");
		System.out.println(e.getMessage());
		System.out.println("-----------------");
	}
	
	@ExceptionHandler(CustomException.class)
	public String basicException(CustomException e) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script>");
		sb.append("alert(' "+ e.getMessage() +" ');");
		sb.append("history.back();");
		sb.append("</script>");
		return sb.toString();
	}
}
