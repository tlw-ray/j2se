package com.tlw.eg.swing.jtable.edited;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public class CellEditor4String extends AbstractTextFieldCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 3031850322045602565L;
	Modified modified;
	Object originalStringValue;
	JTextField jTextField=new JTextField();
	public CellEditor4String(){
		jTextField.setBorder(BorderFactory.createEmptyBorder());
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		modified=null;
		originalStringValue=null;
		String textValue;
		Color foreground=UIManager.getColor("TextField.foreground");
		if(value instanceof Modified){
			modified=(Modified)value;
			if(modified.isModified())foreground=Color.blue;
			textValue=modified.getValueChanged().toString();
		}else{
			originalStringValue=value;
			textValue=value.toString();
		}
		jTextField.setText(textValue);
		jTextField.setForeground(foreground);
		if(isSelected)jTextField.selectAll();
		return jTextField;
	}
	@Override
	public Object getCellEditorValue() {
		Object valueChanged=jTextField.getText();
		if(modified==null){
			if(valueChanged==originalStringValue || valueChanged.equals(originalStringValue)){
				return originalStringValue;
			}else{
				Modified newModified=new Modified();
				newModified.setValueOriginal(originalStringValue);
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