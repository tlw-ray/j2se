package com.tlw.swing.table.cn;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class DTableModel extends AbstractTableModel{
	private static final long serialVersionUID = -7095738944219971520L;
	//<CellData>
	private List[] listData;
	private int column;
	public DTableModel(List[] listData,int column){
		this.listData=listData;
		this.column=column;
	}
	public int getColumnCount() {		
		return column;
	}

	public int getRowCount() {
		return listData.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		CellData cellData=(CellData) listData[rowIndex].get(columnIndex);
		return cellData==null?null:cellData.getValue();
	}
}
