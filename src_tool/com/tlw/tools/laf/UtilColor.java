package com.tlw.tools.laf;

import java.awt.Color;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-11-3
@version:2009-11-3
Description:
 */
public class UtilColor {
	public static String COLOR_PREFIX="#";
	public static String encode(Color color){
		int rgb=color.getRGB();
		String rgbStr=Integer.toHexString(rgb);
		return COLOR_PREFIX+rgbStr.substring(2).toUpperCase();
	}
	public static Color decode(String c){
		if(c.startsWith(COLOR_PREFIX))c=c.substring(1);
	    if (c.length() > 6)throw new NumberFormatException("c is not a 24 bit representation of the color, string too long"); 
	    Color color = new Color( Integer.parseInt( c , 16 ) );
	    return color;

	}
}
