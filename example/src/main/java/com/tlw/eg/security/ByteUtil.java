package com.tlw.eg.security;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月24日
 */
public class ByteUtil {
	public static void showBytes(byte[] bytes){
		for(int i=0;i<bytes.length;i++){
			String hex=toHex(bytes[i]);
			System.out.print(hex+ " ");
		}
		System.out.println();
	}
	
	static final String hex="0123456789ABCDEF";
	private static String toHex(byte b){
		return (""+hex.charAt(0xf&b>>4)+hex.charAt(b&0xf));
	}
}
