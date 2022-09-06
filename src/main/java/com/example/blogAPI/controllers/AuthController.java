package com.example.blogAPI.controllers;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.blogAPI.exceptions.APIException;
import com.example.blogAPI.payloads.JWTAuthRequest;
import com.example.blogAPI.payloads.JWTAuthResponse;
import com.example.blogAPI.payloads.UserDTO;
import com.example.blogAPI.security.JWTTokenHelper;
import com.example.blogAPI.services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private JWTTokenHelper tokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/login")
	public ResponseEntity<JWTAuthResponse> createToken(@RequestBody JWTAuthRequest request){
		 authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		String generatedToken = tokenHelper.generateToken(userDetails);
		JWTAuthResponse authResponse = new JWTAuthResponse();
		authResponse.setToken(generatedToken);
		return new ResponseEntity<JWTAuthResponse>(authResponse,HttpStatus.OK);
	}


	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			authenticationManager.authenticate(authToken);
		} catch (BadCredentialsException e) {
			throw new APIException("Invalid username or password!!!");
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDto){
		 UserDTO user = userService.registerUser(userDto);
		return new ResponseEntity<UserDTO>(user,HttpStatus.OK);
	}
}
