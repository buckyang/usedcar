
package com.amateur.account.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.account.dto.RegistrationDTO;
import com.amateur.domain.Account;
import com.amateur.domain.Address;
import com.amateur.domain.MobileToken;
import com.amateur.domain.Reseller;
import com.amateur.persistence.AccountMapper;
import com.amateur.service.SequenceService;
import com.amateur.util.EncryptionUtil;

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
	public Account getAccountById(Integer accountId){
		return accountMapper.getAccountById(accountId);
	}
	
	public Account getAccountByAccessToken(String accessToken){
		Map<String, Object> tokenAndDateMap = new HashMap<String, Object>();
		tokenAndDateMap.put("accessToken", accessToken);
		tokenAndDateMap.put("date", new Date());
		return accountMapper.getAccountByAccessToken(tokenAndDateMap);
	}
	
	public void updateOrInsertMobileToken(MobileToken mobileToken){
		if(accountMapper.selectMobileToken(mobileToken) != null){
			accountMapper.updateMobileToken(mobileToken);
		}else{
			accountMapper.insertMobileToken(mobileToken);
		}
	}



	public boolean validateMobileAccessToken(MobileToken queryMobileToken, String requestAccessToken) {

		MobileToken mobileToken = accountMapper.selectMobileToken(queryMobileToken);
		if(mobileToken != null && mobileToken.getValidDate().compareTo(new Date()) > 0){
			if(EncryptionUtil.validMobileRequestToken(requestAccessToken, mobileToken.getAccessToken())){
				return true;
			}
		}
		return false;
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
