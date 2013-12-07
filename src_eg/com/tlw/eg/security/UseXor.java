package com.tlw.eg.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
@since 2010-5-14
@version 2010-5-14
@author 唐力伟 (tlw_ray@163.com)
 */
public class UseXor {

	/**
	 * @param args
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws IllegalStateException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalStateException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException {
		String encode="UTF-8";
		String password="openPlant";
		byte key=0x22;
		byte[] bytesPsw=password.getBytes(encode);
		
		byte[] encrypt=encryptXor(key,bytesPsw);
		System.out.println(new String(encrypt,encode));
		
		byte[] decrypt=encryptXor(key,encrypt);
		System.out.println(new String(decrypt,encode));
		
	}
	private static byte[] encryptXor(byte key,byte[] content){
		byte[] result=new byte[content.length];
		for(int i=0;i<content.length;i++)result[i]=(byte)(content[i]^key);
		return result;
	}

}
