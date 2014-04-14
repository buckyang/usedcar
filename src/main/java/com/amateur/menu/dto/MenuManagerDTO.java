package com.amateur.menu.dto;

import java.io.Serializable;
import java.util.List;

public class MenuManagerDTO implements Serializable {

	private static final long serialVersionUID = 1701075605507216785L;

	private int menuId;

	private String title;

	private String contents;

	private int parentMenuId;

	private int displayOrder;
	
	private boolean editAllProperty;

	private MenuManagerDTO nextMenuItem;

	private List<MenuManagerDTO> childMenuItems;


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

	public MenuManagerDTO getNextMenuItem() {
		return nextMenuItem;
	}

	public void setNextMenuItem(MenuManagerDTO nextMenuItem) {
		this.nextMenuItem = nextMenuItem;
	}

	public List<MenuManagerDTO> getChildMenuItems() {
		return childMenuItems;
	}

	public void setChildMenuItems(List<MenuManagerDTO> childMenuItems) {
		this.childMenuItems = childMenuItems;
	}

}
