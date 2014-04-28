package com.amateur.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Component
public class ImageUtil {

	private static final Logger logger = Logger.getLogger(ImageUtil.class);

	public void createThumbnail(InputStream src, String dist, float width,
			float height) {

		logger.debug("ImageUtil createThumbnail: beginning.");
		try {
			BufferedImage image = ImageIO.read(src);
			// get the ratio of zoom
			double ratio = 1.0;
			if (image.getHeight() > height || image.getWidth() > width) {
				if (image.getHeight() > image.getWidth()) {
					ratio = height / image.getHeight();
				} else {
					ratio = width / image.getWidth();
				}
			}
			int newWidth = (int) (image.getWidth() * ratio);
			int newHeight = (int) (image.getHeight() * ratio);
			BufferedImage bfImage = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);
			bfImage.getGraphics().drawImage(
					image.getScaledInstance(newWidth, newHeight,
							Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream os = new FileOutputStream(dist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(os);
			encoder.encode(bfImage);
			os.close();
		} catch (Exception e) {
			logger.error("createThumbnail: error happened " + e);
		}
	}
}
