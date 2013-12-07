package com.tlw.eg.applet;

import javax.swing.JApplet;
import javax.swing.JLabel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-18
@version:2009-5-18
Description:
 */
public class JavaScriptCallApplet extends JApplet{
	private static final long serialVersionUID = -1184152538470519185L;
	JLabel label=new JLabel("Label!");
	public JavaScriptCallApplet(){
		add(label);
	}
	public void setLabelText(String param){
		label.setText(param);
	}
}
