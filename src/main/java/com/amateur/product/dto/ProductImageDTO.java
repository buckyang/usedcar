package com.amateur.product.dto;

import org.springframework.web.multipart.MultipartFile;

public class ProductImageDTO {
	private MultipartFile image;

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
