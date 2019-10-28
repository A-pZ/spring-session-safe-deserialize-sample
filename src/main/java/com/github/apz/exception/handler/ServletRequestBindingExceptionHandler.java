package com.github.apz.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ServletRequestBindingExceptionHandler {
	@ExceptionHandler(ServletRequestBindingException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle(ServletRequestBindingException e) {
		log.warn(e);
		return "index";
	}
}
