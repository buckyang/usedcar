package com.amateur.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.account.dto.RestPasswordDTO;

@Component
public class RestPasswordValidator implements Validator {
	
	@Autowired
	LocalValidatorFactoryBean validator;

	@Override
	public boolean supports(Class<?> clazz) {
		return RestPasswordDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		RestPasswordDTO restPasswordDTO=(RestPasswordDTO) target;
		String newPassword=restPasswordDTO.getNewPassword();
		String confirmPassword=restPasswordDTO.getConfirmPassword();
		
		if(!newPassword.equals(confirmPassword)){
			errors.rejectValue("newPassword", "confirm.password.not.eq.new.password");
		}
	}

}
