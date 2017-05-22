package com.tlw.eg.util.observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.tlw.util.Random;
import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-18
@version:2009-8-18
Description:
 */
public class MyPanel extends JPanel {
	private static final long serialVersionUID = -1543699695702602388L;
	public static void main(String[] args) {
		MyPanel pane=new MyPanel();
		UtilUi.show(pane, 400, 400, "");
	}
	ObserverdString myString=new ObserverdString();
	MyLabel label=new MyLabel();
	MyTextField textfield=new MyTextField();
	MyButton btn=new MyButton();
	public MyPanel(){
		myString.addObserver(label);
		myString.addObserver(textfield);
		myString.addObserver(btn);
		
		add(label);
		add(textfield);
		add(btn);
		
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				double d=Random.nextDouble();
				myString.setString(d+"");
			}
		});
		
		textfield.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				myString.setString(textfield.getText());
			}
		});
	}
}
