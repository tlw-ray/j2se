package com.tlw.tools.encode.ascii_native;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
@since 2010-3-22
@version 2010-3-22
@author 唐力伟 (tlw_ray@163.com)
 */
public class URLEncode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String source="你好!";
		String encode=URLEncoder.encode(source, "utf-8");
		System.out.println(encode);
		String decode=URLDecoder.decode(encode, "utf-8");
		System.out.println(decode);
		
		String str="\228\189\160\229\165\189\33";
		System.out.println(str);
		
	}
}