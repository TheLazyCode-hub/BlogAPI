package com.example.blogAPI.payloads;

public class JWTAuthResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JWTAuthResponse(String token) {
		super();
		this.token = token;
	}

	public JWTAuthResponse() {
		super();
	}
	
}
