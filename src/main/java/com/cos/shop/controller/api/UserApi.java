package com.cos.shop.controller.api;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cos.shop.dto.UserDto;
import com.cos.shop.service.UserService;

@RestController
public class UserApi {
	
	@Autowired
	private UserService userService;

	// 회원가입
	@PostMapping("/auth/joinProc")
    public ResponseEntity<UserDto> join(@Valid @RequestBody UserDto userDto) {
      
        userService.save(userDto);
        
        return new ResponseEntity<UserDto>(HttpStatus.OK);
    }
	
	// 중복검사 
	
	@GetMapping("/checkUsername")
    public ResponseEntity<?> checkUsernameDuplication(@RequestParam("username") String username) {
        // 사용자명 중복 검사 로직 구현
        boolean isDuplicated = userService.isUsernameDuplicated(username);

        // 중복 검사 결과 반환
        Map<String, Boolean> response = new HashMap<>();
        response.put("duplicated", isDuplicated);
        return ResponseEntity.ok(response);
    }

	

}
