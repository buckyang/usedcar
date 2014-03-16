package com.amateur.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.account.dto.LoginDTO;
import com.amateur.account.service.AccountService;
import com.amateur.domain.Account;
import com.amateur.util.EncryptionUtil;


@Component
public class LoginValidator implements Validator {
	@Autowired
	LocalValidatorFactoryBean validator;
	@Autowired
	private AccountService accountService;
	
	public boolean supports(Class<?> arg0) {
		return LoginDTO.class.isAssignableFrom(arg0);
	}

	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		LoginDTO loginDTO = (LoginDTO) target;
		boolean loginFailed = true;
		Account account = accountService.getAccountByPhoneOrEmail(loginDTO.getPhoneOrEmail().trim());
		if(account != null){
			if(account.getPassword().equals(EncryptionUtil.encryptPassword(loginDTO.getPassword().trim()))){
				loginFailed = false;
			}
		}
		if(loginFailed){
			errors.rejectValue("password", "account.login.failed");
		}
	}

}
