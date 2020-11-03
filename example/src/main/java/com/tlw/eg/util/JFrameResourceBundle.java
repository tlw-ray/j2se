package com.tlw.eg.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-11-4
@version:2009-11-4
Description:
 */
public class JFrameResourceBundle extends JPanel {
	private static final long serialVersionUID = -4757533257652499675L;
	public static void main(String[] args) {
		JFrameResourceBundle pane=new JFrameResourceBundle();
		final JFrame f=UtilUi.show(pane, 600, 400, "");
		f.setJMenuBar(pane.jmenuBar);
	}
	ResourceBundle rb;
	JLabel label=new JLabel();
	JMenuBar jmenuBar=new JMenuBar();
	JMenu jmenu=new JMenu();
	JRadioButtonMenuItem jmenuItemZh=new JRadioButtonMenuItem();
	JRadioButtonMenuItem jmenuItemEn=new JRadioButtonMenuItem();
	ButtonGroup bg=new ButtonGroup();
	public JFrameResourceBundle(){
		jmenuItemZh.setText(Locale.CHINESE.getDisplayName(Locale.CHINESE));
		jmenuItemEn.setText(Locale.ENGLISH.getDisplayName(Locale.ENGLISH));
		jmenuItemZh.setActionCommand(Locale.CHINESE.getLanguage());
		jmenuItemEn.setActionCommand(Locale.ENGLISH.getLanguage());
		ActionListener al=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equalsIgnoreCase(Locale.CHINESE.getLanguage())){
					Locale.setDefault(Locale.CHINESE);
				}else if(e.getActionCommand().equalsIgnoreCase(Locale.ENGLISH.getLanguage())){
					Locale.setDefault(Locale.ENGLISH);
				}
				initLocale();
			}
		};
		jmenuItemZh.addActionListener(al);
		jmenuItemEn.addActionListener(al);
		jmenu.add(jmenuItemZh);
		jmenu.add(jmenuItemEn);
		bg.add(jmenuItemZh);
		bg.add(jmenuItemEn);
		
		initLocale();
		
		jmenuBar.add(jmenu);
		add(label);
		
	}
	private void initLocale(){
		rb=ResourceBundle.getBundle(getClass().getName());
		label.setText(rb.getString("label"));
		jmenu.setText(rb.getString("menuLanguage"));

		//initialize the menu item checked item.
		Enumeration enu=bg.getElements();
		while(enu.hasMoreElements()){
			AbstractButton btn=(AbstractButton)enu.nextElement();
			if(btn.getText().equalsIgnoreCase(Locale.getDefault().getDisplayLanguage())){
				btn.setSelected(true);
			}
		}
	}
}
