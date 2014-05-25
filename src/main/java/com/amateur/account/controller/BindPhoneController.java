package com.amateur.account.controller;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amateur.account.dto.BindPhoneDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.BindPhoneValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.VerificationCode;
import com.amateur.session.Profile;
@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class BindPhoneController extends BaseController {
	
	@Autowired
	private BindPhoneValidator bindPhoneValidator;

	@Autowired
	private AccountService accountService;
	
	@Override
	protected String getPostSuccessCode() {
		return "bind.phone.success";
	}

	@InitBinder("bindPhoneDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(bindPhoneValidator);
	}

	@ModelAttribute("bindPhoneDTO")
	public BindPhoneDTO createFormBean() {
		BindPhoneDTO bindPhoneDTO = new BindPhoneDTO();
		return bindPhoneDTO;
	}
	
	
	@RequestMapping(value = "/bindPhone", method = RequestMethod.GET)
	public String bindPhoneDispatcher(@ModelAttribute("profile") Profile profile,Model mode) {
		Account account=accountService.getAccountById(profile.getAccountId());
		if(account==null){
			return "redirect:/login";
		}
		Boolean bindPhone=account.getBindPhone();
		if(bindPhone==null||!bindPhone){
			return "account/bindNewPhone";
		}
		BindPhoneDTO bindPhoneDTO=new BindPhoneDTO();
		bindPhoneDTO.setPhoneNumber(account.getPhone());
		mode.addAttribute(bindPhoneDTO);
		return "account/validBindedPhone";
	}
	
	@RequestMapping(value = "/bindNewPhone", method = RequestMethod.GET)
	public String bindPhone(@ModelAttribute("profile") Profile profile,Model mode) {
		return "redirect:/account/bindPhone";
	}
	
	@RequestMapping(value = "/bindNewPhone", method = RequestMethod.POST)
	public String bindNewPhone(@Valid @ModelAttribute("bindPhoneDTO")BindPhoneDTO bindPhoneDTO,BindingResult result, @ModelAttribute("profile") Profile profile,Model mode) {
		if(!result.hasErrors()){
			VerificationCode code=new VerificationCode();
			code.setPrinciple(bindPhoneDTO.getPhoneNumber());
			code.setVerificationCode(bindPhoneDTO.getCode());
			code.setType(6);
			code.setUsed(false);
			if(accountService.checkCodeVaild(code)){
				Account account=new Account();
				account.setAccountId(profile.getAccountId());
				account.setPhone(bindPhoneDTO.getPhoneNumber());
				account.setBindPhone(true);
				
				code.setUsed(true);
				code.setType(6);
				code.setUsedDate(Calendar.getInstance().getTime());
				
				accountService.updateAccountPhoneBinding(account,code);
				return "redirect:viewUserInfo";
			}else{
				result.rejectValue("code", "bind.phone.invalid.code");
			}
		}
		bindPhoneDTO.setCode(null);
		mode.addAttribute(bindPhoneDTO);
		return "account/bindNewPhone";
	}
	
	@RequestMapping(value = "/validBindedPhone", method = RequestMethod.GET)
	public String validBindedPhone(@ModelAttribute("bindPhoneDTO")BindPhoneDTO bindPhoneDTO,@ModelAttribute("profile") Profile profile,Model mode) {
		Account account=accountService.getAccountById(profile.getAccountId());
		if(account==null){
			return "redirect:/login";
		}
		Boolean bindPhone=account.getBindPhone();
		if(bindPhone!=null && bindPhone){
			bindPhoneDTO.setPhoneNumber(account.getPhone());
			mode.addAttribute(bindPhoneDTO);
			return "account/validBindedPhone";
		}else{
			return "redirect:/account/bindNewPhone";
		}
	}
	
	@RequestMapping(value = "/validBindedPhone", method = RequestMethod.POST)
	public String validBindedPhone(@Valid @ModelAttribute("bindPhoneDTO")BindPhoneDTO bindPhoneDTO,BindingResult result, @ModelAttribute("profile") Profile profile,Model mode) {
		if(!result.hasErrors()){
			VerificationCode code=new VerificationCode();
			code.setPrinciple(bindPhoneDTO.getPhoneNumber());
			code.setVerificationCode(bindPhoneDTO.getCode());
			code.setType(7);
			code.setUsed(false);
			if(accountService.checkCodeVaild(code)){
				code.setUsed(true);
				code.setType(7);
				code.setUsedDate(Calendar.getInstance().getTime());
				accountService.updateVerificationStatus(code);
				bindPhoneDTO.setCode(null);
				bindPhoneDTO.setPhoneNumber(null);
				return "account/bindNewPhone";
			}else{
				result.rejectValue("code", "bind.phone.invalid.code");
			}
		}
		bindPhoneDTO.setCode(null);
		mode.addAttribute(bindPhoneDTO);
		return "account/validBindedPhone";
	}
	
	@RequestMapping(value = "/validBindedPhone",  method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> validBindedPhoneJSON(@Valid @ModelAttribute("bindPhoneDTO")BindPhoneDTO bindPhoneDTO,BindingResult result, @ModelAttribute("profile") Profile profile) {
		if(!result.hasErrors()){
			VerificationCode code=new VerificationCode();
			code.setPrinciple(bindPhoneDTO.getPhoneNumber());
			code.setVerificationCode(bindPhoneDTO.getCode());
			code.setType(7);
			code.setUsed(false);
			if(accountService.checkCodeVaild(code)){
				code.setUsed(true);
				code.setType(7);
				code.setUsedDate(Calendar.getInstance().getTime());
				accountService.updateVerificationStatus(code);
				Map<String,Object> resultMap=processPostJSON(result);
				resultMap.put("message", messageSource.getMessage("valid.binded.phone.success", null, Locale.getDefault()));
				return resultMap;
			}else{
				result.rejectValue("code", "bind.phone.invalid.code");
			}
		}
		return processPostJSON(result);
	}
	
	@RequestMapping(value = "/bindNewPhone",  method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> bindNewPhoneJSON(@Valid @ModelAttribute("bindPhoneDTO")BindPhoneDTO bindPhoneDTO,BindingResult result, @ModelAttribute("profile") Profile profile,Model mode) {
		if(!result.hasErrors()){
			VerificationCode code=new VerificationCode();
			code.setPrinciple(bindPhoneDTO.getPhoneNumber());
			code.setVerificationCode(bindPhoneDTO.getCode());
			code.setType(6);
			code.setUsed(false);
			if(accountService.checkCodeVaild(code)){
				Account account=new Account();
				account.setAccountId(profile.getAccountId());
				account.setPhone(bindPhoneDTO.getPhoneNumber());
				account.setBindPhone(true);
				
				code.setUsed(true);
				code.setType(6);
				code.setUsedDate(Calendar.getInstance().getTime());
				
				Map<String,Object> resultMap=processPostJSON(result);
				resultMap.put("message", messageSource.getMessage("binded.phone.success", null, Locale.getDefault()));
				return resultMap;
			}else{
				result.rejectValue("code", "bind.phone.invalid.code");
			}
		}
		return processPostJSON(result);
	}

}
