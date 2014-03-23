package com.amateur.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.account.dto.ManagerUserInfoDTO;
@Component
public class ManagerUserInfoValidator implements Validator {
	
	@Autowired
	private LocalValidatorFactoryBean validator;

	@Override
	public boolean supports(Class<?> clazz) {
		return ManagerUserInfoDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		ManagerUserInfoDTO userInfoDTO=(ManagerUserInfoDTO) target;
		if(StringUtils.isBlank(userInfoDTO.getCertificateNumber())){
			return;
		}
		String regex="^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(userInfoDTO.getCertificateNumber());
		if(!matcher.matches()){
			errors.rejectValue("certificateNumber", "certificate.number.pattern.error");
		}
	}

}