package com.org.employeemgmt.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.org.employeemgmt.vo.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FileNotSupportedException.class)
	public final ResponseEntity<ErrorDetails> handleFileFormatException(FileNotSupportedException ex) {
		ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST,"System supports text/csv and text/plain types",ex.getMessage(),new Date());
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
}