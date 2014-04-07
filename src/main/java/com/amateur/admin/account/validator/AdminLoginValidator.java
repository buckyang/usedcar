package com.amateur.admin.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.admin.account.dto.AdminLoginDTO;
import com.amateur.admin.account.service.AdminAccountService;
import com.amateur.domain.AdminAccount;
import com.amateur.util.EncryptionUtil;


@Component
public class AdminLoginValidator implements Validator {
	@Autowired
	LocalValidatorFactoryBean validator;
	@Autowired
	private AdminAccountService adminAccountService;
	
	public boolean supports(Class<?> arg0) {
		return AdminLoginDTO.class.isAssignableFrom(arg0);
	}

	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		AdminLoginDTO loginDTO = (AdminLoginDTO) target;
		boolean loginFailed = true;
		AdminAccount account = adminAccountService.selectAccountByLoginName(loginDTO.getLoginName().trim());
		if(account != null){
			if(account.getPassword().equals(EncryptionUtil.encryptPassword(loginDTO.getPassword().trim()))){
				loginFailed = false;
			}
		}
		if(loginFailed){
			errors.rejectValue("password", "admin.account.login.failed");
		}
	}
}
