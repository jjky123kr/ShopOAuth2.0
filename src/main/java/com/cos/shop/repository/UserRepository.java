package com.cos.shop.repository;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.shop.dto.UserDto;
import com.cos.shop.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	public User findByUsername(String username);
	
	boolean existsByUsername(String username); // username 중복검사



}
