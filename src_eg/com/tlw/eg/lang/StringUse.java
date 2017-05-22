package com.tlw.eg.lang;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-10
Description:String类型一些用法的例子
 ********************************/
public class StringUse {

	public static void main(String[] args) throws Exception {
		encode();
		

	}
	public static void encode() throws Exception{
		System.out.println("------encode()------");
		String a="你好xml";
		String b = new String(a.getBytes("ISO-8859-1"), "UTF-8");
		System.out.println(b);
		
	}
}
