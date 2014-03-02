package com.amateur.domain;

import java.io.Serializable;

public class Sequence implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6025823645630438186L;
	private String name;
	private int nextId;
	private String prefix;
	private String suffix;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNextId() {
		return nextId;
	}
	public void setNextId(int nextId) {
		this.nextId = nextId;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}
