package com.tlw.text;

import java.io.UnsupportedEncodingException;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-12
Description:字符串转换为二进制格式，同ultraEdit
 ********************************/
public class Str2Byte {
	public static void main(String[] args) {
		String str="xml   你好   123!！";
		System.out.println(str);
		str2byte(str,"GBK");
		str2byte(str,"UTF-8");
		str2byte(str,"UTF-16BE");
		str2byte(str,"UTF-16LE");
	}
	private static byte[] str2byte(String str,String charsetName){
		try {
			byte[] bytes=str.getBytes(charsetName);
			//输出到控制台:
			System.out.print(charsetName);
			for(int i=0;i<10-charsetName.length();i++){
				System.out.print(" ");
			}
			System.out.print(":");
			
			showBytes(bytes);
			return bytes;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void showBytes(byte[] bytes){
		for(int k=0;k<bytes.length;k++){
			byte b=bytes[k];
			int i=b;
			String output=Integer.toHexString(i);
			//处理:为负数的情况即单字节大于128的情况下下会以补码的形式即ffffff开头输出
			if(output.length()>2)output=output.substring(6);
			//处理:不足10的需要补0
			if(0<=i && i<10)output="0"+output;
			System.out.print(output.toUpperCase()+" ");
		}
		System.out.println();
	}
}
