package com.cos.shop.config.oauth.provider;

import java.util.Map;

public class KakaoUserInfo implements OAuthUserinfo {
	
  // 생성자 주입
  private Map<String, Object>attributes;
  
  public KakaoUserInfo(Map<String, Object>attributes) {
	this.attributes = attributes;
}

	@Override
	public String getProvider_id() {
		// TODO Auto-generated method stub
		return (String) attributes.get("id").toString();
	}

	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		return "kakao";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return (String) ((Map)attributes.get("kakao_account")).get("email");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return (String) ((Map)attributes.get("properties")).get("nickname");
	}

}
