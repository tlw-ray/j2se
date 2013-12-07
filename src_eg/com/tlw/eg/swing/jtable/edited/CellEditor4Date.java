package com.tlw.eg.swing.jtable.edited;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;

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
public class CellEditor4Date extends AbstractTextFieldCellEditor implements TableCellEditor {
	private static final long serialVersionUID = 3031850322045602565L;
	Modified modified;
	Date originalDateValue;
	Format format=DateFormat.getDateTimeInstance();
	JFormattedTextField jFormattedTextField=new JFormattedTextField(format);
	public CellEditor4Date(){
		jFormattedTextField.setBorder(BorderFactory.createEmptyBorder());
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		modified=null;
		originalDateValue=null;
		String textValue;
		Color foreground=UIManager.getColor("TextField.foreground");
		if(value instanceof Modified){
			modified=(Modified)value;
			if(modified.isModified())foreground=Color.blue;
			Date dateChanged=(Date)modified.getValueChanged();
			textValue=format.format(dateChanged);
			System.out.println("Input:"+dateChanged.getTime());
		}else{
			originalDateValue=(Date)value;
			textValue=format.format(value);
		}
		jFormattedTextField.setText(textValue);
		jFormattedTextField.setForeground(foreground);
		if(isSelected)jFormattedTextField.selectAll();
		return jFormattedTextField;
	}
	@Override
	public Object getCellEditorValue() {
		try {
			Date valueChanged=(Date)format.parseObject(jFormattedTextField.getText());
			System.out.println("Output:"+valueChanged.getTime());
			if(modified==null){
				if(valueChanged==originalDateValue || valueChanged.equals(originalDateValue)){
					return originalDateValue;
				}else{
					Modified newModified=new Modified();
					newModified.setValueOriginal(originalDateValue);
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
			return originalDateValue;
		else
			return modified;
	}
}