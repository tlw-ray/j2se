package com.tlw.eg.lang;
/**
 * @author liwei.tang@magustek.com
 * @since 2015年2月12日
 * 是否实现接口与能否clone无关
 * clone
 */
public class Cloneable1 {

	public static void main(String[] args) {
		Clone1 c1=new Clone1();
		c1.str="clone1_cloned";
		Clone2 c2=new Clone2();
		c2.str="clone2_cloned";
		System.out.println(c1);
		System.out.println(c1.clone());
		System.out.println(c2);
		System.out.println(c2.clone());
	}
	
	static class Clone1{
		String str="clone1";
		public Object clone(){
			return new Clone1();
		}
		@Override
		public String toString() {
			return "Clone1 [str=" + str + "]";
		}
	}
	
	static class Clone2 implements Cloneable{
		String str="clone2";
		public Object clone(){
			try {
				return super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				return null;
			}
		}
		@Override
		public String toString() {
			return "Clone2 [str=" + str + "]";
		}
	}

}
