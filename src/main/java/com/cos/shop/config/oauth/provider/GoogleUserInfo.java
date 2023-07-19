package com.cos.shop.config.oauth.provider;

import java.util.Map;

public class GoogleUserInfo implements OAuthUserinfo {
// 생성자 주입 
	
	private Map<String, Object>attirbutes;  //oauth2User.getAttribute();
	
	
	public GoogleUserInfo(Map<String, Object>attributes) {
		this.attirbutes = attributes;
	}
	
	
	@Override
	public String getProvider_id() {
		
		return (String) attirbutes.get("sub");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "google";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String) attirbutes.get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String)attirbutes.get("name");
	}

}
