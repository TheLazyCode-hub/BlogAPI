package com.example.blogAPI.exceptions;

public class APIException extends RuntimeException {

	public APIException(String message) {
		super(message);
	}

	public APIException() {
	}
}
