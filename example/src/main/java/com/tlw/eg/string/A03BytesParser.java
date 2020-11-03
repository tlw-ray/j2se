package com.tlw.eg.string;

import java.io.UnsupportedEncodingException;

public class A03BytesParser {

	public static void main(String[] args) throws UnsupportedEncodingException {
		int[] bytes = new int[]{
				0x3D, 0xE4, 0xF0, 0xD2, 
				0xF0, 0x23, 0xC0, 0xF0, 
				0xAE, 0xF1
		};
		
		System.out.println(parse(bytes, "gbk"));
		System.out.println(parse(bytes, "utf-8"));
		System.out.println(parse(bytes, "Unicode"));
		
		bytes = new int[]{0xCB, 0xAB};
		System.out.println(parse(bytes, "gbk"));
	}
	
	public static String parse(int[] bytes, String encoding) throws UnsupportedEncodingException{
		byte[] byts = new byte[bytes.length];
		for(int i=0;i<bytes.length;i++){
			byts[i] = (byte)bytes[i];
		}
		return new String(byts, encoding);
	}

}
