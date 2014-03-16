package com.amateur.persistence;

import com.amateur.domain.Account;
import com.amateur.domain.Address;
import com.amateur.domain.Reseller;

public interface AccountMapper {
		
	int registerAccount(Account account);
	int registerResellerPart(Reseller reseller);
	Account getAccountByEmail(String email);
	Account getAccountById(Integer accountId);
	Account getAccountByPhoneOrEmail(String emailOrPhone);
	void updatePassword(Account account);
	void updateUserInfo(Account account);
	void updateHomeAddress(Address address);
	Address getHomeAddressByAccountId(Integer accountId);
}
