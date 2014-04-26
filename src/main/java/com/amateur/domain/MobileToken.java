package com.amateur.domain;

import java.io.Serializable;
import java.util.Date;

public class MobileToken implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6149193455046020961L;

	public static final String DEFAULT_MOBILE_IDENTIFIER = "default_mobile_client";
	
	private String	accessToken;
	private Date	validDate;
	private Integer	accountId;
	private String	clientIdentifier;
	private String userAgent;


	public MobileToken() {
		super();
	}



	public String getAccessToken() {
		return accessToken;
	}



	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}



	public Date getValidDate() {
		return validDate;
	}



	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}



	public Integer getAccountId() {
		return accountId;
	}



	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}



	public String getClientIdentifier() {
		return clientIdentifier;
	}



	public void setClientIdentifier(String clientIdentifier) {
		this.clientIdentifier = clientIdentifier;
	}



	public String getUserAgent() {
		return userAgent;
	}



	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
