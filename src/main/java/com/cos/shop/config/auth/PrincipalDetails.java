package com.cos.shop.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.shop.model.User;

public class PrincipalDetails implements UserDetails,OAuth2User {

	private User user;

	private Map<String, Object> attributes;

	// 일반로그인 생성자
	public PrincipalDetails(User user) {
		this.user = user;
	}

	// OAuth2.0 로그인
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	// 계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을 수 있어서 루프를 들어야 하는데 우리는 한개만)
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {

			Collection<GrantedAuthority> collectors = new ArrayList<>();
//				collectors.add(new GrantedAuthority() {
//					
//					@Override
//					public String getAuthority() {
//						// TODO Auto-generated method stub
//						return "ROLE_"+user.getRole(); //ROLE_USER
//					}
//				});

			// 람다식 사용
			collectors.add(() -> {
				return "ROLE_" + user.getRole();
			});
			return collectors;
		}
		
		//OAuth2User 오버라이딩
		@Override
		public Map<String, Object> getAttributes() {
			// TODO Auto-generated method stub
			return attributes;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

}
