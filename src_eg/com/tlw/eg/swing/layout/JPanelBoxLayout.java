package com.tlw.eg.swing.layout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-9
@version:2009-6-9
Description:
 */
public class JPanelBoxLayout extends JPanel{
	private static final long serialVersionUID = 6662345939691994295L;
	public static void main(String[] args) {
		JPanelBoxLayout pane=new JPanelBoxLayout();
		Shower.show(pane);
	}
	public JPanelBoxLayout(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int i=0;i<5;i++){
			JButton btn=new JButton(i+"");
			add(btn);
		}
	}
}
