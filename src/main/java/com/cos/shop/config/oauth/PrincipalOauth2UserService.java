package com.cos.shop.config.oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.shop.config.auth.PrincipalDetails;
import com.cos.shop.config.oauth.provider.FacebookUserInfo;
import com.cos.shop.config.oauth.provider.GoogleUserInfo;
import com.cos.shop.config.oauth.provider.KakaoUserInfo;
import com.cos.shop.config.oauth.provider.NaverUserInfo;
import com.cos.shop.config.oauth.provider.OAuthUserinfo;
import com.cos.shop.model.RoleType;
import com.cos.shop.model.User;
import com.cos.shop.repository.UserRepository;
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;
	
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		System.out.println("getClinent:" + userRequest.getClientRegistration());
		System.out.println("getAccessToken:" + userRequest.getAccessToken());
		System.out.println("getAttributes:" + super.loadUser(userRequest).getAttributes());

		OAuth2User oauth2User = super.loadUser(userRequest);
		// 구글 로그인 버튼 클릭-> 구글로그인 창->로그인 완료 ->code를 리턴(OAuth-Client라이브러리)->AccessToken요청
		// userRequest정보->loadUser함수 호출->구글로 부터 회원프로필 받는다.
		
		
		OAuthUserinfo oAuthUserinfo = null;
		
		if(userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuthUserinfo = new GoogleUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
			System.out.println("페이스북 로그인 요청");
			oAuthUserinfo = new FacebookUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			 System.out.println("네이버 로그인 요청");
			 oAuthUserinfo = new NaverUserInfo(oauth2User.getAttributes());
		}else if(userRequest.getClientRegistration().getRegistrationId().equals("kakao")) {
			System.out.println("카카오 로그인 요청");
			oAuthUserinfo = new KakaoUserInfo(oauth2User.getAttributes());
		}else {
			System.out.println("우리는 구글과 페이스북만 지원해요");
		}

		String provider = oAuthUserinfo.getProvider();
		String provider_id = oAuthUserinfo.getProvider_id();
		String username = provider + "_" + provider_id;
		String name = oAuthUserinfo.getName();
		String password = bCryptPasswordEncoder.encode("겟인데어"); 
		String email = oAuthUserinfo.getEmail();	
		String role = "USER";
		
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
		    user = User.builder()
		            .username(username)
		            .password(password)
		            .name(name)
		            .email(email)
		            .role(RoleType.USER)
		            .provider_Id(provider_id)
		            .provider(provider)            
		            .build();
		    userRepository.save(user);
		}else {
			System.out.println("로그인을 하여,자동으로 회원가입이 되었습니다.");
		}
		
		return new PrincipalDetails(user,oauth2User.getAttributes());
		
		
	

	}

}
