package com.amateur.account.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.amateur.account.dto.UpdatePasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.domain.Account;

@Component
public class UpdatePasswordValidator implements Validator {
	
	@Autowired
	private AccountService accountService;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdatePasswordDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UpdatePasswordDTO passwordDTO = (UpdatePasswordDTO) target;
		Account account=accountService.getAccountById(passwordDTO.getAccountId());
		
		if (StringUtils.isBlank(passwordDTO.getOldPWD())) {
			errors.rejectValue("oldPWD", "old.password.is.not.blank");
		}else if(!passwordDTO.getOldPWD().equals(account.getPassword())){
			errors.rejectValue("oldPWD", "old.password.input.error");
		}
		String regex="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(passwordDTO.getNewPWD());
		if(StringUtils.isBlank(passwordDTO.getNewPWD())||!matcher.matches()){
			errors.rejectValue("newPWD", "new.password.pattern.is.error");
		}else if(!passwordDTO.getNewPWD().equals(passwordDTO.getConfirmPWD())){
			errors.rejectValue("confirmPWD", "confirm.password.is.not.match.newpassword");
		}
	}

}
