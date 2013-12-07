package com.tlw.eg.swing;

/**
 @Author: 唐力伟 (tlw_ray@163.com)
 @since:2009-6-9
 @version:2009-6-9
 Description:
 */
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-9
@version:2009-6-9
Description:纵向流布局
内部组件宽度取决于容器宽度
内部组件高度取决于其自身的getPreferredSize().height属性
自顶向下排布
 */
public class VFlowLayout implements LayoutManager, Serializable {
	private static final long serialVersionUID = 7161503014129653282L;
	public static void main(String[] args){
		JFrame f=new JFrame();
		VFlowLayout vLayout=new VFlowLayout();
		f.getContentPane().setLayout(vLayout);
		for(int i=0;i<8;i++){
			JButton btn=new JButton(i+"");
			f.getContentPane().add(btn);
		}
		f.setSize(400,400);
		f.setVisible(true);
	}
	public Dimension preferredLayoutSize(Container parent) {
		return  calculateLayoutSize(parent);
	}
	public Dimension minimumLayoutSize(Container parent) {
		return  calculateLayoutSize(parent);
	}
	public void layoutContainer(Container parent) {
		calculateLayoutSize(parent);
	}
	/**
	 * Total up the height of each component in the container plus the insets of
	 * the container itself. Width is width of the container
	 */
	protected Dimension calculateLayoutSize(Container parent) {
		Component components[] = parent.getComponents();
		int left=0;
		int top=0;
		int compCount = components.length;
		for (int i = 0; i < compCount; i++) {
			Component comp=components[i];
			if(!comp.isVisible())continue;
			comp.setSize(parent.getWidth(),comp.getPreferredSize().height);
			Dimension dim=comp.getSize();
			Rectangle bounds=new Rectangle(left,top,dim.width,dim.height);
			comp.setBounds(bounds);
			top+=dim.height;
		}
		Dimension result=new Dimension(parent.getWidth(),top);
		return result;
	}
	/** Not used by this class */
	public void addLayoutComponent(String name, Component comp) {
		throw new RuntimeException("Not used by this class");
	}
	/** Not used by this class */
	public void removeLayoutComponent(Component comp) {
		throw new RuntimeException("Not used by this class");
	}
}