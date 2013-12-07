package com.tlw.eg.swing.jtable.edited;

import java.awt.Component;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public class CellEditor4Object extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 8163038304475186162L;
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		return null;
	}
	@Override
	public Object getCellEditorValue() {
		return null;
	}
	public boolean isCellEditable(EventObject e) {
		return false;
	}
}
