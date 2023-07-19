package com.cos.shop.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_Id;
	
   
	private String username;
	
	private String password;
	
	private String name;
	
	private String email;
	
	private String provider;
	
	private String provider_Id;
	
	@Enumerated(EnumType.STRING)
	private RoleType role;
	
	@CreationTimestamp // 시간이 자동으로 입력 (현재 )
	private Timestamp createDate;

	


	
	
}
