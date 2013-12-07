package com.tlw.eg.swing.jtable.edited;

import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public final class CellEditor4Number extends AbstractTextFieldCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 3031850322045602565L;
	Modified modified;
	Double originalDoubleValue;
	NumberFormat nf=DecimalFormat.getNumberInstance();
	JFormattedTextField jFormattedTextField=new JFormattedTextField(nf);
	public CellEditor4Number(){
		jFormattedTextField.setBorder(BorderFactory.createEmptyBorder());
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		modified=null;
		originalDoubleValue=null;
		String textValue;
		Color foreground=UIManager.getColor("TextField.foreground");
		if(value instanceof Modified){
			modified=(Modified)value;
			if(modified.isModified())foreground=Color.blue;
			textValue=nf.format(modified.getValueChanged());
		}else{
			originalDoubleValue=(Double)value;
			textValue=nf.format(value);
		}
		jFormattedTextField.setText(textValue);
		jFormattedTextField.setForeground(foreground);
		if(isSelected)jFormattedTextField.selectAll();
		return jFormattedTextField;
	}
	@Override
	public Object getCellEditorValue() {
		try {
			Object valueChanged=nf.parseObject(jFormattedTextField.getText());
			if(modified==null){
				if(valueChanged==originalDoubleValue || valueChanged.equals(originalDoubleValue)){
					return originalDoubleValue;
				}else{
					Modified newModified=new Modified();
					newModified.setValueOriginal(originalDoubleValue);
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
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(modified==null)
			return originalDoubleValue;
		else
			return modified;
	}
}