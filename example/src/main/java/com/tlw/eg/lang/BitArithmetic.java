package com.tlw.eg.lang;
/**
@since 2010-1-21
@version 2010-1-21
@author 唐力伟 (tlw_ray@163.com)
 */
public class BitArithmetic {
	public static void main(String[] args) {
		int max=12;
		for(int i=0;i<max;i++){
			System.out.println(max+"\t>>\t"+i+"\t=\t"+(max>>i));
			System.out.println(max+"\t&\t"+i+"\t=\t"+(max&i));
		}
	}
}
