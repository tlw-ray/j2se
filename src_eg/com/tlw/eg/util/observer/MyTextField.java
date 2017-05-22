package com.tlw.eg.util.observer;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-18
@version:2009-8-18
Description:
 */
public class MyTextField extends JTextField implements Observer {
	private static final long serialVersionUID = 8234828722670430193L;
	public MyTextField(){
		this.setColumns(10);
	}
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("be notified");
		setText(arg.toString());
	}

}
