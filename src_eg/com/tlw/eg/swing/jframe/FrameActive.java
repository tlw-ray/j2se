package com.tlw.eg.swing.jframe;

import java.awt.Color;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-27
@version:2009-5-27
Description:窗体不再最前时，由于内部触发事件，造成在任务栏闪烁。
 */
public class FrameActive extends JFrame {
	private static final long serialVersionUID = 68957739458665752L;
	public static void main(String[] args) {
		JFrame f = new JFrame() {
			private static final long serialVersionUID = 6476657197871098901L;
			{
				new Thread() {
					public void run() {
						try {
							sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("Fire!");
						BufferedImage img1=new BufferedImage(32,32,BufferedImage.TYPE_3BYTE_BGR);
						img1.getGraphics().setColor(Color.blue);
						img1.getGraphics().fillRect(0, 0, 32, 32);
						BufferedImage img2=new BufferedImage(32,32,BufferedImage.TYPE_3BYTE_BGR);
						img1.getGraphics().setColor(Color.blue);
						img1.getGraphics().fillRect(0, 0, 32, 32);
						toFront();
						for(int i=0;i<10;i++){
							if(i % 2 ==0){
								setTitle("aaaaa");
								setIconImage(img1);
							}else{
								toBack();
								setIconImage(img2);
								setTitle(".....");
							}
							try {
								Thread.sleep(300);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}.start();
			};
		};
		f.setSize(500,500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
