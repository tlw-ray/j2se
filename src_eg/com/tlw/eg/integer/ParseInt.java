package com.tlw.eg.integer;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-20
Description:
 ********************************/
public class ParseInt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Integer.parseInt("FF",32));
		System.out.println(Integer.parseInt("ff",Character.MAX_RADIX));
		System.out.println(Character.MAX_RADIX);
	}

}
