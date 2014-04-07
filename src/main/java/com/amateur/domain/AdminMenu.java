package com.amateur.domain;

import java.util.List;

public class AdminMenu extends AdminPage {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 8884068479195950795L;
	private List<AdminMenu>		childMenus;



	public List<AdminMenu> getChildMenus() {
		return childMenus;
	}



	public void setChildMenus(List<AdminMenu> childMenus) {
		this.childMenus = childMenus;
	}

}
