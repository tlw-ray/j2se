package com.tlw.eg.lang;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-20
Description:
 ********************************/
public class CharUse {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		charPoint();
	}
	static void charPoint(){
		char[] chs=new char[]{'a','c','f','g',};
		int cp=Character.codePointAt(chs, 2);
		System.out.println((int)'f');
		System.out.println(cp);
	}
}
