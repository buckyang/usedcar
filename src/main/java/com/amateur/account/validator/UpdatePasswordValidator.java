package com.amateur.account.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.amateur.account.dto.UpdatePasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.domain.Account;
import com.amateur.util.EncryptionUtil;

@Component
public class UpdatePasswordValidator implements Validator {
	
	@Autowired
	LocalValidatorFactoryBean validator;
	
	@Autowired
	private AccountService accountService;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return UpdatePasswordDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		validator.validate(target, errors);
		if(errors.hasErrors()){
			return;
		}
		
		UpdatePasswordDTO passwordDTO = (UpdatePasswordDTO) target;
		Account account=accountService.getAccountById(passwordDTO.getAccountId());
		if(account==null){
			errors.reject("message", "account.invalid");
			return;
		}
		String currentPWD=account.getPassword();
		String oldPWDCip=EncryptionUtil.encryptPassword(passwordDTO.getOldPWD());
		if(!currentPWD.equals(oldPWDCip)){
			errors.rejectValue("oldPWD", "old.password.input.error");
			return;
		}
		
		String newPWD=passwordDTO.getNewPWD();
		String newPWDCip=EncryptionUtil.encryptPassword(newPWD);
		
		
		if(currentPWD.equals(newPWDCip)){
			errors.rejectValue("newPWD", "new.pwd.not.same.with.old.pwd");
			return;
		}
		
		/*String regex="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,20})";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(newPWD);
		if(!matcher.matches()){
			errors.rejectValue("newPWD", "new.password.pattern.is.error");
			return;
		}*/
		
		String confirmPWD=passwordDTO.getConfirmPWD();
		if(!newPWD.equals(confirmPWD)){
			errors.rejectValue("confirmPWD", "confirm.password.is.not.match.newpassword");
		}
	}

}
