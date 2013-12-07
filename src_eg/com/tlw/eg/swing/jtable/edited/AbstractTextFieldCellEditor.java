package com.tlw.eg.swing.jtable.edited;

import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public abstract class AbstractTextFieldCellEditor extends AbstractCellEditor implements TableCellEditor{
	private static final long serialVersionUID = 1831522556300466117L;
	public boolean isCellEditable(EventObject e) {
		if(e instanceof MouseEvent){
			MouseEvent me=(MouseEvent)e;
			if(me.getClickCount()>1)return true;
		}
		return false;
	}
}
