package com.tlw.eg.encode;

import java.io.UnsupportedEncodingException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月7日
 */
public class UTF8_GBK_Bytes {
	
	//对于'测'字UTF-8和GBK的编码如下：
	//GBK: 0110 1101 0100 1011
	//模板:1110 ???? 10?? ???? 10?? ????
	//UTF8:1110 0110 1011 0101 1000 1011

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str="测试";						//UTF-8

		byte[] bytesGBK=str.getBytes("gbk");
		System.out.println("'测试'的GBK编码（4字节）：");
		showBytes(bytesGBK);
		
		System.out.println("'测试'的UTF8编码（6字节）：");
		byte[] bytes1=str.getBytes("utf-8");
		showBytes(bytes1);
		
		System.out.println("'测试'使用UTF-8解码GBK内容后：");
		String str1=new String(bytesGBK, "UTF-8");
		System.out.println(str1);
		
		for(int i=0;i<str1.length();i++){
			char ch=str1.charAt(i);
			System.out.println(Integer.toBinaryString(ch));
		}
		
		System.out.println("可以看出�'1111111111111101'是转码产生的错误信息。");
	}
	
	public static void showBytes(byte[] bytes){
		for(byte byt:bytes){
			System.out.println(Integer.toBinaryString(byt));
		}
	}

}
