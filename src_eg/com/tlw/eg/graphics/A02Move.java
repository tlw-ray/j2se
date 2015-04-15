package com.tlw.eg.graphics;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年1月25日
 */
public class A02Move {

	public static void main(String[] args) {
		AffineTransform at=new AffineTransform();
		at.translate(10, 10);
		
		Point pointFrom=new Point();
		Point pointTo=new Point();
		at.transform(pointFrom, pointTo);
		System.out.println("pointFrom:"+pointFrom);
		System.out.println("PointTo:"+pointTo);
	}

}
