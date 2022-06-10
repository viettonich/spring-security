package com.example.app.ui.response;

import lombok.Data;

@Data
public class ErrorResponse {
	private String message;
	private String errorCode;
	private int status;
	private String path;

}
