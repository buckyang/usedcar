package com.amateur.product.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.amateur.controller.BaseController;
import com.amateur.exception.ImageUploadException;
import com.amateur.product.dto.UsedCarDTO;
import com.amateur.product.service.ProductService;
import com.amateur.session.Profile;
import com.amateur.util.ImageUtil;

@Controller
@RequestMapping(value = "/product")
@SessionAttributes("profile")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

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
	public String publishUsedCar(UsedCarDTO usedCarDTO,
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
		return processGenericJSON(result);
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, Object> imageUploadJSON(
			@RequestParam MultipartFile image, HttpServletRequest request) {

		logger.debug("ProductController imageUploadJSON: beginning.");
		Map<String, Object> resultMap = null;
		String commonName = image.getName() + "_" + System.currentTimeMillis();
		try {
			processImage(image, request, commonName);
		} catch (IOException e) {
			logger.error("ProductController imageUpload: error generated. " + e);
			return processGenericJSON(false);
		} catch (ImageUploadException e) {
			logger.error("ProductController imageUpload: error generated. " + e);
			resultMap = processGenericJSON(false);
			resultMap.put(MESSAGE_PARAM_KEY, e.getMessage());
			return resultMap;
		}
		resultMap = processGenericJSON(true);
		resultMap.put("url", request.getContextPath() + "/image/upload/"
				+ commonName + "_regular.jpg");
		return resultMap;
	}

	@RequestMapping(value = "/imageupload", method = RequestMethod.POST)
	public void imageUpload(@RequestParam MultipartFile image,
			HttpServletRequest request, HttpServletResponse response) {

		logger.debug("ProductController imageUpload: beginning.");
		PrintWriter out = null;
		String commonName = image.getName() + "_" + System.currentTimeMillis();
		try {
			out = response.getWriter();
			processImage(image, request, commonName);
		} catch (IOException e) {
			logger.error("ProductController imageUpload: " + e);
			out.print("1`文件上传失败，请重试！！");
			out.flush();
			return;
		} catch (ImageUploadException e) {
			logger.error("ProductController imageUpload: " + e);
			out.print("1`" + e.getMessage());
			out.flush();
			return;
		}
		out.print("0`" + request.getContextPath() + "/image/upload/"
				+ commonName + "_regular.jpg");
		out.flush();
	}

	private void processImage(MultipartFile image, HttpServletRequest request,
			String commonName) throws IOException, ImageUploadException {

		if (!image.isEmpty()) {
			validateImage(image);
			saveImage(image, request, commonName);
		}

	}

	private void saveImage(MultipartFile image, HttpServletRequest request,
			String commonName) throws IOException {
		String realPath = request.getSession().getServletContext()
				.getRealPath("/image/upload");
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdir();
		}
		BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
		ImageUtil.createThumbnail(bufferedImage, realPath + File.separator
				+ commonName + "_jumbo.jpg", 800, 600, false);
		ImageUtil.createThumbnail(bufferedImage, realPath + File.separator
				+ commonName + "_large.jpg", 400, 300, false);
		ImageUtil.createThumbnail(bufferedImage, realPath + File.separator
				+ commonName + "_regular.jpg", 200, 150, false);
		ImageUtil.createThumbnail(bufferedImage, realPath + File.separator
				+ commonName + "_small.jpg", 100, 75, false);
		ImageUtil.createThumbnail(bufferedImage, realPath + File.separator
				+ commonName + "_thumbnail.jpg", 50, 38, false);
	}

	private void validateImage(MultipartFile image) throws ImageUploadException {

		if (!image.getContentType().equals("image/jpeg")) {
			throw new ImageUploadException("文件格式不正确， 请上传JPG或则JPEG格式图片");
		}
	}

}
