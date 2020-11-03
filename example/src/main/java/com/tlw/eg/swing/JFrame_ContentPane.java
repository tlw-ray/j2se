package com.tlw.eg.swing;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-11
Description:
1.对JFrame赋予Layout实际是赋给JFrame.getContentPane()

 ********************************/
public class JFrame_ContentPane extends JFrame {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		JFrame_ContentPane t=new JFrame_ContentPane();
		t.setVisible(true);
	}
	public JFrame_ContentPane(){
		initComponents();
	}
	private void initComponents(){
		setLayout(new FlowLayout());
		System.out.println(getContentPane().getLayout());
		System.out.println(getLayout());
		JButton btn=new JButton("aaaa");
		JButton btn2=new JButton("bbbb");
		add(btn);
		add(btn2);
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
}