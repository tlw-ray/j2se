package com.tlw.eg.swing.jtable;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableColumnModel;

import com.tlw.util.UtilUi;


public class JScrollPaneRowHeaded extends JScrollPane {
	private static final long serialVersionUID = -1314527406967999262L;
	public static void main(String[] args){
		int rowCount=100;
		int columnCount=100;
		String[][] data=new String[rowCount][columnCount];
		String[] columnNames2=new String[columnCount];
		for(int i=1;i<=rowCount;i++){
			for(int j=1;j<=columnCount;j++){
				data[i-1][j-1]=i*j+"";
			}
		}
		for(int i=1;i<=columnCount;i++)columnNames2[i-1]=i+"";
		JTable table=new JTable(data,columnNames2);
		JScrollPane scroll=new JScrollPaneRowHeaded(table,1);
		UtilUi.show(scroll, 800, 600, "");
	}
	public JScrollPaneRowHeaded(JTable mainTable, final int numberFixedColumns) {
		super();
		//构建两个实质表，一个仅显示前几个锁定的列，一个显示数据可横向拖动
		DefaultTableColumnModel columnModelColumnHead=new DefaultTableColumnModel();
		DefaultTableColumnModel columnModelData=new DefaultTableColumnModel();
		for(int i=0;i<mainTable.getColumnCount();i++){
			if(i<numberFixedColumns){
				columnModelColumnHead.addColumn(mainTable.getColumnModel().getColumn(i));
			}else{
				columnModelData.addColumn(mainTable.getColumnModel().getColumn(i));
			}
		}
		JTable jtableRowHeader = new JTable(mainTable.getModel(),columnModelColumnHead);
		JTable jtableData = new JTable(mainTable.getModel(),columnModelData);
		jtableRowHeader.setAutoscrolls(false);
		jtableRowHeader.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jtableData.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//使得两个表在选中时保持一致
		jtableData.setSelectionModel(jtableRowHeader.getSelectionModel());

		//构建行头
		jtableRowHeader.setMaximumSize(new Dimension(jtableRowHeader.getPreferredSize().width,jtableData.getSize().height));
		jtableRowHeader.setColumnSelectionAllowed(false);
		jtableRowHeader.getTableHeader().setResizingAllowed(false);

		JViewport headerViewport = new JViewport();
		headerViewport.setView(jtableRowHeader);
		headerViewport.setPreferredSize(jtableRowHeader.getMaximumSize());
		
		setRowHeader(headerViewport);
		setViewportView(jtableData);
		setCorner(JScrollPane.UPPER_LEFT_CORNER,jtableRowHeader.getTableHeader());
		setCorner(JScrollPane.LOWER_LEFT_CORNER,new JLabel("历史数据"));
	}
}
