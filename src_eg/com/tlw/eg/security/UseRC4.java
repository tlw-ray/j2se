package com.tlw.eg.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
@since 2010-5-14
@version 2010-5-14
@author 唐力伟 (tlw_ray@163.com)
仅在JDK1.6下适用
 */
public class UseRC4 {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		String Algorithm="RC4";	
		String encode="UTF-8";
		String password="openPlant";
		
		SecretKeySpec key=new SecretKeySpec(password.getBytes(),Algorithm);	
		System.out.println(key.getAlgorithm());
		System.out.println(key.getFormat());
		System.out.println(new String(key.getEncoded()));
		
		Cipher cipher=Cipher.getInstance(Algorithm);
		String psw="openPlant";
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] cryptoByte=cipher.doFinal(psw.getBytes(encode));
		String cryptoString=new String(cryptoByte,encode);
		System.out.println("加密:"+cryptoString);
		
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] pswByte=cipher.doFinal(cryptoByte);
		String pswString=new String(pswByte,encode);
		System.out.println("解密:"+pswString);
	}
}
