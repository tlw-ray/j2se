package com.tlw.eg.graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年2月5日
 */
public class A03Rotate {

	public static void main(String[] args) {
		
		JPanel panel=new JPanel(){
			private static final long serialVersionUID = 5529168098289667466L;

			public void paint(Graphics g){
				super.paint(g);
				Graphics2D g2=(Graphics2D)g;
//				AffineTransform at=new AffineTransform();
//				at.rotate(45*Math.PI/180, 50, 50);
//				Point rightBottom=new Point(60,60);
//				Point newRightBottom=new Point();
//				at.transform(rightBottom, newRightBottom);
				
//				g.drawRect(50, 50, 50, 150);
//				g2.rotate(90*Math.PI/180, 75, 125);
//				g.drawRect(50, 50, 50, 150);
//				System.out.println(g2.getTransform());
				
				AffineTransform at=new AffineTransform(0,-0.000001,1,0,0,0);
				
				Point p0=new Point(200, 200);
				Point p1=new Point(300, 400);
				g.drawRect(p0.x, p0.y, p1.x-p0.x, p1.y-p0.y);
				
				g2.transform(at);
				g.drawRect(p0.x, p0.y, p1.x-p0.x, p1.y-p0.y);
				
				
//				g.drawRect(50, 50, 50, 50);
//				g2.rotate(-45*Math.PI/180, 75, 75);
				///...
				
//				g2.drawLine(200, 200, 300, 300);
//				g2.rotate(45*Math.PI/180, 250, 250);
//				g2.drawLine(200, 200, 300, 300);
//				g2.rotate(-45*Math.PI/180, 250, 250);
			}
		};
		UtilUi.show(panel, 800, 600, "");
	}

}
