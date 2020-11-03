package com.tlw.eg.string;

import java.io.UnsupportedEncodingException;

public class A02NextChar {

	public static void main(String[] args) throws UnsupportedEncodingException {
		byte[] gbk = new byte[]{(byte)0xCD, (byte)0xF6};
		byte[] utf8 = new byte[]{(byte)0xE7, (byte)0x8E, (byte)0x8C};
		byte[] unicode = new byte[]{(byte)0xFE, (byte)0xFF, (byte)0x73, (byte)0x8C};
		
		System.out.println(new String(gbk, "GBK"));
		System.out.println(new String(utf8, "UTF8"));
		System.out.println(new String(unicode, "Unicode"));
	}

}
