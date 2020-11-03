package com.tlw.swing.table.cn;

class CellData{
	public static CellData creatHiddenCell(int posRow,int posCol){
		CellData cellData=new CellData(null,-1,-1);
		cellData.setPosRow(posRow);
		cellData.setPosCol(posCol);
		return cellData;			
	}
	private Object value;
	private int colSpan;
	private int rowSpan;
	private int posRow;
	private int posCol;
	public int getPosCol() {
		return posCol;
	}

	public void setPosCol(int posCol) {
		this.posCol = posCol;
	}

	public int getPosRow() {
		return posRow;
	}

	public void setPosRow(int posRow) {
		this.posRow = posRow;
	}

	CellData(Object value,int colSpan,int rowSpan){
		this.value=value;
		this.colSpan=colSpan;
		this.rowSpan=rowSpan;
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public int getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	public boolean isVisible(){
		return getRowSpan()<1;
	}
}
