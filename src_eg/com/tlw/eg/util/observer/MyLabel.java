package com.tlw.eg.util.observer;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-18
@version:2009-8-18
Description:
 */
public class MyLabel extends JLabel implements Observer {
	private static final long serialVersionUID = 7443437685081850025L;
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("MyLabel be notified");
		setText(arg.toString());
	}

}
