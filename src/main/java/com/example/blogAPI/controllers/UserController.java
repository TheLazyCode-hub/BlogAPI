package com.example.blogAPI.controllers;


import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.ColumnTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogAPI.payloads.APIresponse;
import com.example.blogAPI.payloads.UserDTO;
import com.example.blogAPI.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//GET
	@RequestMapping(method = RequestMethod.GET,value = "/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> userdto = userService.getAllUsers();
		return new ResponseEntity<>(userdto,HttpStatus.OK);
	}
	
	//Get by id
	@RequestMapping(method = RequestMethod.GET,value = "/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Integer userId){
		UserDTO userdto = userService.getUserById(userId);
		return new ResponseEntity<>(userdto,HttpStatus.OK);
	}
	
	//POST
	@RequestMapping(method = RequestMethod.POST,value="/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userdto){
		UserDTO createdUserdto = userService.createUser(userdto);
		return new ResponseEntity<UserDTO>(createdUserdto,HttpStatus.CREATED);
	}
	
	//UPDATE
	@RequestMapping(method = RequestMethod.PUT,value = "/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userdto,@PathVariable("userId") Integer userId){
		UserDTO updatedUser = userService.updateUser(userdto, userId);
		return new ResponseEntity<UserDTO>(updatedUser,HttpStatus.OK);
	}
	
	//DELETE
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE,value = "/{userId}")
	public ResponseEntity<APIresponse> deletUser(@PathVariable("userId") Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<APIresponse>(new APIresponse("User deletion",true), HttpStatus.OK);
	}
	
}
