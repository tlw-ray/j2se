package com.tlw.tool.unicode;

import java.io.UnsupportedEncodingException;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-12
Description:java默认为UTF-8编码，此程序输出所有可用字符
 ********************************/
public class ShowChars {
	public static void main(String[] args) {
		showUnicodeRegion();
		showUnicodeChars();
		showUnicodeBlocks();
	}
	public static void showUnicodeRegion(){
		System.out.println("----------显示unicode字符集范围----------");
		String hexMin=Integer.toHexString(Character.MIN_CODE_POINT).toUpperCase();
		String hexMax=Integer.toHexString(Character.MAX_CODE_POINT).toUpperCase();
		System.out.println(Character.MIN_CODE_POINT+"[Ox"+hexMin+"]---"+Character.MAX_CODE_POINT+"[0x"+hexMax+"]");
		System.out.println();
	}
	public static void showUnicodeChars(){
		System.out.println("--------------每行1000个显示所有Unicode字符--------------");
		for(int i=Character.MIN_CODE_POINT;i<=Character.MAX_CODE_POINT;i++){
			if(i%1000==0){
				System.out.println();
				System.out.print(i/1000+":");
			}
			System.out.print((char)i+" ");
			String str="asdf";str.replaceAll("''","'");
		}
	}
	public static void showUnicodeBlocks(){
		//以几个代表性字符看type,directionality,unicodeBloc的区别
		char[] chars=new char[]{'你','a','A','1','一'};
		for(char ch:chars){
			String str=ch+"";
			//为了格式整齐便于查看
			if(ch!='你' && ch!='一')str+=" ";
			System.out.println("["+str+"]"+" charType:"+Character.getType(ch)+
					" charDirectionality:"+Character.getDirectionality(ch)+
					" UnicodeBlock:"+Character.UnicodeBlock.of(ch));
		}
		/*All Types
		 COMBINING_SPACING_MARK, CONNECTOR_PUNCTUATION, CONTROL, CURRENCY_SYMBOL, 
		 DASH_PUNCTUATION, DECIMAL_DIGIT_NUMBER, ENCLOSING_MARK, END_PUNCTUATION, 
		 FINAL_QUOTE_PUNCTUATION, FORMAT, INITIAL_QUOTE_PUNCTUATION, LETTER_NUMBER, 
		 LINE_SEPARATOR, LOWERCASE_LETTER, MATH_SYMBOL, MODIFIER_LETTER, MODIFIER_SYMBOL, 
		 NON_SPACING_MARK, OTHER_LETTER, OTHER_NUMBER, OTHER_PUNCTUATION, OTHER_SYMBOL, 
		 PARAGRAPH_SEPARATOR, PRIVATE_USE, SPACE_SEPARATOR, START_PUNCTUATION, SURROGATE, 
		 TITLECASE_LETTER, UNASSIGNED, UPPERCASE_LETTER
		 
		 All Directionality:
		 DIRECTIONALITY_UNDEFINED, DIRECTIONALITY_LEFT_TO_RIGHT, DIRECTIONALITY_RIGHT_TO_LEFT, 
		 DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC, DIRECTIONALITY_EUROPEAN_NUMBER, 
		 DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR, DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR, 
		 DIRECTIONALITY_ARABIC_NUMBER, DIRECTIONALITY_COMMON_NUMBER_SEPARATOR, 
		 DIRECTIONALITY_NONSPACING_MARK, DIRECTIONALITY_BOUNDARY_NEUTRAL, 
		 DIRECTIONALITY_PARAGRAPH_SEPARATOR, DIRECTIONALITY_SEGMENT_SEPARATOR, DIRECTIONALITY_WHITESPACE,
		 DIRECTIONALITY_OTHER_NEUTRALS, DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING, 
		 DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE, DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING, 
		 DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE, DIRECTIONALITY_POP_DIRECTIONAL_FORMAT
		 */
		System.out.println();
		
		String str="你好";
		try {
			String strErr=new String(str.getBytes("UTF-8"),"GBK");
			System.out.println("源:【"+str+"】   错误解码:【"+strErr+"】");
			System.out.println("解析错误解码的每个字符:");
			for(char ch:strErr.toCharArray()){
				System.out.println(Character.getType(ch)+" "+Character.getDirectionality(ch)+" "+Character.UnicodeBlock.of(ch));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		/*结论:通过unicodeBloc可以区分含有汉字的文档，是否包含其他的文字。
		 * 例如如果在某java源码文件中发现除了英文和汉字之外的其他字符，那么说明很可能解码发生了错误。*/
	}
}
