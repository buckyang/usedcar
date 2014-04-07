package com.amateur.admin.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.domain.AdminAccount;
import com.amateur.persistence.AdminAccountMapper;

@Service
public class AdminAccountService {

	
	@Autowired
	private AdminAccountMapper adminAccountMapper;
	
	public AdminAccount selectAccountByLoginName(String name){
		return adminAccountMapper.selectAccountByLoginName(name);
	}
	public void updateLoginTime(AdminAccount adminAccount){
		adminAccountMapper.updateLoginTime(adminAccount);
	}
	
}
