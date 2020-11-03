package com.tlw.swing.table.cn;

interface GridSplit {
	/** 
	 * @参数row：指定单元格所在的逻辑行 
	 * @参数column：指定单元格所在的逻辑列 
	 * @返回指定单元格所跨越的列数 
	 */
	int spanCol(int row, int column);

	/** 
	 * @参数row：指定单元格所在的逻辑行 
	 * @参数column：指定单元格所在的逻辑列 
	 * @返回覆盖指定单元格的可视单元格的列值，如果单元格本来就是可视的话，返回自身的列值 
	 */
	int visibleColCell(int row, int column);
	
	/** 
	 * @参数row：指定单元格所在的逻辑行 
	 * @参数column：指定单元格所在的逻辑列 
	 * @返回指定单元格所跨越的行数 
	 */
	int spanRow(int row, int column);

	/** 
	 * @参数row：指定单元格所在的逻辑行 
	 * @参数column：指定单元格所在的逻辑列 
	 * @返回覆盖指定单元格的可视单元格的行值，如果单元格本来就是可视的话，返回自身的行值 
	 */
	int visibleRowCell(int row, int column);
	
	boolean isVisible(int row, int column);
}
