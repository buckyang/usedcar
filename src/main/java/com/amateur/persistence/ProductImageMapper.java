package com.amateur.persistence;

import com.amateur.domain.ProductImage;

public interface ProductImageMapper {
		
	int insertProdutImage(ProductImage productImage);
	int updateProdutImage(ProductImage productImage);
	int deleteProdutImage(String productId);
		
}
