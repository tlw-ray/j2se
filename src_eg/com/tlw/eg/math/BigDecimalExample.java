package com.tlw.eg.math;

import java.math.BigDecimal;

/**
@since 2010-8-17
@version 2010-8-17
@author 唐力伟 (tlw_ray@163.com)
加减乘除四则运算，除法要设定小数点位数和舍入方式。否则抛出异常。
 */
public class BigDecimalExample {
	public static void main(String[] args) {
		BigDecimal A=new BigDecimal(2);
		BigDecimal B=new BigDecimal(3);
		System.out.println(A.add(B));
		System.out.println(A.add(B));
		System.out.println(A.subtract(B));
		System.out.println(A.multiply(B));
		System.out.println(A.setScale(8).divide(B,BigDecimal.ROUND_HALF_EVEN));
	}
}