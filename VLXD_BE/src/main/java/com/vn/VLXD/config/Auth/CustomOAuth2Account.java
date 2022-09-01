package com.vn.VLXD.config.Auth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2Account implements OAuth2User{

	private OAuth2User oAuth2User;
	
	
	
	public CustomOAuth2Account(OAuth2User oAuth2User) {
		this.oAuth2User = oAuth2User;
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oAuth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oAuth2User.getAttribute("name");
	}
	
	public String getFullName() {
		return oAuth2User.getAttribute("name");
	}

}
