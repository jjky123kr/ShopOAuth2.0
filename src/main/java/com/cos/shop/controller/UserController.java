package com.cos.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.shop.model.User;


@Controller
public class UserController {

	@GetMapping({ "", "/" })
	public String index() {
		return "index";
	}

	@GetMapping("/user")
	public @ResponseBody String user() {
		return "User";
	}

	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "관리자";

	}
// 일반 로그인, 회원가입
	@GetMapping("/login")
	public String login() {
		return "User/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "User/join";

	}
//소셜 로그인/ 회원가입
	@GetMapping("/socialJoin")
	public String socialJoin() {
		return "User/socialJoin";
	}
	
	@GetMapping("/socialLogin")
	public String socialLogin() {
		return"User/socialLogin";
	}
	
}
