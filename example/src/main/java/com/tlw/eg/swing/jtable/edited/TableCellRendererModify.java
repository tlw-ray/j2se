package com.tlw.eg.swing.jtable.edited;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-28
@version:2009-12-28
Description:
 */
public class TableCellRendererModify extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 8766296963103435057L;
	Format formatDate=DateFormat.getDateTimeInstance();
	Format formatNumber=NumberFormat.getNumberInstance();
	Format formatInteger=NumberFormat.getIntegerInstance();
	JCheckBox jcheckBox=new JCheckBox();
	Border emptyBorder=BorderFactory.createLineBorder(Color.gray);
	Border blueBorder=BorderFactory.createLineBorder(Color.blue);
	{jcheckBox.setBackground(UIManager.getColor("table.background"));
	jcheckBox.setForeground(Color.blue);
	jcheckBox.setFont(new Font("Dialog",Font.ITALIC,8));}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		jcheckBox.setText("");
		if(value instanceof Modified){
			Modified modified=(Modified)value;
			Object obj=modified.getValueChanged();
			if(obj instanceof Boolean){
				Boolean bool=(Boolean)obj;
				jcheckBox.setSelected(bool.booleanValue());
				jcheckBox.setText("changed");
				return jcheckBox;
			}
		}
		if(value instanceof Boolean){
			Boolean bool=(Boolean)value;
			jcheckBox.setSelected(bool.booleanValue());
			return jcheckBox;
		}
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	@Override
	public void setValue(Object value){
		super.setValue(value);
		if(value instanceof Modified){
			Modified mValue=(Modified)value;
			if(mValue.getValueChanged()!=null){
				setForeground(Color.blue);
				setText(valueToString(mValue.getValueChanged()));
			}
		}else{
			setForeground(Color.black);
			setText(valueToString(value));
		}
	}
	private String valueToString(Object value){
		if(value instanceof Date){
			Date date=(Date)value;
			return formatDate.format(date);
		}else if(value instanceof Integer){
			return formatInteger.format(value);
		}else if(value instanceof Number){
			return formatNumber.format(value);
		}else if(value instanceof Modified){
			Modified modified=(Modified)value;
			return valueToString(modified.getValueChanged());
		}else{
			return value.toString();
		}
	}
}