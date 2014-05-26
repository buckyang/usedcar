package com.amateur.account.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.amateur.account.dto.BindMailDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.BindMailValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.VerificationCode;
import com.amateur.service.impl.MailSenderServiceImpl;
import com.amateur.session.Profile;

@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class BindMailController extends BaseController {
	

	@Autowired
	private BindMailValidator bindMailValidator;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MailSenderServiceImpl mailSender;
	
	@Override
	protected String getPostSuccessCode() {
		return "bind.mail.success";
	}

	@InitBinder("bindMailDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(bindMailValidator);
	}

	@ModelAttribute("bindMailDTO")
	public BindMailDTO createFormBean() {
		BindMailDTO bindMailDTO = new BindMailDTO();
		return bindMailDTO;
	}
	
	
	@RequestMapping(value = "/bindMail", method = RequestMethod.GET)
	public String bindMailDispatcher(@ModelAttribute("profile") Profile profile,Model mode,HttpServletRequest req) {
		Account account=accountService.getAccountById(profile.getAccountId());
		if(account==null){
			return "redirect:/login";
		}
		Boolean bindMail=account.getBindEmail();
		if(bindMail==null||!bindMail){
			String errorMsg=req.getParameter("validMailErrorMessage");
			if(!StringUtils.isEmpty(errorMsg)){
				mode.addAttribute("validMailErrorMessage", messageSource.getMessage("valid.binded.mail.failed", null, Locale.getDefault()));
			}
			return "account/applyBindNewMail";
		}
		BindMailDTO bindMailDTO=new BindMailDTO();
		bindMailDTO.setMail(account.getEmail());
		mode.addAttribute(bindMailDTO);
		String errorMsg=req.getParameter("validMailErrorMessage");
		if(!StringUtils.isEmpty(errorMsg)){
			mode.addAttribute("validMailErrorMessage", messageSource.getMessage("valid.binded.mail.failed", null, Locale.getDefault()));
		}
		return "account/applyValidBindedMail";
	}
	
	@RequestMapping(value = "/applyBindNewMail", method = RequestMethod.GET)
	public String applyBindNewMail(@ModelAttribute("profile") Profile profile,Model mode) {
		return "redirect:/account/bindMail";
	}
	
	@RequestMapping(value = "/applyBindNewMail", method = RequestMethod.POST)
	public String applyBindNewMail(@Valid @ModelAttribute("bindMailDTO")BindMailDTO bindMailDTO,BindingResult result, @ModelAttribute("profile") Profile profile,Model mode) {
		if(!result.hasErrors()){
			String newMail=bindMailDTO.getMail();
			Account account=accountService.getAccountById(profile.getAccountId());
			if(account==null){
				return "redirect:/login";
			}
			Map<String,Object> dataMap=new HashMap<String,Object>();
			String username=account.getRealName()==null?account.getNickname():account.getRealName();
			dataMap.put("mail",newMail);
			dataMap.put("realname",username);
			String code=UUID.randomUUID().toString().replace("-", "");
			dataMap.put("activeCode",code );
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.HOUR, 1);
			Date date=calendar.getTime();
			dataMap.put("expireDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			dataMap.put("from", "testForgetpwd@163.com");
			dataMap.put("subject", "[2soce的邮件]邮箱验证提醒");
			dataMap.put("email_type_to", new String []{newMail});
			VerificationCode record =new VerificationCode();
			record.setPrinciple("'"+newMail+"'");
			record.setType(5);
			record.setUsed(false);
			record.setUsedDate(null);
			record.setValidDate(date);
			record.setVerificationCode(code);
			accountService.addVerificationCode(record);
			mailSender.sendMail(dataMap, "emailForBindNewMail.ftl");
			mode.addAttribute("applyBindMailSuccess",messageSource.getMessage("apply.bind.mail.success",new String[]{newMail},Locale.getDefault()));
			return "account/commitSuccessPage";
		}
		return "account/applyBindNewMail";
	}
	
	@RequestMapping(value = "/validBindNewMail", method = RequestMethod.GET)
	public String validBindNewMail(HttpServletRequest req,@ModelAttribute("profile") Profile profile,Model mode) {
		String mail=req.getParameter("mail");
		String activeCode= req.getParameter("activeCode");
		Account account=accountService.getAccountById(profile.getAccountId());
		if(account==null){
			return "redirect:/login";
		}
		if(!checkParams(mail,activeCode,mode,5)){
			return "redirect:/account/bindMail";
		}
		account.setAccountId(profile.getAccountId());
		account.setEmail(mail);
		account.setBindEmail(true);
		
		VerificationCode code=new VerificationCode();
		code.setUsed(true);
		code.setType(5);
		code.setPrinciple(mail);
		code.setVerificationCode(activeCode);
		code.setUsedDate(Calendar.getInstance().getTime());
		accountService.updateAccountMailBinding(account,code);
		return "redirect:viewUserInfo";
	}
	
	@RequestMapping(value = "/applyValidBindedMail", method = RequestMethod.GET)
	public String applyValidBindedMail(@ModelAttribute("bindMailDTO")BindMailDTO bindMailDTO,@ModelAttribute("profile") Profile profile,Model mode) {
		Account account=accountService.getAccountById(profile.getAccountId());
		if(account==null){
			return "redirect:/login";
		}
		Boolean bindMail=account.getBindEmail();
		if(bindMail!=null && bindMail){
			bindMailDTO.setMail(account.getEmail());
			mode.addAttribute(bindMailDTO);
			return "account/applyValidBindedMail";
		}else{
			return "redirect:/account/bindMail";
		}
	}
	
	@RequestMapping(value = "/applyValidBindedMail", method = RequestMethod.POST)
	public String applyValidBindedMail(@Valid @ModelAttribute("bindMailDTO")BindMailDTO bindMailDTO,BindingResult result, @ModelAttribute("profile") Profile profile,Model mode) {
		if(!result.hasErrors()){
			String bindedMail=bindMailDTO.getMail();
			Account account=accountService.getAccountById(profile.getAccountId());
			if(account==null){
				return "redirect:/login";
			}
			Map<String,Object> dataMap=new HashMap<String,Object>();
			String username=account.getRealName()==null?account.getNickname():account.getRealName();
			dataMap.put("mail",bindedMail);
			dataMap.put("realname",username);
			String code=UUID.randomUUID().toString().replace("-", "");
			dataMap.put("activeCode",code );
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.HOUR, 1);
			Date date=calendar.getTime();
			dataMap.put("expireDate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			dataMap.put("from", "testForgetpwd@163.com");
			dataMap.put("subject", "[2soce的邮件]邮箱验证提醒");
			dataMap.put("email_type_to", new String []{bindedMail});
			VerificationCode record =new VerificationCode();
			record.setPrinciple("'"+bindedMail+"'");
			record.setType(8);
			record.setUsed(false);
			record.setUsedDate(null);
			record.setValidDate(date);
			record.setVerificationCode(code);
			accountService.addVerificationCode(record);
			mailSender.sendMail(dataMap, "emailForValidBindedMail.ftl");
			mode.addAttribute("applyBindMailSuccess",messageSource.getMessage("apply.bind.mail.success",new String[]{bindedMail},Locale.getDefault()));
			return "account/commitSuccessPage";
		}
		mode.addAttribute(bindMailDTO);
		return "account/applyValidBindedMail";
	}
	
	@RequestMapping(value = "/validBindedMail", method = RequestMethod.GET)
	public String validBindedMail(HttpServletRequest req,@ModelAttribute("profile") Profile profile,Model mode) {
		String mail=req.getParameter("mail");
		String activeCode= req.getParameter("activeCode");
		Account account=accountService.getAccountById(profile.getAccountId());
		if(account==null){
			return "redirect:/login";
		}
		if(!checkParams(mail,activeCode,mode,8)){
			return "redirect:/account/bindMail";
		}
		VerificationCode code=new VerificationCode();
		code.setPrinciple(mail);
		code.setUsed(true);
		code.setType(8);
		code.setVerificationCode(activeCode);
		code.setUsedDate(Calendar.getInstance().getTime());
		accountService.updateVerificationStatus(code);
		return "account/applyBindNewMail";
	}
	
	private boolean checkParams(String mail, String activeCode,Model m,int type) {
		if(StringUtils.isBlank(mail)||StringUtils.isBlank(activeCode)){
			m.addAttribute("validMailErrorMessage", messageSource.getMessage("valid.binded.mail.failed", null, Locale.getDefault()));
			return false;
		}
		VerificationCode record=new VerificationCode();
		record.setPrinciple(mail);
		record.setVerificationCode(activeCode);
		record.setType(type);
		record.setUsed(false);
		if(accountService.checkCodeVaild(record)){
			return true;
		}
		m.addAttribute("validMailErrorMessage", messageSource.getMessage("valid.binded.mail.failed", null, Locale.getDefault()));
		return false;
	}
	
}
