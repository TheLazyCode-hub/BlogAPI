package com.example.blogAPI.payloads;

public class APIresponse {
	private String message;
	private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public APIresponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	

}
