package com.amateur.product.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import com.amateur.domain.ProductImage;
import com.amateur.persistence.ProductImageMapper;
import com.amateur.product.dto.ProductImageDTO;
import com.amateur.product.validator.ProductImageValidator;
import com.amateur.util.ImageUtil;

@Controller
@RequestMapping(value = "/product")
public class ProductImageController extends BaseController {

	private static final Logger logger = Logger
			.getLogger(ProductImageController.class);

	@Autowired
	private ProductImageValidator productImageValidator;

	@Autowired
	private ImageUtil imageUtil;

	@Autowired
	private ProductImageMapper productImageMapper;

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

	@RequestMapping(value = "imageUpload", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> imageUpload(
			@Valid @ModelAttribute("productImageDTO") ProductImageDTO productImageDTO,
			BindingResult result, HttpServletRequest request) {

		Map<String, String> savedImages = null;
		String imageId = null;
		if (!result.hasErrors()) {
			try {
				savedImages = saveImage(productImageDTO.getImage(), request);
				ProductImage productImage = new ProductImage();
				productImage.setSizeJumbo(savedImages
						.get(SiteConfiguration.IMAGE_JUMBO_SIZE_KEY));
				productImage.setSizeLarge(savedImages
						.get(SiteConfiguration.IMAGE_LARGE_SIZE_KEY));
				productImage.setSizeRegular(savedImages
						.get(SiteConfiguration.IMAGE_REGULAR_SIZE_KEY));
				productImage.setSizeSmall(savedImages
						.get(SiteConfiguration.IMAGE_SMALL_SIZE_KEY));
				productImage.setSizeThumbnail(savedImages
						.get(SiteConfiguration.IMAGE_THUMBNAIL_SIZE_KEY));
				if (productImageMapper.insertProdutImage(productImage) == 1) {
					imageId = productImage.getImageId().toString();
				}
			} catch (IOException e) {
				logger.error("Process image IO error", e);
				result.rejectValue("image", "product.uplaodimage.internalerror");
			}
		}
		Map<String, Object> processPostJSON = processPostJSON(result);
		if (!result.hasErrors()) {
			processPostJSON.put("imageId", imageId);
		}
		return processPostJSON;
	}

	private Map<String, String> saveImage(MultipartFile image,
			HttpServletRequest request) throws IOException {

		Map<String, String> savedImageMap = imageUtil.saveProductImage(image,
				image.getName() + System.currentTimeMillis());
		return savedImageMap;
	}

	@Override
	protected String getPostSuccessCode() {

		return "image.upload.success";
	}
}
