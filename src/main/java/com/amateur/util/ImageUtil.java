package com.amateur.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amateur.configuration.SiteConfiguration;

@Component
public class ImageUtil {

	private static final String IMAGE_EXTENSION = ".jpg";

	public static final String WEB_RESOURCE_SEPARATOR = "/";

	private static final Logger logger = Logger.getLogger(ImageUtil.class);

	private Resource productImageLocation;

	private Resource productImageOriginalLocation;
	@Autowired
	private SiteConfiguration siteConfiguration;

	public Map<String, String> saveProductImage(MultipartFile imageSource,
			String imageName, String accountId) throws IOException {

		logger.debug("ImageUtil saveProductImage: begin!");
		boolean isRatio = false;
		BufferedImage image = ImageIO.read(imageSource.getInputStream());
		Map<String, String> imageSizeMap = new HashMap<String, String>();
		File productImageDir = new File(getProductImageLocation().getFile()
				.getAbsolutePath() + File.separator + accountId);
		if (!productImageDir.exists()) {
			FileUtils.forceMkdir(productImageDir);
		}
		for (Entry<String, List<Integer>> imageDimension : siteConfiguration
				.getImageSizeDimension().entrySet()) {
			String destinationFile = productImageDir.getAbsolutePath()
					+ File.separator
					+ imageName
					+ siteConfiguration.getImageSizeSuffixMap().get(
							imageDimension.getKey()) + IMAGE_EXTENSION;
			String generatedImageURL = siteConfiguration
					.getProductImageContext()
					+ WEB_RESOURCE_SEPARATOR
					+ imageName
					+ siteConfiguration.getImageSizeSuffixMap().get(
							imageDimension.getKey()) + IMAGE_EXTENSION;
			int width = imageDimension.getValue().get(0);
			int height = imageDimension.getValue().get(1);
			if (isRatio) {
				double ratio = 1.0;
				if (image.getHeight() > height || image.getWidth() > width) {
					if (image.getHeight() > image.getWidth()) {
						ratio = height / image.getHeight();
					} else {
						ratio = width / image.getWidth();
					}
				}
				width = (int) (image.getWidth() * ratio);
				height = (int) (image.getHeight() * ratio);
			}
			BufferedImage bfImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			bfImage.getGraphics().drawImage(
					image.getScaledInstance(width, height, Image.SCALE_SMOOTH),
					0, 0, null);
			ImageIO.write(bfImage, "jpg", new File(destinationFile));
			imageSizeMap.put(imageDimension.getKey(), generatedImageURL);
		}
		FileUtils.forceMkdir(getProductImageOriginalLocation().getFile());
		FileUtils.writeByteArrayToFile(new File(
				getProductImageOriginalLocation().getFile().getAbsolutePath()
						+ File.separator + accountId + File.separator + imageName + IMAGE_EXTENSION),
				imageSource.getBytes());
		return imageSizeMap;
	}

	public Resource getProductImageLocation() {
		return productImageLocation;
	}

	public void setProductImageLocation(Resource productImageLocation) {
		this.productImageLocation = productImageLocation;
	}

	public Resource getProductImageOriginalLocation() {
		return productImageOriginalLocation;
	}

	public void setProductImageOriginalLocation(
			Resource productImageOriginalLocation) {
		this.productImageOriginalLocation = productImageOriginalLocation;
	}

}
