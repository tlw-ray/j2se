package com.tlw.eg.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-14
@version:2009-5-14
Description:
 */
public class MySplit {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		JPanel paneMain=new JPanel();
		JPanel paneLeft=new JPanel();
		JPanel paneExpend=new JPanel();
		JToggleButton btnControl=new JToggleButton(">");
		btnControl.setMargin(new Insets(0,0,0,0));
		final JPanel paneRight=new JPanel();
		paneRight.setPreferredSize(new Dimension(100,100));
		
		paneMain.setLayout(new BorderLayout());
		paneMain.add(paneLeft,BorderLayout.CENTER);
		paneMain.add(paneExpend,BorderLayout.EAST);
		
		paneExpend.setLayout(new BorderLayout());
		paneExpend.add(btnControl,BorderLayout.WEST);
		paneExpend.add(paneRight,BorderLayout.CENTER);
		
		btnControl.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JToggleButton sender=(JToggleButton)e.getSource();
				if(sender.isSelected()){
					sender.setText("<");
					paneRight.setVisible(false);
				}else{
					sender.setText(">");
					paneRight.setVisible(true);
				}
			}
		});
		
		frame.add(paneMain,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
	}
}
