package com.tlw.eg.swing.jtable.edited;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public class CellEditor4Boolean extends AbstractCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 3031850322045602565L;
	Modified modified;
	Boolean originalBooleanValue;
	JCheckBox jcheckBox=new JCheckBox();
	{jcheckBox.setForeground(Color.blue);
	jcheckBox.setFont(new Font("Dialog",Font.ITALIC,8));}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		modified=null;
		originalBooleanValue=null;
		boolean checked;
		if(value instanceof Modified){
			modified=(Modified)value;
			Boolean bool=(Boolean)modified.getValueChanged();
			checked=bool.booleanValue();
			jcheckBox.setText("changed");
		}else{
			originalBooleanValue=(Boolean)value;
			checked=originalBooleanValue.booleanValue();
			jcheckBox.setText("");
		}
		jcheckBox.setSelected(checked);
		return jcheckBox;
	}
	@Override
	public Object getCellEditorValue() {
		Object valueChanged=jcheckBox.isSelected();
		if(modified==null){
			if(valueChanged==originalBooleanValue || valueChanged.equals(originalBooleanValue)){
				return originalBooleanValue;
			}else{
				Modified newModified=new Modified();
				newModified.setValueOriginal(originalBooleanValue);
				newModified.setValueChanged(valueChanged);
				return newModified;
			}
		}else{
			if(valueChanged==modified.getValueOriginal() || valueChanged.equals(modified.getValueOriginal())){
				return modified.getValueOriginal();
			}else{
				modified.setValueChanged(valueChanged);
				return modified;
			}
		}
	}
}