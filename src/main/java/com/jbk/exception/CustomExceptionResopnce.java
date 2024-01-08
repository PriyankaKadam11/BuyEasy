package com.jbk.exception;

import java.util.Date;

public class CustomExceptionResopnce {

	private String defaultMessage;
	private int statusCode;
	private Date date;

	public CustomExceptionResopnce() {
		super();
	}

	public CustomExceptionResopnce(String defaultMessage, int statusCode, Date date) {
		super();
		this.defaultMessage = defaultMessage;
		this.statusCode = statusCode;
		this.date = date;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
