package com.tlw.eg.swing.jtable;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-10-19
@version:2009-10-19
Description:第一列锁定的JTable
 */
public class JTableRowHeaded extends JTable {
	private static final long serialVersionUID = 2985728858828206861L;
	public static void main(String[] args) {
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
		TableModel model=new DefaultTableModel(data, columnNames2);
		JTable table=new JTableRowHeaded(model,null,null,80);
		JScrollPane scroll=new JScrollPane(table);
		UtilUi.show(scroll, 800, 600, "");
	}
	JTable jtableRowHeader=new JTable();
	int rowHeaderWidth=80;
	private final int ROW_HEADER_COLUMN_COUNT=1;
	public JTableRowHeaded(TableModel tableModel,TableColumnModel  cm, ListSelectionModel sm,int headerWidth){
		super(tableModel,cm,sm);
		//构建两个实质表，一个仅显示前几个锁定的列，一个显示数据可横向拖动
		rowHeaderWidth=headerWidth;
		createRowHeader();
	}
    protected void configureEnclosingScrollPane() {
    	Container p = getParent();
        if (p instanceof JViewport) {
            Container gp = p.getParent();
            if (gp instanceof JScrollPane) {
                JScrollPane parentJScrollPane=(JScrollPane)gp;
    			parentJScrollPane.setRowHeaderView(jtableRowHeader);
    			parentJScrollPane.setColumnHeaderView(getTableHeader());
    			parentJScrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER,jtableRowHeader.getTableHeader());
    			parentJScrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER,new JLabel("历史数据"));
            }
        }
    }
	public JTable getJTableRowHeader() {
		return jtableRowHeader;
	}
	public void setJTableRowHeader(JTable jtableRowHeader) {
		this.jtableRowHeader = jtableRowHeader;
	}
	
	public void createRowHeader(){
		//构建两个实质表，一个仅显示前几个锁定的列，一个显示数据可横向拖动
		if(getModel()==null || jtableRowHeader==null)return;
		
		DefaultTableColumnModel columnModelRowHead=new DefaultTableColumnModel();
		DefaultTableColumnModel columnModelContent=new DefaultTableColumnModel();
		for(int i=0;i<getColumnCount();i++){
			if(i<ROW_HEADER_COLUMN_COUNT){
				columnModelRowHead.addColumn(getColumnModel().getColumn(i));
			}else{
				columnModelContent.addColumn(getColumnModel().getColumn(i));
			}
		}

		jtableRowHeader.setModel(getModel());
		jtableRowHeader.setColumnModel(columnModelRowHead);
		jtableRowHeader.setPreferredScrollableViewportSize(new Dimension(rowHeaderWidth,getHeight()));
		jtableRowHeader.setAutoscrolls(false);
		jtableRowHeader.setColumnSelectionAllowed(false);
		jtableRowHeader.getTableHeader().setResizingAllowed(false);
		
		setColumnModel(columnModelContent);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setSelectionModel(jtableRowHeader.getSelectionModel());//使得两个表在选中时保持一致
	}
}