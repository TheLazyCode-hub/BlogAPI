package com.example.blogAPI.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Size;

import com.example.blogAPI.entities.Role;

public class UserDTO {
	
	private int userId;
	
	@NotEmpty(message = "Name can't be empty")
	@Size(min = 4,message = "Minimum name size should be greater than 4")
	private String userName;
	
	@Email(message = "Wrong Email format")
	private String email;
	
	@NotEmpty
	@Size(min = 8,max=16,message = "Password size should be 8-16 characters length")
	private String password;
	
	private String about;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserDTO(int userId, String userName, String email, String password, String about) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	public UserDTO() {
		super();
	}
	
	private Set<RoleDTO> roles = new HashSet<>();

	public Set<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
	
}
