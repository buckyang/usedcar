package com.amateur.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.account.dto.ForgetPasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.domain.Account;

@Component
public class ForgetPasswordValidator implements Validator {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	LocalValidatorFactoryBean validator;


	@Override
	public boolean supports(Class<?> clazz) {
		return ForgetPasswordDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		ForgetPasswordDTO forgetPasswordDTO=(ForgetPasswordDTO) target;
		String loginName=forgetPasswordDTO.getLoginName();
		Account account=accountService.getAccountByPhoneOrEmail(loginName);
		if(account==null){
			errors.rejectValue("loginName", "forget.password.invalid.name");
		}else if(loginName.indexOf('@')>-1&&!account.getBindEmail()){
			errors.rejectValue("loginName", "forget.password.invalid.name");
		}
	}

}
