package com.tlw.eg.swing.trans_frame;

import java.awt.AWTException;
import java.awt.Graphics;
import java.awt.Robot;
import java.awt.image.BufferedImage;

import javax.swing.JApplet;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-1-20
@version:2009-1-20
Description:
 */
public class TransparentApplet extends JApplet {
	private static final long serialVersionUID = -6490348693663679854L;

	public static void main(String[] args) {
		TransparentApplet ta=new TransparentApplet();
		ta.start();
	}
	public TransparentApplet(){
	}
	public void paint(Graphics g){
		try {
			BufferedImage screenshot = (new Robot()).createScreenCapture(getBounds());
			g.drawImage(screenshot, 0, 0, null);
			g.drawString("hissdfsdf", 100, 100);
		} catch (AWTException e) {
			e.printStackTrace();
		} 
	}
}
