package com.tlw.eg.image;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.tlw.swing.Shower;



/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-11-29
@version:2009-11-29
Description:
 */
public class JPanelGIF extends JPanel {
	private static final long serialVersionUID = -1280251317109279585L;
	public static void main(String[] args) {
		JPanelGIF pane=new JPanelGIF();
		Shower.show(pane, 400, 300);
	}
	ImageIcon img = new ImageIcon("sample.gif");
	public void paint(Graphics g){
		g.drawImage(img.getImage(), 20, 20, this);
	}
}
