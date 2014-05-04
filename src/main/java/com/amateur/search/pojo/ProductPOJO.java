package com.amateur.search.pojo;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class ProductPOJO {
	@Field
	private String	productId;
	@Field
	private String	productName;
	@Field
	private Double	listPrice;
	@Field
	private Integer	priceType;
	@Field
	private String	carVin;
	@Field
	private Date	updateTime;
	@Field
	private String	jumboImage;
	@Field
	private String	largeImage;
	@Field
	private String	regularImage;
	@Field
	private String	smallImage;
	@Field
	private String	thumbNailImage;
	@Field
	private String	brandName;
	@Field
	private String	seriesName;
	@Field
	private String	modelName;
	@Field
	private String	displacement;
	@Field
	private String	transmissionType;
	@Field
	private Date	purchaseDate;
	@Field
	private Integer	odometer;



	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public Double getListPrice() {
		return listPrice;
	}



	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}



	public Integer getPriceType() {
		return priceType;
	}



	public void setPriceType(Integer priceType) {
		this.priceType = priceType;
	}



	public String getCarVin() {
		return carVin;
	}



	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}



	public Date getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



	public String getJumboImage() {
		return jumboImage;
	}



	public void setJumboImage(String jumboImage) {
		this.jumboImage = jumboImage;
	}



	public String getLargeImage() {
		return largeImage;
	}



	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}



	public String getRegularImage() {
		return regularImage;
	}



	public void setRegularImage(String regularImage) {
		this.regularImage = regularImage;
	}



	public String getSmallImage() {
		return smallImage;
	}



	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}



	public String getThumbNailImage() {
		return thumbNailImage;
	}



	public void setThumbNailImage(String thumbNailImage) {
		this.thumbNailImage = thumbNailImage;
	}



	public String getBrandName() {
		return brandName;
	}



	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}



	public String getSeriesName() {
		return seriesName;
	}



	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}



	public String getModelName() {
		return modelName;
	}



	public void setModelName(String modelName) {
		this.modelName = modelName;
	}



	public String getDisplacement() {
		return displacement;
	}



	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}



	public String getTransmissionType() {
		return transmissionType;
	}



	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}



	public Date getPurchaseDate() {
		return purchaseDate;
	}



	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}



	public Integer getOdometer() {
		return odometer;
	}



	public void setOdometer(Integer odometer) {
		this.odometer = odometer;
	}


}
