package com.tlw.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-9-4
@version:2009-9-4
Description:
 */
public class JLabelUpDown extends JLabel {
	private static final long serialVersionUID = 8198812705077941806L;
	public static void main(String[] args){
		JLabelUpDown label=new JLabelUpDown();
		Shower.show(label);
	}
	ImageIcon iconUp;
	ImageIcon iconDown;
	int iconSize=8;
	int state=0;
	public static final int STATE_NONE=0;
	public static final int STATE_UP=1;
	public static final int STATE_DOWN=2;
	public JLabelUpDown(){
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				switch(getState()){
				case 0:setState(STATE_UP);break;
				case 1:setState(STATE_DOWN);break;
				default:setState(STATE_NONE);break;
				}
			}
		});
		BufferedImage imgUp=new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_4BYTE_ABGR);
		BufferedImage imgDown=new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g=imgUp.getGraphics();
		drawUpDown(0,0,iconSize,Color.black,true,g);
		g=imgDown.getGraphics();
		drawUpDown(0,0,iconSize,Color.black,false,g);
		iconUp=new ImageIcon(imgUp);
		iconDown=new ImageIcon(imgDown);
	}
	boolean isUp=true;
	public void paint(Graphics g){
		super.paint(g);
	}
	public void setState(int s){
		state=s;
		switch(state){
		case 1:setIcon(iconUp);break;
		case 2:setIcon(iconDown);break;
		default :setIcon(null);break;
		}
	}
	public int getState(){
		return state;
	}
	private void drawUpDown(int x0,int y0,int size,Color color ,boolean isUp,Graphics g){
		g.setColor(color);
		for(int i=1;i<size;i++){
			int len,space;
			if(isUp)
				len=(i/2+1)*2;
			else
				len=size-((i-1)/2)*2;
			space=(size-len)/2;
			g.drawLine(x0+space,y0+i-1,x0+space+len-1,y0+i-1);
		}
	}
}
