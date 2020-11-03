package com.tlw.eg.swing;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-24
@version:2009-12-24
Description:
 */
public class JSplitPaneInJLayeredPane {
	public static void main(String[] args) {
		JSplitPane jsp=new JSplitPane();
		JButton btn=new JButton("Button1");
		JSlider jsld=new JSlider();
		JLayeredPane jlp=new JLayeredPane();
		jlp.add(jsp,new Integer(0));
		jsp.setBounds(20, 20, 200, 200);
		jlp.add(btn,new Integer(1));
		btn.setBounds(50, 50, 80, 25);
		jlp.add(jsld,new Integer(2));
		jsld.setBounds(100, 100, 70, 10);
		UtilUi.show(jlp, 800, 600, "");
	}
}