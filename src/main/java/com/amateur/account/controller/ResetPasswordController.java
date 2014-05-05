package com.amateur.account.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amateur.account.dto.RestPasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.RestPasswordValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.ResetPasswordRecord;
import com.amateur.util.EncryptionUtil;

@Controller
@RequestMapping("account")
public class ResetPasswordController extends BaseController {
	
	@Autowired
	private RestPasswordValidator restPasswordValidator;
	
	@Autowired
	private AccountService accountService;
	
	@InitBinder("resetPasswordDTO")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(restPasswordValidator);
	}
	
	@ModelAttribute("resetPasswordDTO")
	public RestPasswordDTO createFormBean() {
		RestPasswordDTO restPassword = new RestPasswordDTO();
		return restPassword;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(HttpServletRequest req,Model m) {
		String mail=req.getParameter("user");
		String activeCode= req.getParameter("active");
		Account forgetPwdAccount=accountService.getAccountByEmail(mail);
		if(!checkRestPasswordParams(mail,activeCode,m,forgetPwdAccount)){
			return "forward:forgetPasswordByMail";
		}
		m.addAttribute("mail", mail);
		m.addAttribute("activeCode", activeCode);
		return "/account/resetPassword";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(@Valid @ModelAttribute("resetPasswordDTO") RestPasswordDTO resetPasswordDTO,BindingResult result,Model m,HttpServletRequest req) {
		if(result.hasErrors()){
			return "/account/resetPassword";
		}
		String mail=resetPasswordDTO.getMail();
		String activeCode=resetPasswordDTO.getActiveCode();
		Account forgetPwdAccount=accountService.getAccountByEmail(mail);
		if(!checkRestPasswordParams(mail,activeCode,m,forgetPwdAccount)){
			return "forward:forgetPasswordByMail";
		}
		Account account=new Account();
		account.setAccountId(forgetPwdAccount.getAccountId());
		account.setPassword(EncryptionUtil.encryptPassword(resetPasswordDTO.getNewPassword()));
		accountService.updatePassword(account);
		ResetPasswordRecord record=new ResetPasswordRecord();
		record.setAccountId(forgetPwdAccount.getAccountId());
		accountService.deleteResetPasswordRecord(record);
		return "/account/resetPassword";
	}

	private boolean checkRestPasswordParams(String mail, String activeCode,Model m,Account account) {
		if(StringUtils.isBlank(mail)||StringUtils.isBlank(activeCode)||account==null){
			m.addAttribute("resetPasswordErrorMessage", messageSource.getMessage("reset.password.url.expired", null, Locale.getDefault()));
			return false;
		}
		ResetPasswordRecord record=accountService.getResetPasswordRecord(account.getAccountId());
		if(record==null||(System.currentTimeMillis()-record.getExpiredDate()-3600*1000)>0||!activeCode.equals(record.getActiveCode())){
			m.addAttribute("resetPasswordErrorMessage", messageSource.getMessage("reset.password.url.expired", null, Locale.getDefault()));
			return false;
		}
		return true;
	}

}
