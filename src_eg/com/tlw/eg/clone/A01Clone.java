package com.tlw.eg.clone;


/**
 * @author liwei.tang@magustek.com
 * @since 2015年9月28日
 * super.clone()实现了浅拷贝，对于实例内部的引用对象不会拷贝
 */
public class A01Clone{

	public static void main(String[] args) throws CloneNotSupportedException {
		Xxx x=new Xxx();
		System.out.println(x);
		System.out.println(x.clone());
	}
	
	public static class Xxx implements Cloneable{
		int id=0;
		String name="x";
		Yyy y=new Yyy();



		@Override
		public String toString() {
			return super.toString()+" [id=" + id + ", name=" + name + ", y=" + y + "]";
		}



		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
	
	public static class Yyy implements Cloneable{
		String name="y";

		@Override
		public String toString() {
			return super.toString()+" [name=" + name + "]";
		}
		
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}

}
