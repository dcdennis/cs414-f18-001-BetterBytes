package edu.colostate.cs.cs414.betterbytes.p4.server.utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Static Tools class. Used for added functionality.
 */
public class Tools {

	/**
	 * Gets a local image from the file system.
	 * @param iFile Name of the image file
	 * @return image file
	 */
	public static BufferedImage getLocalImg(String iFile) {
		try {
			File pathToFile = new File(iFile);
			if (!pathToFile.exists()) {
				String absPath = System.getProperty("user.dir") + "/src/";
				pathToFile = new File(absPath+iFile);
				if(pathToFile.exists()) {
					return ImageIO.read(pathToFile);
				}
				Tools.log("File doesn't Exist: " + pathToFile.getAbsolutePath());
				return null;
			}
			BufferedImage image = ImageIO.read(pathToFile);
			return image;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return null;
	}

	/**
	 * Sets the Tools object to sleep for a random duration between one and two
	 * @param one Lower bound on sleep
	 * @param two Upper bound on sleep
	 */
	public static void sleep(int one, int two) {
		Tools.sleep(Tools.random(one, two));
	}

	/**
	 * Return a random int between min and max
	 * @param min Min value for random number
	 * @param max Max value for random number
	 * @return randomly generated int
	 */
	public static int random(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt(max - min + 1) + min;
		return randomNum;
	}

	/**
	 * Set Thread to sleep for an amount of time
	 * @param amt Amount of time to sleep
	 */
	public static void sleep(int amt) {
		try {
			Thread.sleep(amt);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Draws text at position (x,y) on Graphic g with a given color and shade
	 * @param text Text to draw
	 * @param x X position
	 * @param y Y position
	 * @param c Color of the text
	 * @param shade Color of the shading
	 * @param g Graphic object
	 */
	public static void drawSharpText(String text, int x, int y, Color c, Color shade, Graphics g) {
		g.setColor(shade);
		g.drawString(text, x + 1, y + 1);
		g.setColor(c);
		g.drawString(text, x, y);
	}

	/**
	 * Print off Object o. If o is a Double object, it is printed in decimal format. Otherwise, it strings with toString()
	 * @param o Object to print
	 */
	public static void log(Object o) {
		if (o instanceof Double) {
			Double it = (Double) o;
			DecimalFormat df2 = new DecimalFormat("#.##");
			System.out.println(df2.format(it)); 
		} else {
			System.out.println(o.toString());
		}
	}

	/**
	 * Gets the url source of a url as string
	 * @param url url as a string
	 * @return url source
	 */
	public static String getUrlSource(String url) {
		try {
			URL yahoo = new URL(url);
			URLConnection yc = yahoo.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(), "UTF-8"));
			String inputLine;
			StringBuilder a = new StringBuilder();
			while ((inputLine = in.readLine()) != null)
				a.append(inputLine);
			in.close();
			return a.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Gets the file data of a file as string
	 * @param file File to read
	 * @return file text
	 */
	public static String getFileData(File file) {
		String it = "";
		if (file != null && file.exists()) {
			BufferedReader br = null;
			FileReader fr = null;
			try {
				fr = new FileReader(file);
				br = new BufferedReader(fr);
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					it += sCurrentLine;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return it;
	}

	/**
	 * Return a random double between min and max
	 * @param min Min value for random number
	 * @param max Max value for random number
	 * @return randomly generated double
	 */
	public static double random(double min, double max) {
		Random r = new Random();
		double randomValue = min + (max - min) * r.nextDouble();
		return randomValue;
	}

}
