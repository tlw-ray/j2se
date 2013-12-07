package com.tlw.eg.swing.jframe;

import javax.swing.JFrame;
import javax.swing.JRootPane;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-14
@version:2009-5-14
Description:
 */
public class DisableFrameButtons {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		function1(frame);
		//function2(frame);
		frame.setExtendedState(JFrame.NORMAL);
		frame.setSize(400,400);
		frame.setVisible(true);
	}
	public static void function1(JFrame frame){
		frame.setUndecorated(true);                                           		//不显示标题栏,最大化,最小化,退出按钮
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG );		//使frame只剩下标题栏
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 				//
	}
	public static void function2(JFrame frame){
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 				//禁用关闭按钮	
		frame.setResizable(false);													//使窗体最大化按钮，关闭按钮不可用，不可用。
		//....缺少禁用最小化按钮的
	}
}
