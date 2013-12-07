package com.tlw.tools.xml;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
@since 2011-5-6
@author 唐力伟 (liwei.tang@magustek.com)
学习XPath用的界面
 */
public class JPanelXPath extends JPanel {
	private static final long serialVersionUID = 3726956687600771634L;
	JLabel jlabel=new JLabel("XPath:");
	JTextField jtextField=new JTextField();
	JTextPane jtextPane=new JTextPane();
	JScrollPane jscrollPane=new JScrollPane(jtextPane);
	public JPanelXPath(){
		
	}
}
