package com.cos.shop.config.oauth.provider;

import java.util.Map;

public class NaverUserInfo implements OAuthUserinfo {

	// 생성자 주입
	
	private Map<String, Object>attribtues;
	
	public NaverUserInfo(Map<String, Object>attribtues) {
		this.attribtues = attribtues;
	}
	
	
	@Override
	public String getProvider_id() {
		// TODO Auto-generated method stub
		return (String)((Map)attribtues.get("response")).get("id");
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "naver";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String)((Map)attribtues.get("response")).get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) ((Map)attribtues.get("response")).get("name");
	}
	
	
	

}
