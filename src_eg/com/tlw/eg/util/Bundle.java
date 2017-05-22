package com.tlw.eg.util;

import java.util.ResourceBundle;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-6
@version:2009-5-6
Description:
 */
public class Bundle{
	public static void main(String[] args) {
		//Bundle b=new Bundle();
		ResourceBundle rb=ResourceBundle.getBundle(Bundle.class.getName());
		String str=rb.getString("s3");
		System.out.println(str);
	}
}