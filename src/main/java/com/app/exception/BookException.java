package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class BookException extends ResponseStatusException {
	
	//constructor that taking status code and error message
	public BookException(HttpStatus status,String msg){
		super(status, msg);
	}
	
}
