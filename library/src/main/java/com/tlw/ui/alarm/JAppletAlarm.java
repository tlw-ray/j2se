package com.tlw.ui.alarm;

import javax.swing.JApplet;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-30
@version:2009-3-30
Description:
 */
public class JAppletAlarm extends JApplet {
	private static final long serialVersionUID = 1846896414266014363L;
	public JAppletAlarm() {
		UtilAlarm.initUIManager();
		final JPanelAlarm jpaneAlarm=new JPanelAlarm();
		getContentPane().add(jpaneAlarm);
	}
}