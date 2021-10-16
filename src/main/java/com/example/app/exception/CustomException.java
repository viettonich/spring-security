package com.example.app.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4379744266949643179L;

	private String message;

	private HttpStatus status;

	public CustomException() {
		super();
	}
	
	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public CustomException(String message, HttpStatus status) {
		super(message);
		this.message = message;
		this.status = status;
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

}
