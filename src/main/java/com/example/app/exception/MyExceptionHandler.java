package com.example.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.app.repository.ErrorResponse;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<ErrorResponse> customExceptionHandle(CustomException ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse();
		if (ex.getStatus() == null) {
			response.setErrorCode(HttpStatus.PAYMENT_REQUIRED.name());
			response.setStatus(HttpStatus.PAYMENT_REQUIRED.value());
		} else {
			response.setErrorCode(ex.getStatus().name());
			response.setStatus(ex.getStatus().value());
		}
		response.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.valueOf(response.getStatus()));
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse> databaseConnectionFailsException(Exception ex, WebRequest request) {
		ErrorResponse response = new ErrorResponse();
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
