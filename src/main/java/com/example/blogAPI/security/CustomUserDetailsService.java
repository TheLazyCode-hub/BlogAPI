package com.example.blogAPI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.blogAPI.entities.User;
import com.example.blogAPI.exceptions.ResourceNotFoundException;
import com.example.blogAPI.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading db or test condition here only
		User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("Email", username, 0));
		return user;
	}

}
