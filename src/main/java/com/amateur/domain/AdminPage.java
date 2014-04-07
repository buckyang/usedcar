package com.amateur.domain;

import java.io.Serializable;
import java.util.Set;

public class AdminPage implements Serializable {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3137756177457066849L;
	private Integer pageId;
	private String name;
	private String description;
	private String url;
	private Integer rank;
	private Integer type;
	private Set<AdminPermission> permissions;
	public Integer getPageId() {
		return pageId;
	}
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Set<AdminPermission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<AdminPermission> permissions) {
		this.permissions = permissions;
	}
}
