package com.tlw.eg.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import com.tlw.swing.Shower;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-22
Description:
 ********************************/
public class PopupUse extends JPanel {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		PopupUse popup=new PopupUse();
		Shower.show(popup);
	}
	Popup popup;
	JPanel panePopup=new JPanel();
	JButton btn=new JButton("show");
	JComboBox cb=new JComboBox();
	public PopupUse(){
		setLayout(new BorderLayout());
		panePopup.setBackground(Color.red);
		panePopup.setSize(200,200);
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				popup=PopupFactory.getSharedInstance().getPopup(PopupUse.this, panePopup, 100, 100);
				popup.show();
				System.out.println(cb.getComponentPopupMenu());
			}
		});
		add(btn,"North");
		add(cb,"South");
	}
}
