package com.tlw.eg.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-22
Description:
 ********************************/
public class JFrameMouseEvent extends JFrame {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		JFrameMouseEvent f=new JFrameMouseEvent();
		f.setVisible(true);
	}
	JPanel pane=new JPanel();
	JButton btn=new JButton("xixi");
	public JFrameMouseEvent(){
		add(btn,"Center");
		addMouseListener(new MouseClick());
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	class MouseClick extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			System.out.println(e);
		}
	}
}