package com.cos.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.shop.dto.UserDto;
import com.cos.shop.model.RoleType;
import com.cos.shop.model.User;
import com.cos.shop.repository.UserRepository;

@Service
public class UserService<userRepository> {

	@Autowired
	  private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	
	// 회원가입 
	@Transactional
	public void save(UserDto userDto) {
		
		String rawPassword = userDto.getPassword();
		String encPassword = encoder.encode(rawPassword);
		
		User user = new User();

		user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(encPassword);
        user.setProvider("gender");
        user.setRole(RoleType.USER);
		
		userRepository.save(user);	
		
	}

  // 중복검사 
	@Transactional
	public boolean isUsernameDuplicated(String username) {
		
		return userRepository.existsByUsername(username);
	}

}
