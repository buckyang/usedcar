package com.amateur.mobiletoken.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.mobiletoken.dto.AccessTokenDTO;
@Component
public class AccessTokenValidator implements Validator {
	@Autowired
	LocalValidatorFactoryBean validator;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return AccessTokenDTO.class.isAssignableFrom(clazz);
	}



	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
	}

}
