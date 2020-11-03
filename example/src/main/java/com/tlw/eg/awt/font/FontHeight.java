package com.tlw.eg.awt.font;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.tlw.swing.Shower;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-22
Description:
 ********************************/
public class FontHeight extends JPanel {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		FontHeight fh=new FontHeight();
		Shower.show(fh);	
	}
	public void paint(Graphics g){
		super.paint(g);
		//Ascent是b,k等字母的高度   
		//Descent是p,g等字母的下半部分的高度   
		//Leading是行与行之间的间隔的高度   
		//字体的高度包括三部分：Ascent、Descent、Leading,单位是像素
		String txt="hip";
		FontMetrics fm=g.getFontMetrics();
		
		//about height
		System.out.println("----------About Height:");
		int height=fm.getHeight();
		int ascent=fm.getAscent();
		int descent=fm.getDescent();
		int leading=fm.getLeading();
		System.out.println("Height:"+height);
		System.out.println("Ascent:"+ascent);
		System.out.println("Descent:"+descent);
		System.out.println("Leading: "+leading);
		System.out.println("Height = Ascent + Descent + Leading");
		
		//about width
		System.out.println("----------About Width:");
		int width=fm.stringWidth(txt);
		System.out.println("Width: "+width);
		for(int i=0;i<txt.length();i++){
			char ch=txt.charAt(i);
			System.out.println(ch+"\t"+fm.charWidth(ch));
		}
		
		g.drawString(txt, 100, 100);
		g.drawRect(100, 100-ascent, 30, height);
		//font 的单位是pt,1/72英寸的高度
		Font font=g.getFont();
		int size=font.getSize();
		float sizef=font.getSize2D();
		System.out.println("font size: "+size+" sizeF: "+sizef);
	}
}
