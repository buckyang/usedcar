package com.amateur.product.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.amateur.controller.BaseController;
import com.amateur.product.dto.UsedCarDTO;
import com.amateur.product.service.ProductService;
import com.amateur.service.AddressService;
import com.amateur.service.BrandService;
import com.amateur.session.Profile;

@Controller
@RequestMapping(value = "/product")
@SessionAttributes("profile")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private BrandService brandService;
	private static final Logger logger = Logger
			.getLogger(ProductController.class);

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		format.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,
				true));
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

	@Override
	protected String getPostSuccessCode() {

		return "product.publish.success";
	}

	@RequestMapping(value = "/getUsedCar", method = RequestMethod.GET)
	public ModelAndView getUsedCar(String productId, String method) {

		ModelAndView modelAndView = new ModelAndView("get-usedCar");
		if (productId != null && !"".equalsIgnoreCase(productId)) {
			UsedCarDTO usedCarDTO = productService.getUsedCarById(productId);
			modelAndView.addObject("usedCarDTO", usedCarDTO);
		}
		if ("edit".equalsIgnoreCase(method)) {
			initilizeModelForProduct(modelAndView);
		} else if ("load".equalsIgnoreCase(method)) {
			modelAndView.setViewName("secure/carDetail");
		}
		return modelAndView;
	}

	private void initilizeModelForProduct(ModelAndView modelAndView) {

		modelAndView.addObject("countyMap",
				addressService.getCountyMapByCityId(225));
		modelAndView.addObject("brandMap", brandService.getBrandMap());
		modelAndView.setViewName("secure/sellcar");
	}

	@RequestMapping(value = "/getUsedCar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> getUsedCarJSON(String productId) {

		logger.debug("LoadProductController getUsedCarJSON: begin.");
		if (productId != null && !"".equalsIgnoreCase(productId)) {
			UsedCarDTO usedCarDTO = productService.getUsedCarById(productId);
			Map<String, Object> resultMap = null;
			if (usedCarDTO != null) {
				resultMap = processGenericJSON(true);
			} else {
				resultMap = processGenericJSON(false);
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			resultMap.put("productName", usedCarDTO.getProductName());
			resultMap.put("brandName", usedCarDTO.getBrandName());
			resultMap.put("seriesName", usedCarDTO.getSeriesName());
			resultMap.put("modelDisplayName", usedCarDTO.getModelDisplayName());
			resultMap.put("imageUrls", usedCarDTO.getImageUrls());
			resultMap.put("licenseImage", usedCarDTO.getLicenseImage());
			resultMap.put("certificateImage", usedCarDTO.getCertificateImage());
			resultMap.put("purchaseDate",
					format.format(usedCarDTO.getPurchaseDate()));
			resultMap.put("odometer", usedCarDTO.getOdometer());
			resultMap.put("listPrice", usedCarDTO.getListPrice());
			resultMap.put("priceType", usedCarDTO.getPriceType());
			resultMap.put("carVin", usedCarDTO.getCarVin());
			resultMap.put("carContact", usedCarDTO.getCarContact());
			resultMap.put("contactPhone", usedCarDTO.getContactPhone());
			resultMap.put("status", usedCarDTO.getStatus());
			resultMap.put("updateTime",
					format.format(usedCarDTO.getUpdateTime()));
			resultMap.put("province", usedCarDTO.getProvince());
			resultMap.put("city", usedCarDTO.getCity());
			resultMap.put("county", usedCarDTO.getCounty());
			resultMap.put("street", usedCarDTO.getStreet());
			return resultMap;
		}
		return processGenericJSON(false);
	}

	@RequestMapping(value = "/publishUsedCar", method = RequestMethod.GET)
	public ModelAndView form() {

		ModelAndView modelAndView = new ModelAndView("publish-usedCar-form");
		initilizeModelForProduct(modelAndView);
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

	@RequestMapping(value = "/publishUsedCar", method = RequestMethod.POST)
	public String publishUsedCar(@Valid UsedCarDTO usedCarDTO,
			@ModelAttribute("profile") Profile profile, BindingResult result) {

		logger.debug("ProductController publishUsedCar: begin.");
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

	@RequestMapping(value = "/deleteUsedCar", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> deleteUsedCarJSON(String productId) {

		boolean result = productService.deleteUsedCar(productId);
		return processGenericJSON(result);
	}

}
