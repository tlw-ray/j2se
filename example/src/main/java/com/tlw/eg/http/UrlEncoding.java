package com.tlw.eg.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年4月1日
 */
public class UrlEncoding {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String value = URLEncoder.encode("\\w", "utf-8");
		System.out.println(value);
	}

}
