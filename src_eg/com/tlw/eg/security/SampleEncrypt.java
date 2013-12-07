package com.tlw.eg.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-18
@version:2009-5-18
Description:
 */
public class SampleEncrypt {
	public static void main(String args[]) throws  NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		String Algorithm="RC4";												//算法
		String password = "administrator";									//密码
		String text="这里的内容将被加密!abcd1234";							//密文
		byte[] textBytes=text.getBytes();									//密文的字节码
		System.out.println("将要被加密的内容:\t'"+new String(textBytes)+"'");
		showBytes(textBytes);
		
		SecretKeySpec key=new SecretKeySpec(password.getBytes(),Algorithm);	//用MD5摘要算法对密码生成密钥
		Cipher cipher=Cipher.getInstance(Algorithm);						//建立基于RC4算法的加密解密器
		
		cipher.init(Cipher.ENCRYPT_MODE, key);								//以加密模式初始化
		byte[] textEncrypt=cipher.doFinal(textBytes);						//加密
		System.out.println("被加密后的内容:\t\t'"+new String(textEncrypt)+"'");
		showBytes(textEncrypt);
		
		cipher.init(Cipher.DECRYPT_MODE,key);								//以解密模式初始化
		byte[] textDecrypt=cipher.doFinal(textEncrypt);						//解密
		System.out.println("被解密后的内容:\t\t'"+new String(textDecrypt)+"'");
		showBytes(textDecrypt);
	}
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
