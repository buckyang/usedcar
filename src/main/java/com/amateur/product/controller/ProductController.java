package com.amateur.product.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amateur.controller.BaseController;
import com.amateur.exception.ImageUploadException;
import com.amateur.product.dto.UsedCarDTO;
import com.amateur.product.service.ProductService;
import com.amateur.service.AddressService;
import com.amateur.service.BrandService;
import com.amateur.service.SequenceService;
import com.amateur.session.Profile;
import com.amateur.util.ImageUtil;

@Controller
@RequestMapping(value = "/product")
@SessionAttributes("profile")
public class ProductController extends BaseController {
	public static final String PRODUCT_IMAGE_LIST_SESSION_KEY = "imageList";
	
	public static final String PRODUCT_ID_SESSION_KEY = "productId";
	
	@Autowired
	private ProductService productService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private SequenceService sequenceService;
	private static final Logger logger = Logger
			.getLogger(ProductController.class);

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

	@RequestMapping(value = "/publishUsedCar", method = RequestMethod.GET)
	public ModelAndView form(String productId) {
		ModelAndView modelAndView = new ModelAndView("publish-usedCar-form");
		if (productId != null && !"".equalsIgnoreCase(productId)) {
			UsedCarDTO usedCarDTO = productService.getUsedCarById(productId);
			modelAndView.addObject("usedCarDTO", usedCarDTO);
		}
		modelAndView.addObject("countyMap",
				addressService.getCountyMapByCityId(225));
		modelAndView.addObject("brandMap", brandService.getBrandMap());
		modelAndView.setViewName("secure/sellcar");
		return modelAndView;
	}

	@RequestMapping(value = "/getSeries", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getSeriesMap(String brandId) {
		return brandService.getSeriesMapByBrandId(Integer.valueOf(brandId));
	}

	@RequestMapping(value = "/getModels", method = RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getModels(String seriesId) {
		return brandService.getModelMapBySeriesId(Integer.valueOf(seriesId));
	}

	@RequestMapping(value = "/publishUsedCar", method = RequestMethod.GET, produces = "application/json")
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

	@RequestMapping(value = "/publishUsedCar", method = RequestMethod.POST)
	public String publishUsedCar(UsedCarDTO usedCarDTO,
			@ModelAttribute("profile") Profile profile, BindingResult result) {

		if (result.hasErrors()) {
			return "secure/sellcar";
		}
		handleUsedCar(usedCarDTO, profile);
		return "redirect:/product/publishUsedCar";
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

	@RequestMapping(value = "/publishUsedCar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> publishUsedCarJSON(@Valid UsedCarDTO usedCarDTO,
			@ModelAttribute("profile") Profile profile, BindingResult result) {
		if (!result.hasErrors()) {
			handleUsedCar(usedCarDTO, profile);
		}
		return processPostJSON(result);
	}

	@RequestMapping(value = "/deleteUsedCar", method = RequestMethod.GET)
	public String deleteUsedCar(String productId, ModelAndView mav) {
		boolean result = productService.deleteUsedCar(productId);
		mav.addObject(EXECUTION_RESULT_PARAM_KEY, result);
		return "redirect:/product/publishusedcar";
	}

	@RequestMapping(value = "/deleteUsedCar", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteUsedCarJSON(String productId) {
		boolean result = productService.deleteUsedCar(productId);
		return processGenericJSON(result);
	}


}
