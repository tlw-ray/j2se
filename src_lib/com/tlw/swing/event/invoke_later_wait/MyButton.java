package com.tlw.swing.event.invoke_later_wait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月9日
 */
public class MyButton extends JButton {

	private static final long serialVersionUID = 7421757176152255456L;

	
	public MyButton(){
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(Thread.currentThread()+"\t"+e);
			}
			
		});
	}
}
