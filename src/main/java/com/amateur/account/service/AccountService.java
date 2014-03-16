package com.amateur.account.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.account.dto.RegistrationDTO;
import com.amateur.domain.Account;
import com.amateur.domain.Address;
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
			Account account = new Account();
			BeanUtils.copyProperties(registrationDTO, account);
			account.setRegistrationDate(new Date());
			account.setAccountId(sequenceService.getAccountId());
			if(accountMapper.registerAccount(account) ==1){
				return true;
			}
		}else if(registrationDTO.getAccountType() == 2){
			Reseller reseller = new Reseller();
			BeanUtils.copyProperties(registrationDTO, reseller);
			reseller.setRegistrationDate(new Date());
			reseller.setAccountId(sequenceService.getAccountId());
			
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
	
	public Account getAccountById(Integer accountId){
		return accountMapper.getAccountById(accountId);
	}
	
	public void updatePassword (Account account) {
		accountMapper.updatePassword(account);
	}
	
	public void updateUserInfo(Account account){
		accountMapper.updateUserInfo(account);
	}
	
	public void updateHomeAddress(Address address){
		accountMapper.updateHomeAddress(address);
	}
	
	public Address getHomeAddressByAccountId(Integer accountId){
		return accountMapper.getHomeAddressByAccountId(accountId);
	} 
}
