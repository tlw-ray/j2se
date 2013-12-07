package com.tlw.ui.alarm.model;

import javax.swing.table.DefaultTableModel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-30
@version:2009-3-30
Description:只读的TableModel;
 */
public class DefaultReadOnlyTableModel extends DefaultTableModel {
	private static final long serialVersionUID = -7768824259790086503L;
	public DefaultReadOnlyTableModel(Object[] columnNames) {
		super(columnNames,0);
	}
	public boolean isCellEditable(int row, int column){
		return false;
	}
}
