package com.jbk.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	Map<String, String> mp = new HashMap<>();

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> exceptionHandler(MethodArgumentNotValidException ex) {
		List<FieldError> fieldErrors = ex.getFieldErrors();
		for (FieldError fe : fieldErrors) {
			String field = fe.getField();
			String defaultMessage = fe.getDefaultMessage();
			mp.put(field, defaultMessage);

		}
		return mp;
	}

	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(code = HttpStatus.CONFLICT)
	public CustomExceptionResopnce resourseAlreadyExist(ResourceAlreadyExistException ex) {

		CustomExceptionResopnce er = new CustomExceptionResopnce();
		er.setDefaultMessage(ex.getMessage());
		er.setStatusCode(HttpStatus.CONFLICT.value());
		er.setDate(new Date());
		return er;
	}

	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public CustomExceptionResopnce somethingWentWrong(SomethingWentWrongException ex) {
		CustomExceptionResopnce cc = new CustomExceptionResopnce();
		cc.setDefaultMessage(ex.getMessage());
		cc.setStatusCode(HttpStatus.BAD_REQUEST.value());
		cc.setDate(new Date());
		return cc;
	}
	
	@ExceptionHandler(ResourseNotExistException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public CustomExceptionResopnce resourseNotExist(ResourseNotExistException ex) {

		CustomExceptionResopnce er = new CustomExceptionResopnce();
		er.setDefaultMessage(ex.getMessage());
		er.setStatusCode(HttpStatus.NOT_FOUND.value());
		er.setDate(new Date());
		return er;
	}

}
