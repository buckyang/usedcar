package com.amateur.account.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.amateur.account.dto.HomeAddressDTO;
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, "birthdate",
				new CustomDateEditor(dateFormat, true));
		binder.setValidator(userInfoValidator);
	}

	@ModelAttribute("managerUserInfoDTO")
	public ManagerUserInfoDTO createFormBean(
			@ModelAttribute("profile") Profile profile) {
		ManagerUserInfoDTO userInfoDTO = new ManagerUserInfoDTO();
		HomeAddressDTO homeAddress = new HomeAddressDTO();
		userInfoDTO.setHomeAddress(homeAddress);
		userInfoDTO.setAccountId(profile.getAccountId() == null ? 1 : profile
				.getAccountId());
		homeAddress.setAccountId(profile.getAccountId() == null ? 1 : profile
				.getAccountId());
		return userInfoDTO;
	}

	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.GET)
	public void viewUserInfo(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			Model mode,BindingResult result) {
		if(!result.hasErrors()){
			loadUserInfo(profile, managerUserInfoDTO);
			mode.addAttribute(managerUserInfoDTO);
		}
	}
	
	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.GET,  produces="application/json")
	public void viewUserInfoJSON(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			Model mode,BindingResult result) {
		if(!result.hasErrors()){
			loadUserInfo(profile, managerUserInfoDTO);
			mode.addAttribute(managerUserInfoDTO);
		}
	}

	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.POST)
	public void updateUserInfo(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			BindingResult result,Model mode) {
		if (!result.hasErrors()) {
			Account account = new Account();
			BeanUtils.copyProperties(managerUserInfoDTO, account,
					new String[] { "homeAddress" });
			accountService.updateUserInfo(account);
			HomeAddressDTO homeAddress = managerUserInfoDTO.getHomeAddress();
			Address newHomeAddress = new Address();
			BeanUtils.copyProperties(homeAddress, newHomeAddress);
			accountService.updateHomeAddress(newHomeAddress);
			loadUserInfo(profile, managerUserInfoDTO);
			mode.addAttribute(managerUserInfoDTO);
			mode.addAttribute("message", getPostSuccessMesage());
		}
	}
	
	@RequestMapping(value = "/managerUserInfo", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Map<String, Object> updateUserInfoJSON(
			@ModelAttribute("profile") Profile profile,
			@Valid @ModelAttribute("managerUserInfoDTO") ManagerUserInfoDTO managerUserInfoDTO,
			BindingResult result,Model mode) {
		if (!result.hasErrors()) {
			Account account = new Account();
			BeanUtils.copyProperties(managerUserInfoDTO, account,
					new String[] { "homeAddress" });
			accountService.updateUserInfo(account);
			HomeAddressDTO homeAddress = managerUserInfoDTO.getHomeAddress();
			Address newHomeAddress = new Address();
			BeanUtils.copyProperties(homeAddress, newHomeAddress);
			accountService.updateHomeAddress(newHomeAddress);
			loadUserInfo(profile, managerUserInfoDTO);
			mode.addAttribute(managerUserInfoDTO);
			mode.addAttribute("message", getPostSuccessMesage());
		}
		return 	processPostJSON(result);
	}
	
	private void loadUserInfo(Profile profile,
			ManagerUserInfoDTO managerUserInfoDTO) {
		Integer accountId = profile.getAccountId();
		accountId = accountId == null ? 1 : accountId;
		Account account = accountService.getAccountById(accountId);
		BeanUtils.copyProperties(account, managerUserInfoDTO,
				new String[] { "homeAddress" });
		HomeAddressDTO homeAddress = managerUserInfoDTO.getHomeAddress();
		Address address = accountService.getHomeAddressByAccountId(accountId);
		BeanUtils.copyProperties(address, homeAddress);
	}

}
