package com.tlw.swing.table.cn;

import java.util.List;

class GridSplitImpl implements GridSplit{
	//<CellData>
	List[] listData;
	CellData[][] cover;
	public GridSplitImpl(List[] listData,CellData[][] cover){
		this.listData=listData;
		this.cover=cover;
	}
	public boolean isVisible(int row, int column) {
		if(row>=listData.length||row<0) return true;
		return cover[row][column]==null;
	}

	public int spanCol(int row, int column) {
		if(listData[row].size()<column+1||listData[row].get(column)==null){
			return 1;
		}
		return ((CellData) listData[row].get(column)).getColSpan();
	}

	public int spanRow(int row, int column) {
		if(listData[row].size()<column+1||listData[row].get(column)==null){
			return 1;
		}
		return ((CellData) listData[row].get(column)).getRowSpan();
	}

	public int visibleColCell(int row, int column) {
		if(row>=listData.length||row<0) return -1;
		return isVisible(row, column)?column:cover[row][column].getPosCol();
	}

	public int visibleRowCell(int row, int column) {
		if(row>=listData.length||row<0) return -1;
		return isVisible(row, column)?row:cover[row][column].getPosRow();
	}	
}
