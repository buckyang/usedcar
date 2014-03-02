package com.amateur.account.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amateur.domain.Account;
import com.amateur.persistence.AccountMapper;

@Service
public class AccountService {

	
	private static final Logger logger = Logger.getLogger(AccountService.class);

	@Autowired
	private AccountMapper accountMapper;

}
