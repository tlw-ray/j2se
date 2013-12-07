package com.tlw.eg.nio.mbb;
//数组如同对象也是以引用的形式存在的
public class ByteArrayReference {
	public static void main(String[] args) {
		byte[] bytes=new byte[]{1,2,3,4,5};
		showBytes(bytes);
		change(bytes);
		showBytes(bytes);
	}
	public static void showBytes(byte[] bytes){
		for(byte b:bytes){
			System.out.print(b+",");
		}
		System.out.println();
	}
	public static void change(byte[] bytes){
		bytes[2]=23;
	}
}
