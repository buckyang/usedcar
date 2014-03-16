package com.amateur.session;

import com.amateur.domain.Account;

public class Profile {
	public static final String	PROFILE_KEY		= "profile";
	public static final int		ANONYMOUS		= 0;
	public static final int		EXPLICIT_LOGIN	= 1;
	public static final int		COOKIE_LOGIN	= 2;
	public static final String	COOKIE_USER_ID	= "USER_ID";

	private int					status			= 0;

	private Account				accountDatasource;



	public Account getAccountDatasource() {
		return accountDatasource;
	}



	public void setAccountDatasource(Account accountDatasource) {
		this.accountDatasource = accountDatasource;
	}



	public Integer getAccountId() {
		if (accountDatasource != null) {
			return accountDatasource.getAccountId();
		}
		return null;
	}



	public String getRealName() {
		if (accountDatasource != null) {
			return accountDatasource.getRealName();
		}
		return null;
	}



	public String getEmail() {
		if (accountDatasource != null) {
			return accountDatasource.getEmail();
		}
		return null;
	}

	public String getPhone() {
		if (accountDatasource != null) {
			return accountDatasource.getPhone();
		}
		return null;
	}

	public String getNickname() {
		if (accountDatasource != null) {
			return accountDatasource.getNickname();
		}
		return null;
	}



	public String getIdNumber() {
		if (accountDatasource != null) {
			return accountDatasource.getIdNumber();
		}
		return null;
	}
	
	public Integer getAccountType(){
		if (accountDatasource != null) {
			return accountDatasource.getAccountType();
		}
		return null;		
	}
	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}
}
