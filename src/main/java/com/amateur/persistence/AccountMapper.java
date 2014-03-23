package com.amateur.persistence;

import java.util.Map;

import com.amateur.domain.Account;
import com.amateur.domain.Address;
import com.amateur.domain.MobileToken;
import com.amateur.domain.Reseller;

public interface AccountMapper {
		
	int registerAccount(Account account);
	int registerResellerPart(Reseller reseller);
	Account getAccountByEmail(String email);
	Account getAccountById(Integer accountId);
	Account getAccountByPhoneOrEmail(String emailOrPhone);
	Account getAccountByProfileHash(String profileHash);
	void updatePassword(Account account);
	void updateUserInfo(Account account);
	void updateHomeAddress(Address address);
	Address getHomeAddressByAccountId(Integer accountId);
	Account getAccountByAccessToken(Map<String, Object> tokenAndDateMap);
	int insertMobileToken(MobileToken mobileToken);
	int updateMobileToken(MobileToken mobileToken);
	MobileToken selectMobileToken(MobileToken mobileToken);
}
