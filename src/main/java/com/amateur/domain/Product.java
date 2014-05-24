package com.amateur.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.amateur.product.dto.UsedCarDTO;

public class Product implements Serializable {

	private static final long serialVersionUID = 4317250069293079394L;
	private String productId;
	private String productName;
	private ProductModel productModel;
	private List<ProductImage> productImageList;
	private Date purchaseDate;
	private Double odometer;
	private Double listPrice;
	private String priceType;
	private String carVin;
	private String carContact;
	private String contactPhone;
	private Integer status;
	private Date updateTime;
	private Integer accountId;
	private ProductAddress productAddress;

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

	public ProductModel getProductModel() {
		return productModel;
	}

	public void setProductModel(ProductModel productModel) {
		this.productModel = productModel;
	}

	public List<ProductImage> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<ProductImage> productImageList) {
		this.productImageList = productImageList;
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

	public ProductAddress getProductAddress() {
		return productAddress;
	}

	public void setProductAddress(ProductAddress productAddress) {
		this.productAddress = productAddress;
	}

	public void copyDTO(UsedCarDTO usedCarDTO) {
		BeanUtils.copyProperties(usedCarDTO, this);
		setUpdateTime(new Date());
		productModel = new ProductModel();
		BeanUtils.copyProperties(usedCarDTO, productModel);
		productAddress = new ProductAddress();
		BeanUtils.copyProperties(usedCarDTO, productAddress);
		productImageList = new ArrayList<ProductImage>();
		List<String> imageIdList = usedCarDTO.getImageIds();
		for (String imageId : imageIdList) {
			populateProductImage(imageId, "0");
		}
		populateProductImage(usedCarDTO.getLicenseImageId(), "1");
		populateProductImage(usedCarDTO.getCertificateImageId(), "2");
	}

	private void populateProductImage(String imageId, String type) {

		if (imageId != null && !"".equalsIgnoreCase(imageId)) {
			ProductImage productImage = new ProductImage();
			productImage.setImageId(Integer.valueOf(imageId));
			productImage.setProductId(productId);
			productImage.setType(type);
			productImage.setName(productName);
			productImage.setDescription(productName);
			productImage.setUpdateTime(new Date());
			productImageList.add(productImage);
		}
	}

	public UsedCarDTO getDTO() {
		UsedCarDTO usedCarDTO = new UsedCarDTO();
		BeanUtils.copyProperties(this, usedCarDTO);
		BeanUtils.copyProperties(this.getProductAddress(), usedCarDTO);
		BeanUtils.copyProperties(this.getProductModel(), usedCarDTO);
		List<ProductImage> imageList = this.getProductImageList();
		List<String> imageUrls = new ArrayList<String>();
		for (ProductImage productImage : imageList) {
			if (productImage != null
					&& "0".equalsIgnoreCase(productImage.getType())) {
				imageUrls.add(productImage.getSizeRegular());
			} else if (productImage != null
					&& "1".equalsIgnoreCase(productImage.getType())) {
				usedCarDTO.setLicenseImage(productImage.getSizeRegular());
			} else if (productImage != null
					&& "2".equalsIgnoreCase(productImage.getType())) {
				usedCarDTO.setCertificateImage(productImage.getSizeRegular());
			}
		}
		usedCarDTO.setImageUrls(imageUrls);
		return usedCarDTO;
	}

}
