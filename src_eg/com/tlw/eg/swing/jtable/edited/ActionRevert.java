package com.tlw.eg.swing.jtable.edited;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public class ActionRevert extends AbstractAction {
	private static final long serialVersionUID = -1397004941371196167L;
	public ActionRevert(){
		putValue(NAME,"还原");
		putValue(SHORT_DESCRIPTION,"还原为最初的值。");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		e.getSource();
	}
}
