package com.amateur.product.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.amateur.controller.BaseController;
import com.amateur.product.dto.UsedCarDTO;
import com.amateur.product.service.ProductService;
import com.amateur.session.Profile;

@Controller
@RequestMapping(value = "/product")
@SessionAttributes("profile")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		format.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
	}

	@Override
	protected String getPostSuccessCode() {
		return "product.publish.success";
	}

	@RequestMapping(value = "/publishusedcar", method = RequestMethod.GET)
	public String form(String productId, ModelAndView modelAndView) {
		if (productId != null && !"".equalsIgnoreCase(productId)) {
			UsedCarDTO usedCarDTO = productService.getUsedCarById(productId);
			modelAndView.addObject("usedCarDTO", usedCarDTO);
		}
		return "secure/sellcar";
	}

	@RequestMapping(value = "/publishusedcar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void formJSON() {
	}

	@ModelAttribute("usedCarDTO")
	private UsedCarDTO createFormBean(@ModelAttribute("profile") Profile profile) {
		UsedCarDTO usedCarDTO = new UsedCarDTO();
		usedCarDTO.setProvinceId(28);
		usedCarDTO.setCityId(225);
		usedCarDTO.setCarContact(profile.getRealName());
		usedCarDTO.setContactPhone(profile.getPhone());
		usedCarDTO.setPriceType("一口价");
		return usedCarDTO;
	}

	@RequestMapping(value = "/publishusedcar", method = RequestMethod.POST)
	public String publishUsedCar(@Valid UsedCarDTO usedCarDTO,
			@ModelAttribute("profile") Profile profile, BindingResult result) {
		if (result.hasErrors()) {
			return "secure/sellcar";
		}
		handleUsedCar(usedCarDTO, profile);
		return "redirect:/product/publishusedcar";
	}

	private void handleUsedCar(UsedCarDTO usedCarDTO, Profile profile) {
		usedCarDTO.setAccountId(profile.getAccountId());
		if (usedCarDTO.getProductId() != null
				&& !"".equalsIgnoreCase(usedCarDTO.getProductId())) {
			productService.updateUsedCar(usedCarDTO);
		} else {
			productService.publishUsedCar(usedCarDTO);
		}
	}

	@RequestMapping(value = "/publishusedcar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> publishUsedCarJSON(@Valid UsedCarDTO usedCarDTO,
			@ModelAttribute("profile") Profile profile, BindingResult result) {
		if (!result.hasErrors()) {
			handleUsedCar(usedCarDTO, profile);
		}
		return processPostJSON(result);
	}

	@RequestMapping(value = "/deleteusedcar", method = RequestMethod.GET)
	public String deleteUsedCar(String productId, ModelAndView mav) {
		boolean result = productService.deleteUsedCar(productId);
		mav.addObject(EXECUTION_RESULT_PARAM_KEY, result);
		return "redirect:/product/publishusedcar";
	}

	@RequestMapping(value = "/deleteusedcar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteUsedCarJSON(String productId) {
		boolean result = productService.deleteUsedCar(productId);
		return processGETJSON(result);
	}

}
