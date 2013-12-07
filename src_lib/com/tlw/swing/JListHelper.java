package com.tlw.swing;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JList;
import javax.swing.JViewport;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-11-21
Description:一些JList可能用到的工具类
 ********************************/
public class JListHelper {
	public static void main(String[] args) {

	}
	public static void scrollToVisible(JList list,int idx){
		if(list.getParent() instanceof JViewport){
			JViewport vp=(JViewport)list.getParent();
			Rectangle rect = list.getCellBounds(idx, idx);
	        Point pt = vp.getViewPosition();
	        rect.setLocation(rect.x - pt.x, rect.y - pt.y);
	        vp.scrollRectToVisible(rect);
		}
	}
}
