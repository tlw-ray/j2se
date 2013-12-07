package com.tlw.encrypt;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-28
@version:2009-3-28
Description:
 */
public class UtilRc4 {
	public static void showBytes(byte[] bytes){
		for(int i=0;i<bytes.length;i++){
			
			String hex=toHex(bytes[i]);
			//
			System.out.print(hex+ " ");
		}
		System.out.println();
	}
	static final String hex="0123456789ABCDEF";
	public static String toHex(byte b){
		return (""+hex.charAt(0xf&b>>4)+hex.charAt(b&0xf));
	}
	public static String toHex0(byte b){
		return Integer.toHexString(b>=0?b:256+b).toUpperCase();
	}
	public static String toHex1(byte b){
		String hex=Integer.toHexString(b & 0XFF); 
		if(hex.length()==1)return "0"+hex;
		return hex;
	}
	public static byte toByte(short unsignedHex){
		
		return 0;
	}
}
