package com.cos.shop.config.oauth.provider;

import java.util.Map;

public class FacebookUserInfo implements OAuthUserinfo{
	
// 생성자 주입
	
	private Map<String, Object>attributes;
	
	public FacebookUserInfo(Map<String, Object>attributes) {
		this.attributes = attributes;
	}
	

	@Override
	public String getProvider_id() {
		// TODO Auto-generated method stub
		return (String) attributes.get("id");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub  
		return "facebook";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String) attributes.get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String)attributes.get("name");
	}

}
