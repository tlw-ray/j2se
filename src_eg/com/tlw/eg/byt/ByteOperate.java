package com.tlw.eg.byt;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-14
Description:
 ********************************/
public class ByteOperate {
	public static void main(String[] args) {
		byte b0=0;
		byte b1=1;
		byte b2=2;
		byte b4=4;
		byte b8=8;
		System.out.println(b0&b0);
		System.out.println(b0&b1);
		System.out.println(b0&b2);
		System.out.println(b0&b4);
		System.out.println(b0&b8);
		
		System.out.println(b0^b0);
		System.out.println(b1^b1);
		System.out.println(b2^b2);
		System.out.println(b4^b4);
		System.out.println(b8^b8);
		
		System.out.println();
	}

}
