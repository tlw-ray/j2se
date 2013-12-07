package com.tlw.eg.swing.jtable;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-25
@version:2009-8-25
Description:
 */
public class JTableRowColumnHeadSelectAble extends JPanel{
	private static final long serialVersionUID = 2625337630618982870L;
	public static void main(String[] args){
		JTableRowColumnHeadSelectAble pane=new JTableRowColumnHeadSelectAble();
		UtilUi.show(pane, 400, 300, "");
	}
	JTable jtableData=new JTable();
	public JTableRowColumnHeadSelectAble(){
		Object[] columnNames=new Object[5];
		Object[][] data=new Object[5][5];
		for(int i=0;i<5;i++){
			columnNames[i]=i+"";
			for(int j=0;j<5;j++){
				data[i][j]=i*j+"";
			}
		}
		jtableData.setModel(new DefaultTableModel(data,columnNames));
		jtableData.setRowSelectionAllowed(true);
		jtableData.setColumnSelectionAllowed(true);
		MouseListener ml=new MousePressSelector();
		jtableData.addMouseListener(ml);
		jtableData.getTableHeader().addMouseListener(ml);
		JScrollPane jsp=new JScrollPane(jtableData);
		setLayout(new BorderLayout());
		add(jsp,BorderLayout.CENTER);
	}
	class MousePressSelector extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			if(e.getButton()==MouseEvent.BUTTON1){
				int column=jtableData.columnAtPoint(e.getPoint());
				if(e.getSource()==jtableData.getTableHeader()){
					jtableData.getColumnModel().getSelectionModel().setSelectionInterval(column, column);
					jtableData.getSelectionModel().setSelectionInterval(0, jtableData.getRowCount());
				}else if(e.getSource()==jtableData){
					if(column==0){
						int row=jtableData.rowAtPoint(e.getPoint());
						jtableData.getColumnModel().getSelectionModel().setSelectionInterval(0, jtableData.getColumnCount()-1);
						jtableData.getSelectionModel().setSelectionInterval(row,row);
					}
				}
			}
		}
	}
}
