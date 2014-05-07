package com.amateur.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

public class ImageUtil {

	private static final Logger logger = Logger.getLogger(ImageUtil.class);

	private String destDirectory;

	public void createThumbnail(BufferedImage image, String fileName,
			int width, int height, boolean isRatio) throws IOException {

		logger.debug("ImageUtil createThumbnail: beginning.");
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
				image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
				0, null);
		String destination = getDestDirectory()+File.separator + fileName;
		String formatName = destination.substring(destination.lastIndexOf(".") + 1);
		ImageIO.write(bfImage, formatName, new File(destination));
	}

	public String getDestDirectory() {
		return destDirectory;
	}

	public void setDestDirectory(String destDirectory) {
		this.destDirectory = destDirectory;
	}

}
