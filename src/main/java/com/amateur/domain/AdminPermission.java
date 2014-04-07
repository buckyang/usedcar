package com.amateur.domain;

import java.io.Serializable;

public class AdminPermission implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 7900756056947979273L;
	private int id;
	private String permission;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
