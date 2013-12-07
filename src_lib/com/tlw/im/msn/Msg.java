package com.tlw.im.msn;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Set;

/**
@since 2010-3-22
@version 2010-3-22
@author 唐力伟 (tlw_ray@163.com)
 */
public class Msg {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str="小";//345/201/217
		//E5=229 B0=176 8F=143
		Set<String> charsetNames=Charset.availableCharsets().keySet();
		for(String charsetName:charsetNames){
			try {
				byte[]	bytes = str.getBytes(charsetName);
				if(bytes.length==3 && (bytes[0]&0xFF)==0xE5 && (bytes[1]&0xFF)==0xB0 && (bytes[2]&0xFF)==0x8F)System.out.println(charsetName);
			} catch (Exception e) {
			}
		}
		byte[] bs=new byte[]{(byte)0xE5,(byte)0x93,(byte)0xA6};
		System.out.println(new String(bs,"UTF-8"));
	}
}