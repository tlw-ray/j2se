package com.tlw.eg.util.observer;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-18
@version:2009-8-18
Description:
 */
public class MyButton extends JButton implements Observer {
	private static final long serialVersionUID = 1115923130511574103L;

	@Override
	public void update(Observable o, Object arg) {
		this.setText(arg.toString());
	}

}
