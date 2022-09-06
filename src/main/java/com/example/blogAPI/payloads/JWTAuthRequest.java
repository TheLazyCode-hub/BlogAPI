package com.example.blogAPI.payloads;

public class JWTAuthRequest {
	private String username;
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JWTAuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public JWTAuthRequest() {
		super();
	}
	
}
