package com.tlw.eg.swing.list;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import com.tlw.swing.Shower;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-11-21
Description:
 ********************************/
public class ScrollToVisible extends JPanel{
	private static final long serialVersionUID = 8111850933268020194L;
	public static void main(String[] args) {
		ScrollToVisible s=new ScrollToVisible();
		Shower.show(s);
	}
	JList list=new JList();
	JScrollPane scroll=new JScrollPane(list);
	JButton btn=new JButton("随机选中");
	
	public ScrollToVisible(){
		Vector<Integer>	items=new Vector<Integer>();
		for(int i=0;i<100;i++){
			items.add(i);
		}
		list.setListData(items);
		setLayout(new BorderLayout());
		
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int i=(int)(Math.random()*100);
				list.setSelectedIndex(i);
				System.out.println(i);
				//滚动到可见
				if(list.getParent() instanceof JViewport){
					JViewport vp=(JViewport)list.getParent();
					Rectangle rect = list.getCellBounds(i, i);
			        Point pt = vp.getViewPosition();
			        rect.setLocation(rect.x - pt.x, rect.y - pt.y);
			        vp.scrollRectToVisible(rect);
				}
			}
		});
		
		add(scroll,"West");
		add(btn,"North");
	}
}
