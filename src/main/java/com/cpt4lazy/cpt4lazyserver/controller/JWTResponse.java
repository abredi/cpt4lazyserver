package com.cpt4lazy.cpt4lazyserver.controller;

import com.cpt4lazy.cpt4lazyserver.entity.User;
/**
 * Custom Response thrown after user login
 * @author cmmap
 *
 */
public class JWTResponse {
	
	private User user;
	private final String tokenType = "Bearer";
	private String accessToken;

	public JWTResponse(User user, String accessToken) {
		this.user = user;
		this.accessToken = accessToken;
	}

	public User getUser() {
		return user;
	}
	
	public String getTokenType() {
		return tokenType;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
