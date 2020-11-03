package com.tlw.ui.alarm.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-30
@version:2009-3-30
Description:
 */
public class DefaultAlarmTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -509284750796505030L;
	public AbstractPagger pagger;
	String[] columnNames;
	List data=new Vector();
	public DefaultAlarmTableModel(String[] columnNames,int pageSize) {
		this.columnNames=columnNames;
		pagger=new Pagger(pageSize);
	}
	public int getColumnCount() {
		return columnNames.length;
	}
	public int getRowCount() {
		return pagger.getPageSize();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		int dataRowIndex=pagger.getPageSize()-rowIndex-1+(pagger.getCurrentPage()-1)*pagger.getPageSize();
		if(dataRowIndex>=data.size()){
			return null;
		}else{
			List row=(List)data.get(dataRowIndex);
			return row.get(columnIndex);
		}
	}
	public String getColumnName(int i){
		return columnNames[i];
	}
	public void appendRow(List row){
		data.add(row);
	}
	public List getData(){
		return data;
	}
	public void setData(List rows){
		this.data=rows;
	}
	class Pagger extends AbstractPagger{
		public Pagger(int pageSize){
			this.pageSize=pageSize;
		}
		public int getRowCount() {
			return data.size();
		}
	}
}
