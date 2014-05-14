package com.amateur.product.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amateur.product.dto.ProductImageDTO;

@Component
public class ProductImageValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return ProductImageDTO.class.isAssignableFrom(clazz);
	}



	@Override
	public void validate(Object target, Errors errors) {
		ProductImageDTO productImageDTO = (ProductImageDTO) target;
		if(productImageDTO.getImage() == null || productImageDTO.getImage().isEmpty()){
			errors.rejectValue("image", "product.uploadimage.empty");
			return;
		}else if(!"image/jpeg".equalsIgnoreCase(productImageDTO.getImage().getContentType())){
			errors.rejectValue("image", "product.uploadimage.format.invalid");
		}
		
	}

}
