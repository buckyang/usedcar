package com.amateur.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import org.springframework.web.multipart.MultipartFile;

import com.amateur.configuration.SiteConfiguration;
import com.amateur.controller.BaseController;
import com.amateur.product.dto.ProductImageDTO;
import com.amateur.product.validator.ProductImageValidator;
import com.amateur.service.SequenceService;
import com.amateur.util.ImageUtil;

@Controller
@RequestMapping(value = "/product")
public class ProductImageController extends BaseController {
	public static final String	PRODUCT_IMAGE_LIST_SESSION_KEY	= "imageList";

	public static final String	PRODUCT_ID_SESSION_KEY			= "productId";

	private static final Logger	logger							= Logger.getLogger(ProductImageController.class);
	
	@Autowired
	private ProductImageValidator productImageValidator;
	
	@Autowired
	private SequenceService sequenceService;
	
	@Autowired
	private ImageUtil imageUtil;
	
	@Autowired
	private SiteConfiguration siteConfiguration;
	
	@InitBinder("productImageDTO")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(productImageValidator);
	}
	
	@ModelAttribute("productImageDTO")
	public ProductImageDTO createFormBean() {
		return new ProductImageDTO();
	}
	

	@RequestMapping(value = "imageUpload", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object>  imageUpload(@Valid @ModelAttribute("productImageDTO") ProductImageDTO productImageDTO,
			BindingResult result, Model m, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> savedImages = null;
		if(!result.hasErrors()){
			try {
				savedImages = saveImage(productImageDTO.getImage(), request);
			} catch (IOException e) {
				logger.error("Process image IO error", e);
				result.rejectValue("image", "product.uplaodimage.internalerror");
			}
		}
		Map<String, Object> processPostJSON = processPostJSON(result);
		if(!result.hasErrors()){
			processPostJSON.put("uploadedImage", siteConfiguration.getStaticServerHostNameURL() + savedImages.get(SiteConfiguration.IMAGE_SMALL_SIZE_KEY));
		}
		return processPostJSON;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> saveImage(MultipartFile image, HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		List<Map<String, String>> imageList = (List<Map<String, String>>) session.getAttribute(PRODUCT_IMAGE_LIST_SESSION_KEY);
		if(imageList == null){
			imageList = new ArrayList<Map<String,String>>();
			session.setAttribute(PRODUCT_IMAGE_LIST_SESSION_KEY, imageList);
		}
		String productId = (String) session.getAttribute(PRODUCT_ID_SESSION_KEY);
		if(productId == null){
			productId = sequenceService.getProductId();
			session.setAttribute(PRODUCT_ID_SESSION_KEY, productId);
		}
		Map<String, String> savedImageMap = imageUtil.saveProductImage(image, productId, Long.toString(System.currentTimeMillis()));
		imageList.add(savedImageMap);
		return savedImageMap;
		
	}

}
