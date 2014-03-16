package com.amateur.account.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.account.dto.RegistrationDTO;
import com.amateur.domain.Account;
import com.amateur.domain.Reseller;
import com.amateur.persistence.AccountMapper;
import com.amateur.service.SequenceService;

@Service
public class AccountService {

	
	private static final Logger logger = Logger.getLogger(AccountService.class);

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private SequenceService sequenceService;
	
	public boolean registrerAccount(RegistrationDTO registrationDTO){
		if(registrationDTO.getAccountType() == 1){
			Account account = new Account(registrationDTO, sequenceService.getAccountId());
			if(accountMapper.registerAccount(account) ==1){
				return true;
			}
		}else if(registrationDTO.getAccountType() == 2){
			Reseller reseller = new Reseller(registrationDTO, sequenceService.getAccountId());
			if(accountMapper.registerAccount(reseller) == 1){
				if(accountMapper.registerResellerPart(reseller) == 1){
					return true;
				}
			}
		}
		
		return false;
	}

	public Account getAccountByEmail(String email) {
		return accountMapper.getAccountByEmail(email);
	}
	
	public Account getAccountByPhoneOrEmail(String emailOrPhone){
		return accountMapper.getAccountByPhoneOrEmail(emailOrPhone);
	}
	public Account getAccountByProfileHash(String profileHash){
		return accountMapper.getAccountByProfileHash(profileHash);
	}
}
