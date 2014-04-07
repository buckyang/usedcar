package com.amateur.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AdminAccount implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 296451825269525916L;
	private Integer accountId;
	private String loginName;
	private String password;
	private Date lastLoginTime;
	private Boolean locked;
	private List<AdminPermission> permissions;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Boolean getLocked() {
		return locked;
	}
	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	public List<AdminPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<AdminPermission> permissions) {
		this.permissions = permissions;
	}

}
