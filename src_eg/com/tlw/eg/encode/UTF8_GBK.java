package com.tlw.eg.encode;

import java.io.UnsupportedEncodingException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月7日
 * UTF-8以3个byte描述一个中文字符
 * 中文字符的范围在（GBK的）：\u4E00-\u9FA5 和 \uF900-\uFA2D
 * GBK以2个byte描述一个中文字符
 * GBK的2个Byte映射到UTF-8的3个Byte需要将内容填入模板：1110 ???? 10?? ???? 10?? ????
 * 模板的长度是3个Byte，对应UTF-8的单个中文字符
 * 模板中问号的数量是16个，对应GBK中单个字符的2个byte。
 * 模板在二进制是以逆序存放的
 */
public class UTF8_GBK {

	public static void main(String[] args) throws UnsupportedEncodingException {
		char ch='测';
		System.out.println("'测' GBK(Binnary): "+Integer.toBinaryString(ch));
		
		System.out.println("'测' GBK(Value):"+Integer.toHexString(ch));
		
		//GBK: 0110 1101 0100 1011
		//模板:1110 ???? 10?? ???? 10?? ????
		//UTF8:1110 0110 1011 0101 1000 1011
		byte[] bytes=new byte[3];
		bytes[0]=(byte)(Integer.valueOf("11100110", 2).intValue());
		bytes[1]=(byte)(Integer.valueOf("10110101", 2).intValue());
		bytes[2]=(byte)(Integer.valueOf("10001011", 2).intValue());
		String c=new String(bytes, "UTF-8");
		System.out.println(c);
	}

}
