package com.amateur.account.controller;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amateur.account.dto.ForgetPasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.ForgetPasswordValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.ResetPasswordRecord;
import com.amateur.service.impl.MailSenderServiceImpl;


@Controller
@RequestMapping("account")
public class ForgetPasswordController extends BaseController {
	
	@Autowired
	private ForgetPasswordValidator forgetPasswordValidator;

	@Autowired
	private MailSenderServiceImpl mailSender;
	
	@Autowired
	private AccountService accountService;
	
	@InitBinder("forgetPasswordDTO")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(forgetPasswordValidator);
	}

	@ModelAttribute("forgetPasswordDTO")
	public ForgetPasswordDTO createFormBean() {
		ForgetPasswordDTO forgetPasswordDTO = new ForgetPasswordDTO();
		return forgetPasswordDTO;
	}
	
	@RequestMapping(value = "/forgetPasswordByMail", method = RequestMethod.GET)
	public void forgetPasswordByMail() {
		
	}
	
	@RequestMapping(value = "/forgetPasswordByMail", method = RequestMethod.POST)
	public void forgetPasswordByMail(@Valid @ModelAttribute("forgetPasswordDTO") ForgetPasswordDTO forgetPasswordDTO,BindingResult result,Model m) {
		if(result.hasErrors()){
			return;
		}
		String loginName=forgetPasswordDTO.getLoginName();
		if(loginName.indexOf('@')>-1){
			Account account=accountService.getAccountByEmail(loginName);
			Map<String,Object> dataMap=new HashMap<String,Object>();
			String username=account.getRealName()==null?loginName:account.getRealName();
			dataMap.put("username",loginName);
			dataMap.put("realname",username);
			String code=UUID.randomUUID().toString().replace("-", "");
			dataMap.put("activeCode",code );
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.HOUR, 1);
			Date date=calendar.getTime();
			dataMap.put("expireDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			dataMap.put("from", "testForgetpwd@163.com");
			dataMap.put("subject", "[2soce的邮件]找回密码");
			dataMap.put("email_type_to", new String []{loginName});
			mailSender.sendMail(dataMap, "emailForPassword.ftl");
			m.addAttribute("sendMail", messageSource.getMessage("forget.password.send.mail.success", new String[]{loginName}, Locale.getDefault()));
			
			ResetPasswordRecord record =new ResetPasswordRecord();
			record.setAccountId(account.getAccountId());
			record.setActiveCode(code);
			record.setExpiredDate(date.getTime());
			record.setMail(loginName);
			if (accountService.getResetPasswordRecord(account.getAccountId()) == null) {
				accountService.addResetPasswordRecord(record);
			} else {
				accountService.updatePasswordRecord(record);
			}
		}
	}
}
