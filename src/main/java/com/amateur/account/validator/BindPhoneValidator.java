package com.amateur.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.account.dto.BindPhoneDTO;
@Component
public class BindPhoneValidator implements Validator {
	
	@Autowired
	LocalValidatorFactoryBean validator;

	@Override
	public boolean supports(Class<?> clazz) {
		return BindPhoneDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		BindPhoneDTO bindPhoneDTO=(BindPhoneDTO) target;
		String regex="^[1][3,4,5,8][0-9]{9}$";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(bindPhoneDTO.getPhoneNumber());
		if(!matcher.matches()){
			errors.rejectValue("phoneNumber", "invalid.phone.number");
			return;
		}
	}

}
