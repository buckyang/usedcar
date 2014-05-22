package com.amateur.mobiletoken.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class AccessTokenDTO {
	@NotEmpty
	private String	userId;
	@NotEmpty
	private String	accessToken;



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getAccessToken() {
		return accessToken;
	}



	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
