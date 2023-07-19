package com.cos.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cos.shop.config.auth.PrincipalDetailsService;
import com.cos.shop.config.oauth.PrincipalOauth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Lazy
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService ;
	
	//비밀번호 암호화
	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}	
	
	@Bean	
	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "/js/**", "/css/**", "/images/**") 
		.permitAll()
	    .antMatchers("/user/**").authenticated()
	    .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
	    .anyRequest().permitAll()
	    .and()
	    .formLogin()
	    .loginPage("/socialLogin")
	    .loginProcessingUrl("/auth/loginProc")
	    .defaultSuccessUrl("/")
	    .and()
	    .oauth2Login()
	    .loginPage("/socialLogin")
	    .userInfoEndpoint()
	    .userService(principalOauth2UserService); 
		return http.build();
	}
	
	
}
