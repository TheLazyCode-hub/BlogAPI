package com.example.blogAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blogAPI.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
