package com.example.blogAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.blogAPI.config.AppConstants;
import com.example.blogAPI.entities.Role;
import com.example.blogAPI.repositories.RoleRepository;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner{
	@Autowired
	RoleRepository roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		List<Role> roles = new ArrayList<>();
		roles.add(new Role(AppConstants.ADMIN_USER,"ADMIN_USER"));
		roles.add(new Role(AppConstants.NORMAL_USER,"NORMAL_USER"));
		roleRepo.saveAll(roles);
	}
	
}
