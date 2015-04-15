package com.tlw.eg.graphics;

import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年3月23日
 */
public class A04 {

	public static void main(String[] args) {
		//arg1: scaleX			theta,sx
		//arg2: shearingX
		//arg3: shearingY
		//arg4: scaleY
		//arg5: translateX
		//arg6: translateY
		AffineTransform at=new AffineTransform(1, 0, 0, 1, 0, 0);
		System.out.println(at);
		
		at.rotate(Math.PI/3);
		System.out.println(at);
		
		at.scale(-1, 2);
		System.out.println(at);
	
		at.translate(100, 200);
		System.out.println(at);
		
//		Point point=new Point(10,10);
//		Point tPoint=new Point();
//		Point2D p2=at.transform(point, tPoint);
//		System.out.println(Math.cos(Math.PI/2));
		
		
//		AffineTransform[[1.0, 0.0, 0.0], [0.0, 1.0, 0.0]]
//		AffineTransform[[1.0, 0.0, 100.0], [0.0, 1.0, 200.0]]
//		AffineTransform[[-1.0, 0.0, 100.0], [0.0, 2.0, 200.0]]
//		AffineTransform[[-0.5, 0.866025403784439, 100.0], [1.732050807568877, 1.0, 200.0]]
		
//		AffineTransform[[1.0, 0.0, 0.0], [0.0, 1.0, 0.0]]
//		AffineTransform[[1.0, 0.0, 100.0], [0.0, 1.0, 200.0]]
//		AffineTransform[[0.5, -0.866025403784439, 100.0], [0.866025403784439, 0.5, 200.0]]
//		AffineTransform[[-0.5, -1.732050807568877, 100.0], [-0.866025403784439, 1.0, 200.0]]
		
//		AffineTransform[[1.0, 0.0, 0.0], [0.0, 1.0, 0.0]]
//		AffineTransform[[0.5, -0.866025403784439, 0.0], [0.866025403784439, 0.5, 0.0]]
//		AffineTransform[[-0.5, -1.732050807568877, 0.0], [-0.866025403784439, 1.0, 0.0]]
//		AffineTransform[[-0.5, -1.732050807568877, -396.41016151377545], [-0.866025403784439, 1.0, 113.3974596215562]]
	}

}
