package com.example.blogAPI.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blogAPI.config.AppConstants;
import com.example.blogAPI.config.ModelsMapping;
import com.example.blogAPI.entities.Role;
import com.example.blogAPI.entities.User;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.payloads.UserDTO;
import com.example.blogAPI.repositories.RoleRepository;
import com.example.blogAPI.repositories.UserRepository;
import com.example.blogAPI.services.UserService;


@Service
public class UserServicesImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelsMapping modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public UserDTO createUser(UserDTO userdto) {
		User user = this.dtoToUser(userdto);
		User savedUser = this.userRepo.save(user);
		return this.userToDTO(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto, Integer userId) {

			User userEntity = userRepo.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User","id",userId));
			userEntity.setUserName(userdto.getUserName());
			userEntity.setEmail(userdto.getEmail());
			userEntity.setPassword(userdto.getPassword());
			userEntity.setAbout(userdto.getAbout());
			
			User updatedUser = userRepo.save(userEntity);
			UserDTO userdtoSend = userToDTO(updatedUser);
		return userdtoSend;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User userEntity = userRepo.findById(userId).orElseThrow(() ->  new ResourceNotFoundException("User","id",userId));
		return userToDTO(userEntity);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepo.findAll();
		List<UserDTO> userDTO = users.stream().map(user -> userToDTO(user)).collect(Collectors.toList());
		return userDTO;
	}

	@Override
	public void deleteUser(Integer userId) {
			if(userRepo.existsById(userId)) {
				userRepo.deleteById(userId);
			}else {
				throw new ResourceNotFoundException("User","id",userId);
			}
	}
	
	private User dtoToUser(UserDTO userDTO) {
		User user = modelMapper.modelMapper().map(userDTO, User.class);
		/*
		 * user.setUserId(userDTO.getUserId()); user.setUserName(userDTO.getUserName());
		 * user.setPassword(userDTO.getPassword()); user.setEmail(userDTO.getEmail());
		 * user.setAbout(userDTO.getAbout());
		 */
		return user;
	}
	
	private UserDTO userToDTO(User user) {
		UserDTO dto = modelMapper.modelMapper().map(user, UserDTO.class);
		/*
		 * dto.setUserId(user.getUserId()); dto.setUserName(user.getUserName());
		 * dto.setEmail(user.getEmail()); dto.setPassword(user.getPassword());
		 * dto.setAbout(user.getAbout());
		 */
		return dto;
		
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user = modelMapper.modelMapper().map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = userRepo.save(user);
		return modelMapper.modelMapper().map(newUser, UserDTO.class);
	}
}
