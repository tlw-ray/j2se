package com.tlw.eg.string;

import java.io.UnsupportedEncodingException;
import java.util.Formatter;

//分析王字不同编码的二进制

public class A01FormatBytes {

	public static void main(String[] args) throws UnsupportedEncodingException {
		//输出王字在不同编码的二进制内容
		showStringHexInfo("王", "GBK");
		showStringHexInfo("王", "UTF-8");
		showStringHexInfo("王", "Unicode");
		
		//输出王字在不同编码下，编码号加一的下一个字的编码
		showStringHexInfo("亡", "GBK");
		showStringHexInfo("玌", "UTF-8");
		showStringHexInfo("玌", "Unicode");
		
		//输出一段话的编码
		String text = "谈话记录";
		showStringHexInfo(text, "GBK");
		showStringHexInfo(text, "UTF-8");
		showStringHexInfo(text, "Unicode");
	}
	
	public static void showStringHexInfo(String text, String encode) throws UnsupportedEncodingException{
		byte[] bytes = text.getBytes(encode);
		String hexString = getByteString(bytes);
		System.out.println("内容: " + text + "\t 编码: " + encode + "\t 长度: " + bytes.length + "\t 二进制: " + hexString);
	}
	
	public static String getByteString(byte[] bytes){
		Formatter formatter = new Formatter();
		for (byte b : bytes) {
		    formatter.format("%02X ", b);
		}
		String hex = formatter.toString();
		formatter.close();
		return hex;
	}

}
