package com.example.app.repository;

import lombok.Data;

@Data
public class ErrorResponse {
	private String message;
	private String errorCode;
	private int status;
	private String path;

}
