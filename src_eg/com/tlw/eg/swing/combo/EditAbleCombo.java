package com.tlw.eg.swing.combo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.tlw.swing.Shower;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:
 ********************************/
public class EditAbleCombo extends JPanel {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		EditAbleCombo cb=new EditAbleCombo();
		Shower.show(cb);
	}
	JComboBox cbbox=new JComboBox(new String[]{"aaa","bbb"});
	public EditAbleCombo(){
		cbbox.setEditable(true);
		add(cbbox);
		cbbox.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String command=e.getActionCommand();
				if(command!=null && command.indexOf("Edited")>-1){
					System.out.println("按下了回车。。。");
				}
			}
		});
	}
}
