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
		List<String> imageList = usedCarDTO.getImageUrls();
		for (String imageUrl : imageList) {
			String[] urls = imageUrl.split(";");
			ProductImage productImage = new ProductImage();
			productImage.setProductId(productId);
			if(urls[0]!=null && !"".equalsIgnoreCase(urls[0].trim())){
				productImage.setImageId(Integer.valueOf(urls[0]));
			}
			productImage.setType(urls[1]);
			productImage.setSizeJumbo(urls[2]);
			productImage.setSizeLarge(urls[3]);
			productImage.setSizeRegular(urls[4]);
			productImage.setSizeSmall(urls[5]);
			productImage.setSizeThumbnail(urls[6]);
			productImage.setName(productName);
			productImage.setDescription(productName+urls[1]);
			productImageList.add(productImage);
		}
	}
	
	public UsedCarDTO getDTO(){
		UsedCarDTO usedCarDTO = new UsedCarDTO();
		BeanUtils.copyProperties(this, usedCarDTO);
		BeanUtils.copyProperties(this.getProductAddress(), usedCarDTO);
		BeanUtils.copyProperties(this.getProductModel(), usedCarDTO);
		return usedCarDTO;
	}

}
