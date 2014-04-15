package com.amateur.domain;

import java.io.Serializable;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = 4865628565259946327L;

	private int menuId;

	private String title;

	private String contents;

	private int parentMenuId;

	private int displayOrder;
	
	private boolean editAllProperty;
	
	
	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(int parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public int getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	
	public boolean isEditAllProperty() {
		return editAllProperty;
	}

	public void setEditAllProperty(boolean editAllProperty) {
		this.editAllProperty = editAllProperty;
	}


	

}
