package com.tlw.byt;

import java.io.UnsupportedEncodingException;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-12
Description:
 ********************************/
public class ByteHelper {
	public static void str2byte(String str,String charsetName){
		try {
			byte[] bytes=str.getBytes(charsetName);
			System.out.print(charsetName);
			for(int i=0;i<10-charsetName.length();i++){
				System.out.print(" ");
			}
			System.out.print(":");
			showBytes(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public static void showBytes(byte[] bytes){
		for(int i=0;i<bytes.length;i++){
			String hex=Integer.toHexString(bytes[i]>=0?bytes[i]:256+bytes[i]).toUpperCase();
			//或
			//String hex=toHex(bytes[i]);
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
	// returns a byte array of length 4
	public static byte[] intToDWord(int i) {
		byte[] dword = new byte[4];
		dword[0] = (byte) ((i >> 24) & 0x000000FF);
		dword[1] = (byte) ((i >> 16) & 0x000000FF);
		dword[2] = (byte) ((i >> 8) & 0x000000FF);
		dword[3] = (byte) (i & 0x00FF);
		return dword;
	}
	public static byte unsign2byte(int unsignSmall){
		return intToDWord(unsignSmall)[3];
	}
	public static String getBits(int i){
		String result="";
		int tmp=i;
		while(i>0){
			tmp=i%2;
			result=tmp+result;
			i/=2;
		}
		return result;
	}
	public static void main(String[] args){
		for(int i=0;i<20;i++)
		System.out.println(getBits(i));
	}
 public static byte[] int2Byte(int intValue){
	  byte[] b=new byte[4];
	  for(int i=0;i<4;i++){
	   b[i]=(byte)(intValue>>8*(3-i) & 0xFF);
	   //System.out.print(Integer.toBinaryString(b[i])+" ");
	   System.out.print((b[i]& 0xFF)+" ");
	  }
	  return b;
	 }
	 public static int byte2Int(byte[] b){
	  int intValue=0;
	  for(int i=0;i<b.length;i++){
	   intValue +=(b[i] & 0xFF)<<(8*(3-i));
	   //System.out.print(Integer.toBinaryString(intValue)+" ");
	  }
	  return intValue;
	 }
}
