package com.tlw.eg.lang;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年12月3日
 * toString()方法中输出的是该对象的hashCode
 */
public class ToStringHash {

	public static class Person{
		
	}
	
	public static class Person1{
		@Override
		public int hashCode() {
			return 100;
		}
	}
	
	public static void main(String[] args) {
		Person p=new Person();
		System.out.println(p);
		Person1 p1=new Person1();
		System.out.println(p1);
	}

}
