package com.cos.shop.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.shop.model.User;
import com.cos.shop.repository.UserRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User principal = userRepository.findByUsername(username);
	   
	    if (principal == null) {
	        throw new UsernameNotFoundException("User not found with username: " + username);    
	    }
	    System.out.println("username"+username);
	    return new PrincipalDetails(principal); // 시큐리티의 세션에 유저 정보가 저장됨
	}

}
	
	
