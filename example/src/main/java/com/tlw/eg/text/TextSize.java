package com.tlw.eg.text;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.tlw.swing.Shower;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-24
Description:
 ********************************/
public class TextSize extends JPanel {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		TextSize ts=new TextSize();
		Shower.show(ts);
	}
	public void paint(Graphics g){
		super.paint(g);
		String str="abcde,f+g=h.ij12;3kl-m/n你好，！";
		int posL=50;
		int posB=50;
		int strLength=str.length();
		g.drawString(str, 50, 50);
		int strWidth=g.getFontMetrics().charsWidth(str.toCharArray(), 0, strLength);
		int strHeight=g.getFontMetrics().getHeight();
		int strDescent=g.getFontMetrics().getDescent();
		int strAscent=g.getFontMetrics().getAscent();
		int strLeading=g.getFontMetrics().getLeading();
		System.out.println("strWidth:"+strWidth);
		System.out.println("strHeight:"+strHeight);
		System.out.println("strDescent:"+strDescent);
		System.out.println("strAscent:"+strAscent);
		System.out.println("strLeading:"+strLeading);
		g.drawRect(posL, posB, 1, 1);
		g.drawRect(posL, posB-strHeight+strDescent, strWidth, strHeight);
		int drawLeft=posL;
		for(int i=0;i<strLength;i++){
			int charWidth=g.getFontMetrics().charWidth(str.charAt(i));
			g.drawRect(drawLeft, posB-strHeight+strDescent, charWidth, strHeight);
			drawLeft+=charWidth;
		}
	}
}
