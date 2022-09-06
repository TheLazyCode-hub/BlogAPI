package com.example.blogAPI.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blogAPI.payloads.UserDTO;


public interface UserService {
	UserDTO registerUser(UserDTO user);
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userId);
}
