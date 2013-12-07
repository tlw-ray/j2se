package com.tlw.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-9
@version:2009-6-9
Description:自动适应尺寸的面板
报警，数据一览中用到

面板必须为FlowLayout
面板宽度取决于布局其给定宽度(一般为其外部容器的宽度)
面板高度取决于所有可见的内部组件，通过FlowLayout布局后，加上组件间空隙的尺寸。
 */
public class JPanelAutoSize extends JPanel {
	private static final long serialVersionUID = -3408605430525638944L;
	public static void main(String[] args) {
		//Example
		JPanelAutoSize pane=new JPanelAutoSize();
		for(int i=0;i<10;i++){
			JButton btn=new JButton(i+"");
			pane.add(btn);
		}
		JFrame f=new JFrame();
		f.getContentPane().add(pane,BorderLayout.NORTH);
		f.getContentPane().add(new JScrollPane(new JTree()),BorderLayout.CENTER);
		f.setSize(400,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public JPanelAutoSize(){setLayoutAlign(FlowLayout.LEFT);}
	public Dimension getSize(){
		Dimension size=super.getSize();
		FlowLayout flowLayout=(FlowLayout)getLayout();
		doLayout();
		
		int maxHeight=0;
		for(int i=0;i<getComponentCount();i++){
			Component comp=getComponent(i);
			int positionY=comp.getY();
			int compHeight=comp.getHeight();
			int currentMaxHeight=positionY+compHeight;
			maxHeight=maxHeight>currentMaxHeight?maxHeight:currentMaxHeight;
		}
		int realyHeight=maxHeight+flowLayout.getVgap();
		if(getBorder()!=null){
			realyHeight+=getBorder().getBorderInsets(this).bottom;
		}
		size.height=realyHeight;
		return size;
	}
	public Dimension getPreferredSize(){
		return getSize();
	}
	public Dimension getMinimumSize(){
		return getPreferredSize();
	}
	public Dimension getMaximumSize(){
		return getPreferredSize();
	}
	public void setLayout(LayoutManager mgr){
		if(mgr instanceof FlowLayout)
			super.setLayout(mgr);
		else
			throw new RuntimeException("JPanelAutoFixSize can not setLayout(),it must be java.awt.FlowLayout.");
	}
	public void setLayoutAlign(int align){
		FlowLayout layout=(FlowLayout)getLayout();
		layout.setAlignment(align);
	}
	public void setHgap(int hgap){
		FlowLayout layout=(FlowLayout)getLayout();
		layout.setHgap(hgap);
	}
	public void setVgap(int vgap){
		FlowLayout layout=(FlowLayout)getLayout();
		layout.setVgap(vgap);
	}
}