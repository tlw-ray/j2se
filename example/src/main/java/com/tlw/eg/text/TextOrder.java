package com.tlw.eg.text;

import java.text.Collator;
import java.util.Locale;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-24
Description:
 ********************************/
public class TextOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] pvNames=new String[]{"ccc","aac","aab","你好","你好再见","你好么","我"};
		Collator cmp = java.text.Collator.getInstance(Locale.getDefault());
		System.out.println(Locale.getDefault());
		java.util.Arrays.sort(pvNames, cmp);  
		for(int i=0;i<pvNames.length;i++){
			System.out.println(pvNames[i]);
		}
	}

}
