package com.amateur.account.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
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

import com.amateur.account.dto.ManagerUserInfoDTO;
import com.amateur.account.service.AccountService;
import com.amateur.account.validator.ManagerUserInfoValidator;
import com.amateur.controller.BaseController;
import com.amateur.domain.Account;
import com.amateur.domain.Address;
import com.amateur.session.Profile;

@Controller
@RequestMapping("account")
@SessionAttributes("profile")
public class ManagerUserInfoController extends BaseController {

	private static final Logger logger = Logger.getLogger(ManagerUserInfoController.class);
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private ManagerUserInfoValidator userInfoValidator;

	@Override
	protected String getPostSuccessCode() {
		return "update.user.info.success";
	}

	@InitBinder("managerUserInfoDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(userInfoValidator);
	}

	@ModelAttribute("managerUserInfoDTO")
	public ManagerUserInfoDTO createFormBean(
			@ModelAttribute("profile") Profile profile) {
		ManagerUserInfoDTO userInfoDTO = new ManagerUserInfoDTO();
		userInfoDTO.setAccountId(profile.getAccountId());
		return userInfoDTO;
	}

	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.GET)
	public void viewUserInfo(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			Model mode, BindingResult result) {
		if (!result.hasErrors()) {
			loadUserInfo(profile, managerUserInfoDTO);
			mode.addAttribute(managerUserInfoDTO);
		}
	}

	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String,Object> viewUserInfoJSON(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			Model mode, BindingResult result) {
		Map<String , Object> resultMap=null;
		if (!result.hasErrors()) {
			resultMap=processGenericJSON(true);
		}else{
			resultMap=processGenericJSON(false);
		}
		loadUserInfo(profile, managerUserInfoDTO);
		resultMap.put("userInfo", managerUserInfoDTO);
		return resultMap;
	}

	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.POST)
	public void updateUserInfo(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			BindingResult result, Model mode) {
		if (!result.hasErrors()) {
			Account account = new Account();
			BeanUtils.copyProperties(managerUserInfoDTO, account, new String[] {
					"password", "birthdate", "updateTime", "registrationDate",
					"accountType", "profileHash" });
			String source = new StringBuilder(managerUserInfoDTO.getBirthyear())
					.append("-").append(managerUserInfoDTO.getBirthmonth())
					.append("-").append(managerUserInfoDTO.getBirthday())
					.toString();
			try {
				account.setBirthdate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(source));
			} catch (ParseException e) {
				logger.error("The birthdate's pattern is error."+source);
			}
			account.setAccountId(profile.getAccountId());
			accountService.updateUserInfo(account);

			Address newHomeAddress = new Address();
			BeanUtils.copyProperties(managerUserInfoDTO, newHomeAddress,new String []{"addressId"});
			newHomeAddress.setAccountId(profile.getAccountId());
			accountService.updateHomeAddress(newHomeAddress);
			mode.addAttribute("message", getPostSuccessMesage());
		}
		loadUserInfo(profile, managerUserInfoDTO);
		mode.addAttribute(managerUserInfoDTO);
	}

	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> updateUserInfoJSON(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			BindingResult result, Model mode) {
		if (!result.hasErrors()) {
			Account account = new Account();
			BeanUtils.copyProperties(managerUserInfoDTO, account, new String[] {
					"password", "birthdate", "updateTime", "registrationDate",
					"accountType", "profileHash" });
			String source = new StringBuilder(managerUserInfoDTO.getBirthyear())
					.append("-").append(managerUserInfoDTO.getBirthmonth())
					.append("-").append(managerUserInfoDTO.getBirthday())
					.toString();
			try {
				account.setBirthdate(new SimpleDateFormat("yyyy-MM-dd")
						.parse(source));
			} catch (ParseException e) {
				
			}
			account.setAccountId(profile.getAccountId());
			accountService.updateUserInfo(account);
			
			Address newHomeAddress = new Address();
			newHomeAddress.setAccountId(profile.getAccountId());
			BeanUtils.copyProperties(managerUserInfoDTO, newHomeAddress,new String []{"addressId"});
			accountService.updateHomeAddress(newHomeAddress);
		}
		loadUserInfo(profile, managerUserInfoDTO);
		Map<String,Object> resultMap= processPostJSON(result);
		resultMap.put("userInfo", managerUserInfoDTO);
		return resultMap;
	}

	private void loadUserInfo(Profile profile,
			ManagerUserInfoDTO managerUserInfoDTO) {
		Integer accountId = profile.getAccountId();
		Account account = accountService.getAccountById(accountId);
		BeanUtils.copyProperties(account, managerUserInfoDTO, new String[] {
				"birthyear", "birthmonth", "birthday", "province", "city",
				"county", "street" });
		Date birthdate = account.getBirthdate();
		if (birthdate != null) {
			String[] year_month_day = new SimpleDateFormat("yyyy-MM-dd")
					.format(birthdate).split("-");
			managerUserInfoDTO.setBirthyear(year_month_day[0]);
			managerUserInfoDTO.setBirthmonth(year_month_day[1]);
			managerUserInfoDTO.setBirthday(year_month_day[2]);
		}
		Address address = accountService.getHomeAddressByAccountId(accountId);
		BeanUtils.copyProperties(address, managerUserInfoDTO, new String[] {
				"accountId", "addressId", "birthday" });
	}

}
