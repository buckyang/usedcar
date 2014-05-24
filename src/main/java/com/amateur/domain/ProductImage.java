package com.amateur.domain;

import java.io.Serializable;
import java.util.Date;

public class ProductImage implements Serializable {

	private static final long serialVersionUID = -3358173139306915707L;
	private Integer imageId;
	private String name;
	private String description;
	private String sizeJumbo;
	private String sizeLarge;
	private String sizeRegular;
	private String sizeSmall;
	private String sizeThumbnail;
	private String productId;
	private String type;
	private Date updateTime;

	public Integer getImageId() {
		return imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
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

	public String getSizeJumbo() {
		return sizeJumbo;
	}

	public void setSizeJumbo(String sizeJumbo) {
		this.sizeJumbo = sizeJumbo;
	}

	public String getSizeLarge() {
		return sizeLarge;
	}

	public void setSizeLarge(String sizeLarge) {
		this.sizeLarge = sizeLarge;
	}

	public String getSizeRegular() {
		return sizeRegular;
	}

	public void setSizeRegular(String sizeRegular) {
		this.sizeRegular = sizeRegular;
	}

	public String getSizeSmall() {
		return sizeSmall;
	}

	public void setSizeSmall(String sizeSmall) {
		this.sizeSmall = sizeSmall;
	}

	public String getSizeThumbnail() {
		return sizeThumbnail;
	}

	public void setSizeThumbnail(String sizeThumbnail) {
		this.sizeThumbnail = sizeThumbnail;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
