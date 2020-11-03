/*
 * Created on 2006-7-27
 */
package com.tlw.ui.others;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ComponentPaintHelper{
	public static void main(String[] args){
		JFrame frame=new JFrame();
		JPanel pane=new JPanel(){
			private static final long serialVersionUID = 1L;
			{
				setBackground(Color.green);
			}
			public void paint(Graphics g){
				super.paint(g);
				PaintGrid(this,g,50,50);
				g.drawString("Hello", 100, 100);
			}
		};
		frame.add(pane,"Center");
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
    public static final Color defaultGridColor=new Color(200, 200, 200);
    public static final BasicStroke defaultGridStroke=
        new BasicStroke(    1, BasicStroke.CAP_BUTT,
            BasicStroke.JOIN_BEVEL,0, new float[]{1, 2}, 0);
    //Add this Mothed To you Component's inherited paint(Graphics g) function
    //bescuse Has No Paint Listener;
    public static void PaintGrid(Component tobeGrid,Graphics g,double hDistance,double vDistance){
        if(tobeGrid!=null){
            Rectangle canvasBounds=tobeGrid.getBounds();
            Graphics2D g2=(Graphics2D)g.create();
            g2.setColor(defaultGridColor);
            //g2.setXORMode(Color.white);
            g2.setXORMode(tobeGrid.getBackground());
            g2.setStroke(defaultGridStroke);
            
            for(int i=0; i<canvasBounds.width; i+=hDistance){
                g2.drawLine((int)i, 0,(int) i, canvasBounds.height);
            }
            for(int i=0; i<canvasBounds.height; i+=vDistance){
                g2.drawLine(0, i, canvasBounds.width, i);
            }
            g2.dispose();
        }
    }
}
