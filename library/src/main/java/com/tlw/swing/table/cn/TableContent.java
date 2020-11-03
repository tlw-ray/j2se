package com.tlw.swing.table.cn;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;
/**
 * 
 * @author fanhoujun
 *
 */
public class TableContent {
	private static final int MAX_COLUMN=128;//定义表格的最大列数
	//<CellData>
	private List list=new ArrayList();
	private int rowCount=0;
	private boolean isNewLine=true;
	
	/**
	 * 连续多次调用等同于一次调用。
	 */	
	public void println(){
		if(isNewLine){
			return;
		}
		list.add(null);
		rowCount++;
		isNewLine=true;
	}
	
	public void append(Object value){
		append(value,1,1);
	}
	public void append(Object value,int colSpan){
		append(value,colSpan,1);
	}
	
	public void append(Object value,int colSpan,int rowSpan){
		if(colSpan<1||rowSpan<1){
			throw new IllegalArgumentException("colSpan和rowSpan都必需大于0(colSpan:+"+colSpan+",rowSpan:"+rowSpan+")");
		}
		if(isNewLine){
			isNewLine=false;		
		}
		list.add(new CellData(value,colSpan,rowSpan));
	}
		
	public DTable createTable(){
		return createTable(null);
	}
	public DTable createTable(Object[]columnHeaders){		
		println();	
		//<CellData>
		List[] listData=new List[rowCount];
		for (int i = 0; i < listData.length; i++) {
			//<CellData>
			listData[i]=new ArrayList();
		}		
		CellData[][] cover=new CellData[rowCount][100];//记录被跨过（覆盖的）单元格
		int cusorRow=0;//行游标
		int cusorCol=0;//列游标
		int column=0;//记录表格的列数（取所有行的最多列）	
		for (int i=0;i<list.size();i++) {		
			CellData cellData =(CellData) list.get(i);
			if(cellData!=null){
				if(cusorCol==MAX_COLUMN){
					throw new RuntimeException("表格的列数不能超过"+MAX_COLUMN+"列");
				}
				while(cover[cusorRow][cusorCol]!=null){
					cusorCol++;
					listData[cusorRow].add(null);
				}
				listData[cusorRow].add(cellData);				
				setSpanHidden(cusorRow, cusorCol, cellData, cover);
				while(cover[cusorRow][++cusorCol]!=null){
					listData[cusorRow].add(null);
				}
			}else{
				column=column<cusorCol?cusorCol:column;
				cusorCol=0;
				cusorRow++;
			}
		}
		if(columnHeaders==null){
			columnHeaders=new Object[column];
		}
		return new DTable(new GridSplitImpl(listData,cover),createTableModel(listData,columnHeaders));
	}
	//<CellData>
	private DefaultTableModel createTableModel(List[] listData,Object[] columnHeaders){
		Object[][]data=new Object[listData.length][columnHeaders.length];
		int row=0;
		//<CellData>
		for(int i=0;i<listData.length;i++){
			List rowList=listData[i];
			int col=0;
			for(int j=0;j<rowList.size();j++){
				CellData cellData=(CellData)rowList.get(j);
				data[row][col++]=cellData==null?null:cellData.getValue();				         
			}
			row++;
		}
		return new DefaultTableModel(data,columnHeaders);
	}
	
	/**
	 * 将该单元跨过的其他单元格的状态设为隐藏
	 * @param row
	 * @param col
	 */
	private void setSpanHidden(int row,int col,CellData cellData,CellData[][] cover){
		if(cellData.getColSpan()<2&&cellData.getRowSpan()<2){
			return;
		}
		CellData coverData=CellData.creatHiddenCell(row, col);
		if(col+cellData.getColSpan()>=MAX_COLUMN){
			throw new RuntimeException("表格的列数不能超过"+MAX_COLUMN+"列");
		}
		
		for(int i=0;i<cellData.getColSpan();i++){			
			for(int j=0;j<cellData.getRowSpan();j++){				
				if(row+j<cover.length){					
					cover[row+j][col+i]=coverData;
				}else{
					cellData.setRowSpan(j);					
				}
			}
		}
		cover[row][col]=null;
	}
	
}
