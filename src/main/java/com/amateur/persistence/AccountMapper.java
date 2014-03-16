package com.amateur.persistence;

import com.amateur.domain.Account;
import com.amateur.domain.Reseller;

public interface AccountMapper {
		
	int registerAccount(Account account);
	int registerResellerPart(Reseller reseller);
	Account getAccountByEmail(String email);
	Account getAccountByProfileHash(String profileHash);
	Account getAccountById(Integer accountId);
	Account getAccountByPhoneOrEmail(String emailOrPhone);
}
