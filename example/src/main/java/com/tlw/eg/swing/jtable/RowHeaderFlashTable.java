package com.tlw.eg.swing.jtable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-4
@version:2009-5-4
Description:
 */
public class RowHeaderFlashTable extends JPanel {
	private static final long serialVersionUID = -6485089627423640612L;
	public static void main(String[] args) {
		RowHeaderFlashTable ft=new RowHeaderFlashTable();
		Shower.show(ft);
	}
	JTable jtable=new JTable();
	JScrollPane jscroll=new JScrollPane(jtable);
	String[] cols={"col1","col2"};
	String[][] data={
			{"红","11"},
			{"黄","22"},
			{"白","33"},
			{"绿","44"},
			{"红","55"},
			{"红","66"},
			{"黄","77"},
			{"白","88"},
			{"绿","99"},
			{"红","00"},
	};
	public RowHeaderFlashTable(){
		DefaultTableModel dtm=new DefaultTableModel(data,cols);
		MyTableRender render=new MyTableRender();
		jtable.setModel(dtm);
		jtable.getColumnModel().getColumn(0).setCellRenderer(render);
		setLayout(new BorderLayout());
		add(jscroll,BorderLayout.CENTER);
		Timer timmer=new Timer(500,new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				colorfull=!colorfull;
				jtable.repaint();
			}
		});
		timmer.start();
	}
	boolean colorfull=true;
	class MyTableRender extends DefaultTableCellRenderer{
		private static final long serialVersionUID = -1001859646342993337L;
		Color color=UIManager.getColor("Label.background");
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus,row, column);
			if(column==0){
				if(colorfull){
					if(value.equals("红")){
						setBackground(Color.red);
					}else if(value.equals("黄")){
						setBackground(Color.yellow);
					}else if(value.equals("白")){
						setBackground(Color.white);
					}else if(value.equals("绿")){
						setBackground(Color.green);
					}
				}else{
					setBackground(color);
				}
			}
			return this;
		}
	}
}
