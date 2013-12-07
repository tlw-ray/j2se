package com.tlw.tool.img;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.VolatileImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
@since 2011-6-21
@author 唐力伟 (liwei.tang@magustek.com)
 */
public class ImageConverter {
	static final String fromType="jpg";
	static final String toType="png";
	static final int scale=8;
	static final String fromDir="E:\\mySelf\\照片\\101MSDCF";
	public static void main(String[] args) throws IOException {
		File fromFile=new File(fromDir);
		File[] files=fromFile.listFiles(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(fromType);
			}
		});
		for(File file:files){
			BufferedImage bi=ImageIO.read(file);
			int newWidth=bi.getWidth()/scale;
			int newHeight=bi.getHeight()/scale;
			Image imageScaled=bi.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
			BufferedImage imageOutput=toBufferedImage(imageScaled,BufferedImage.TYPE_3BYTE_BGR);
			String outputFilePath=file.getPath();
			outputFilePath=outputFilePath.substring(0,outputFilePath.length()-4);
			outputFilePath=outputFilePath+"."+toType;
			System.out.println(outputFilePath);
			File output=new File(outputFilePath);
			ImageIO.write((RenderedImage) imageOutput, toType, output);
		}
		System.out.println("finish...");
	}
	public static BufferedImage toBufferedImage(final Image image, final int type) {  
		if (image instanceof BufferedImage)
			return (BufferedImage) image;
		if (image instanceof VolatileImage)
			return ((VolatileImage) image).getSnapshot();
		loadImage(image);
		final BufferedImage buffImg = new BufferedImage(image.getWidth(null),
				image.getHeight(null), type);
		final Graphics2D g2 = buffImg.createGraphics();
		g2.drawImage(image, null, null);
		g2.dispose();
		return buffImg;  
	}
	private static void loadImage(final Image image) {
		class StatusObserver implements ImageObserver {
			boolean imageLoaded = false;

			public boolean imageUpdate(final Image img, final int infoflags,
					final int x, final int y, final int width, final int height) {
				if (infoflags == ALLBITS) {
					synchronized (this) {
						imageLoaded = true;
						notify();
					}
					return true;
				}
				return false;
			}
		}
		final StatusObserver imageStatus = new StatusObserver();
		synchronized (imageStatus) {
			if (image.getWidth(imageStatus) == -1
					|| image.getHeight(imageStatus) == -1) {
				while (!imageStatus.imageLoaded) {
					try {
						imageStatus.wait();
					} catch (InterruptedException ex) {
					}
				}
			}
		}
	}  
}
