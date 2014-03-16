package com.amateur.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.account.dto.RegistrationDTO;
import com.amateur.account.service.AccountService;
@Component
public class RegistrationValidator implements Validator {
	@Autowired
	LocalValidatorFactoryBean validator;
	@Autowired
	private AccountService accountService;
	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		RegistrationDTO registrationDTO = (RegistrationDTO) target;
		if(accountService.getAccountByEmail(registrationDTO.getEmail())!= null){
			errors.rejectValue("email", "account.email.existing");
		}
		if(!registrationDTO.getPassword().equals(registrationDTO.getRepassword()))
		errors.rejectValue("rePassword", "account.repassword.notequal");

		
	}

}
