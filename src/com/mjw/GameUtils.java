package com.mjw;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtils {
	private GameUtils() {}
	public static BufferedImage getPic(String path) {
		BufferedImage bi = null;
		URL url;
		try {
//			url = new URL(path);
			url =GameUtils.class.getClassLoader().getResource(path);
			bi=ImageIO.read(url);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bi;
	}
}
