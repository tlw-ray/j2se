package com.tlw.eg.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月24日
 */
public class Digest {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
		String text = "admin123";											//密码
		byte[] textBytes=text.getBytes();									//密文的字节码
		System.out.println("将要被加密的内容:\t'"+new String(textBytes)+"'");
		ByteUtil.showBytes(textBytes);
		
		 MessageDigest digestedMD5 = MessageDigest.getInstance("MD5");
		 byte[] digestedMD5Bytes=digestedMD5.digest(textBytes);
		 ByteUtil.showBytes(digestedMD5Bytes);
		 
		 MessageDigest mdInst = MessageDigest.getInstance("SHA1");
		 byte[] digestSHA1Bytes=mdInst.digest(textBytes);
		ByteUtil.showBytes(digestSHA1Bytes);
	}
}
