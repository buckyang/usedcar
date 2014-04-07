package com.amateur.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amateur.domain.AdminAccount;
import com.amateur.domain.AdminPermission;

public class AdminProfile {
	public static final int		ANONYMOUS		= 0;
	public static final int		EXPLICIT_LOGIN	= 3;
	public static final int		COOKIE_LOGIN	= 1;
	public static final String ADMIN_PROFILE_SESSION_KEY = "adminProfile";
	private AdminAccount	accountDatasource;
	private List<String>	permissions;

	private int					status			= 0;


	public AdminAccount getAccountDatasource() {
		return accountDatasource;
	}



	public void setAccountDatasource(AdminAccount accountDatasource) {
		this.accountDatasource = accountDatasource;
	}



	public String getLoginName() {
		if (accountDatasource != null) {
			return accountDatasource.getLoginName();
		}
		return null;
	}



	public Date getLastLoginTime() {
		if (accountDatasource != null) {
			return accountDatasource.getLastLoginTime();
		}
		return null;
	}



	public List<String> getPermissions() {
		if(accountDatasource == null){
			return null;
		}
		if (permissions == null) {
			if (accountDatasource != null) {
				permissions = new ArrayList<String>();
				for (AdminPermission adminPermission : accountDatasource.getPermissions()) {
					permissions.add(adminPermission.getPermission());
				}
			}

		}
		return permissions;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}

}
