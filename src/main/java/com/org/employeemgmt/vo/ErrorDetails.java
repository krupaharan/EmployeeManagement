package com.org.employeemgmt.vo;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private HttpStatus httpStatus;
	private String message;
	private String details;
	private Date timeStamp;
	
	public ErrorDetails() {
		
	}
	
	public ErrorDetails(HttpStatus httpStatus, String message, String details, Date timeStamp) {
		this.message = message;
		this.details = details;
		this.timeStamp = timeStamp;
		this.httpStatus = httpStatus;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}