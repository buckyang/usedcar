package com.amateur.account.controller;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.amateur.account.dto.RestPasswordDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.RestPasswordValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.VerificationCode;
import com.amateur.util.EncryptionUtil;

@Controller
public class ResetPasswordController extends BaseController {
	
	@Autowired
	private RestPasswordValidator restPasswordValidator;
	
	@Autowired
	private AccountService accountService;
	
	
	@Override
	protected String getPostSuccessCode() {
		return "reset.pwd.success";
	}
	
	@InitBinder("resetPasswordDTO")
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(restPasswordValidator);
	}
	
	@ModelAttribute("resetPasswordDTO")
	public RestPasswordDTO createFormBean() {
		RestPasswordDTO restPassword = new RestPasswordDTO();
		return restPassword;
	}
	
	@RequestMapping(value = "/resetPasswordByPhone", method = RequestMethod.GET)
	public String resetPasswordByPhone(HttpServletRequest req,Model m,RedirectAttributes redirectAttributes) {
		Map<String,?> flashAttributesMap =  RequestContextUtils.getInputFlashMap(req);
		if(null==flashAttributesMap){
			return "redirect:forgetPassword";
		}
		String phone=(String) flashAttributesMap.get("phone");
		if(StringUtils.isBlank(phone)){
			return "redirect:forgetPassword";
		}
		return "resetPasswordByPhone";
	}
	
	@RequestMapping(value = "/resetPasswordByPhone", method = RequestMethod.POST)
	public String resetPasswordByPhone(@Valid @ModelAttribute("resetPasswordDTO") RestPasswordDTO resetPasswordDTO,BindingResult result,Model m,HttpServletRequest req) {
		if(result.hasErrors()){
			m.addAttribute("phone", resetPasswordDTO.getPrinciple());
			return "resetPasswordByPhone";
		}
		String phone=resetPasswordDTO.getPrinciple();
		String activeCode=resetPasswordDTO.getActiveCode();
		Account forgetPwdAccount=accountService.getAccountByPhoneOrEmail(phone);
		if(!checkRestPasswordParams(forgetPwdAccount,phone,activeCode,4)){
			m.addAttribute("phone", resetPasswordDTO.getPrinciple());
			result.rejectValue("activeCode", "reset.pwd.active.code.invalid");
			return "resetPasswordByPhone";
		}
		Account account=new Account();
		account.setAccountId(forgetPwdAccount.getAccountId());
		account.setPassword(EncryptionUtil.encryptPassword(resetPasswordDTO.getNewPassword()));
		accountService.updatePassword(account);
		VerificationCode record=new VerificationCode();
		record.setUsed(true);
		record.setPrinciple(phone);
		record.setType(4);
		record.setUsedDate(Calendar.getInstance().getTime());
		record.setVerificationCode(resetPasswordDTO.getActiveCode());
		accountService.updateVerificationStatus(record);
		return "resetPwdSuccess";
	}
	
	@RequestMapping(value = "/resetPasswordByPhone", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String,Object> resetPasswordByPhone(@Valid @ModelAttribute("resetPasswordDTO") RestPasswordDTO resetPasswordDTO,BindingResult result) {
		Map<String,Object> resultMap=processPostJSON(result);
		if(result.hasErrors()){
			return resultMap;
		}
		String phone=resetPasswordDTO.getPrinciple();
		String activeCode=resetPasswordDTO.getActiveCode();
		Account forgetPwdAccount=accountService.getAccountByPhoneOrEmail(phone);
		if(!checkRestPasswordParams(forgetPwdAccount,phone,activeCode,4)){
			resultMap.put("phone", resetPasswordDTO.getPrinciple());
			resultMap.put(MESSAGE_PARAM_KEY, messageSource.getMessage("reset.pwd.active.code.invalid", null, Locale.getDefault()));
			resultMap.put(EXECUTION_RESULT_PARAM_KEY, false);
			return resultMap;
		}
		Account account=new Account();
		account.setAccountId(forgetPwdAccount.getAccountId());
		account.setPassword(EncryptionUtil.encryptPassword(resetPasswordDTO.getNewPassword()));
		accountService.updatePassword(account);
		VerificationCode record=new VerificationCode();
		record.setUsed(true);
		record.setPrinciple(phone);
		record.setType(4);
		record.setUsedDate(Calendar.getInstance().getTime());
		record.setVerificationCode(resetPasswordDTO.getActiveCode());
		accountService.updateVerificationStatus(record);
		return resultMap;
	}
	
	
	@RequestMapping(value = "/resetPasswordByMail", method = RequestMethod.GET)
	public String resetPasswordByMail(HttpServletRequest req,Model m,RedirectAttributes redirectAttributes) {
		String mail=req.getParameter("user");
		String activeCode= req.getParameter("activeCode");
		Account forgetPwdAccount=accountService.getAccountByEmail(mail);
		if(!checkRestPasswordParams(forgetPwdAccount,mail,activeCode,3)){
			redirectAttributes.addFlashAttribute("resetPasswordErrorMessage", messageSource.getMessage("reset.password.url.expired", null, Locale.getDefault()));
			return "redirect:forgetPassword";
		}
		m.addAttribute("mail", mail);
		m.addAttribute("activeCode", activeCode);
		return "resetPasswordByMail";
	}
	
	@RequestMapping(value = "/resetPasswordByMail", method = RequestMethod.POST)
	public String resetPasswordByMail(@Valid @ModelAttribute("resetPasswordDTO") RestPasswordDTO resetPasswordDTO,BindingResult result,Model m,HttpServletRequest req,RedirectAttributes redirectAttributes) {
		if(result.hasErrors()){
			m.addAttribute("mail", resetPasswordDTO.getPrinciple());
			m.addAttribute("activeCode", resetPasswordDTO.getActiveCode());
			return "resetPasswordByMail";
		}
		String mail=resetPasswordDTO.getPrinciple();
		String activeCode=resetPasswordDTO.getActiveCode();
		Account forgetPwdAccount=accountService.getAccountByEmail(mail);
		if(!checkRestPasswordParams(forgetPwdAccount,mail,activeCode,3)){
			redirectAttributes.addFlashAttribute("resetPasswordErrorMessage", messageSource.getMessage("reset.password.url.expired", null, Locale.getDefault()));
			return "redirect:forgetPassword";
		}
		Account account=new Account();
		account.setAccountId(forgetPwdAccount.getAccountId());
		account.setPassword(EncryptionUtil.encryptPassword(resetPasswordDTO.getNewPassword()));
		accountService.updatePassword(account);
		VerificationCode record=new VerificationCode();
		record.setUsed(true);
		record.setPrinciple(mail);
		record.setType(3);
		record.setUsedDate(Calendar.getInstance().getTime());
		record.setVerificationCode(resetPasswordDTO.getActiveCode());
		accountService.updateVerificationStatus(record);
		return "resetPwdSuccess";
	}

	private boolean checkRestPasswordParams(Account account,String principle,String activeCode,int type) {
		if(account==null){
			return false;
		}
		VerificationCode record=new VerificationCode();
		record.setPrinciple(principle);
		record.setVerificationCode(activeCode);
		record.setType(type);
		record.setUsed(false);
		if(accountService.checkCodeVaild(record)){
			return true;
		}
		return false;
	}

}
