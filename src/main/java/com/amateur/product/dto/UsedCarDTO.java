package com.amateur.product.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.bind.annotation.RequestBody;

public class UsedCarDTO {

	private String productId;
	private String productName;
	@NotNull(message = "请选择车型")
	private Integer modelId;
	private String brandName;
	private String seriesName;
	private String modelDisplayName;
	@NotNull(message = "请选择车系")
	private Integer seriesId;
	@NotNull(message = "请选择品牌")
	private Integer brandId;
	private List<String> imageIds;
	@NotBlank(message = "请上传行驶证")
	private String licenseImageId;
	private String certificateImageId;
	private List<String> imageUrls;
	private String licenseImage;
	private String certificateImage;
	@Past
	private Date purchaseDate;
	@Digits(integer = 2, fraction = 1, message = "请输入最多2位整数及1位小数")
	private Double odometer;
	@Digits(integer = 4, fraction = 1, message = "请输入最多4位整数及1位小数")
	private Double listPrice;
	@NotBlank
	private String priceType;
	@Size(min = 17, max = 17, message = "请输入17位的车辆识别码")
	private String carVin;
	@NotBlank(message = "请输入联系人姓名")
	private String carContact;
	@Size(min = 11, max = 11, message = "请输入11位电话号码")
	private String contactPhone;
	private Integer status;
	private Date updateTime;
	private Integer accountId;
	@NotNull(message="请选择省份")
	private Integer provinceId;
	private String province;
	@NotNull(message="请选择城市")
	private Integer cityId;
	private String city;
	@NotNull(message="请选择区县")
	private Integer countyId;
	private String county;
	private String street;
	@AssertTrue(message="你必须接受协议")
	private Boolean acceptTerm;

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

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
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

	public String getModelDisplayName() {
		return modelDisplayName;
	}

	public void setModelDisplayName(String modelDisplayName) {
		this.modelDisplayName = modelDisplayName;
	}

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Double getOdometer() {
		return odometer;
	}

	public void setOdometer(Double odometer) {
		this.odometer = odometer;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getCarVin() {
		return carVin;
	}

	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}

	public String getCarContact() {
		return carContact;
	}

	public void setCarContact(String carContact) {
		this.carContact = carContact;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Boolean getAcceptTerm() {
		return acceptTerm;
	}

	public void setAcceptTerm(Boolean acceptTerm) {
		this.acceptTerm = acceptTerm;
	}

	public List<String> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<String> imageIds) {
		this.imageIds = imageIds;
	}

	public String getLicenseImageId() {
		return licenseImageId;
	}

	public void setLicenseImageId(String licenseImageId) {
		this.licenseImageId = licenseImageId;
	}

	public String getCertificateImageId() {
		return certificateImageId;
	}

	public void setCertificateImageId(String certificateImageId) {
		this.certificateImageId = certificateImageId;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	public String getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}

	public String getCertificateImage() {
		return certificateImage;
	}

	public void setCertificateImage(String certificateImage) {
		this.certificateImage = certificateImage;
	}

}
