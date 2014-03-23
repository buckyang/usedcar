package com.amateur.persistence;

import com.amateur.domain.ProductAddress;

public interface ProductAddressMapper {
		
	int insertProductAddress(ProductAddress productAddress);
	int updateProdutAddress(ProductAddress productAddress);
	int deleteProductAddress(String productId);
}
