package com.cos.shop.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;

import com.cos.shop.model.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

	@Pattern(regexp= "^[a-z]{1}[a-z0-9]{5,10}+$",message="아이디영문 숫자 조합 6~10자리")
	@NotBlank(message = "아이디를 입력하세요")
	@Column(nullable = false, length = 20)
	private String username;
	
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	@Column(nullable = false, length = 20)
	private String password;
	
	@NotEmpty(message="필수 입력해 주세요")	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Column(nullable = false, length = 20)
	private String email;
	
    private String provider;
	
	private String provider_Id;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp // 시간이 자동으로 입력 (현재 )
	private Timestamp createDate;

	   private List<String> errors;

	    public void setErrors(List<String> errorMessages) {
	        this.errors = errorMessages;
	    }
}