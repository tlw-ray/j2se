package com.tlw.eg.swing;

import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.tlw.swing.Shower;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-11-7
Description:
 ********************************/
public class ViewPortSame extends JPanel {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		ViewPortSame vps=new ViewPortSame();
		Shower.show(vps);
	}
	JTextArea jta1=new JTextArea();
	JTextArea jta2=new JTextArea();
	JScrollPane jsp1=new JScrollPane(jta1);
	JScrollPane jsp2=new JScrollPane(jta2);
	public ViewPortSame(){
		setLayout(new GridLayout(1,2));
		add(jsp1);
		add(jsp2);
		for(int i=0;i<100;i++){
			jta1.append(i+"111111111111111111111111111111111111\n");
			jta2.append(i+"2222222222222222\n");
		}
		jsp1.getViewport().addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				Rectangle rect=jsp1.getViewport().getViewRect();
				jsp2.getViewport().setViewPosition(rect.getLocation());
				jsp2.repaint();
			}
		});
		jsp2.getViewport().addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				Rectangle rect=jsp2.getViewport().getViewRect();
				jsp1.getViewport().setViewPosition(rect.getLocation());
				jsp1.repaint();
			}
		});
	}
}
