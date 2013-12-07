package com.tlw.eg.swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-13
@version:2009-6-13
Description:
 */
public class CustomDrawBorder extends JPanel {
	private static final long serialVersionUID = -1977373975492498035L;
	public static void main(String[] args) {
		CustomDrawBorder pane=new CustomDrawBorder();
		Shower.show(pane, 100, 100);
	}
	public CustomDrawBorder(){}
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(Color.gray);
		g.draw3DRect(10, 10, 20, 20, false);
	}
}
