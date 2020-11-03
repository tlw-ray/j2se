package com.tlw.eg.swing.jtable.edited;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;

import javax.swing.AbstractAction;
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
public class CellEditor4Integer extends AbstractTextFieldCellEditor implements TableCellEditor{
	private static final long serialVersionUID = -7570631086853780101L;
	Modified modified;
	Integer originalIntegerValue;
	Format nf=DecimalFormat.getIntegerInstance();
	JFormattedTextField jFormattedTextField=new JFormattedTextField(nf);
	public CellEditor4Integer(){
		jFormattedTextField.setBorder(BorderFactory.createEmptyBorder());
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		modified=null;
		originalIntegerValue=null;
		String textValue;
		Color foreground=UIManager.getColor("TextField.foreground");
		if(value instanceof Modified){
			modified=(Modified)value;
			if(modified.isModified())foreground=Color.blue;
			textValue=nf.format(modified.getValueChanged());
		}else{
			originalIntegerValue=(Integer)value;
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
			Number valueChanged=(Number)nf.parseObject(jFormattedTextField.getText());
			if(modified==null){
				if(valueChanged.intValue()==originalIntegerValue.intValue() || valueChanged.equals(originalIntegerValue)){
					return originalIntegerValue;
				}else{
					Modified newModified=new Modified();
					newModified.setValueOriginal(originalIntegerValue);
					newModified.setValueChanged(valueChanged.intValue());
					return newModified;
				}
			}else{
				if(valueChanged.intValue()==((Number)modified.getValueOriginal()).intValue() 
						|| valueChanged.equals(modified.getValueOriginal())){
					return modified.getValueOriginal();
				}else{
					modified.setValueChanged(valueChanged.intValue());
					return modified;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(modified==null)
			return originalIntegerValue;
		else
			return modified;
	}
	class ActionRevert1 extends AbstractAction {
		private static final long serialVersionUID = -1397004941371196167L;
		public ActionRevert1(){
			putValue(NAME,"还原");
			putValue(SHORT_DESCRIPTION,"还原为最初的值。");
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(modified==null){
				jFormattedTextField.setText(originalIntegerValue.toString());
			}else{
				jFormattedTextField.setText(modified.getValueOriginal().toString());
			}
			fireEditingStopped();
		}
	}
}