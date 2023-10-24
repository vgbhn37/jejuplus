package com.green.jejuplus.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.green.jejuplus.handler.exception.CustomException;

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
