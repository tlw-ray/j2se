package com.tlw.eg.swing.jtable;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-17
@version:2009-8-17
Description:
 */
public class JTableColumnResize extends JPanel {
	private static final long serialVersionUID = -6046429180431075689L;
	public static void main(String[] args){
		JTableColumnResize col=new JTableColumnResize();
		UtilUi.show(col, 400, 300, "");
	}
	JTable jtable=new JTable();
	String[][] data=new String[][]{
			{"111","222"},
			{"4444","333"},
			{"555","5666"}
	};
	String[] names=new String[]{"列1","列2"};
	public JTableColumnResize(){
		jtable.setModel(new DefaultTableModel(data, names));
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setLayout(new BorderLayout());
		JScrollPane jscrollTable=new JScrollPane(jtable);
		add(jscrollTable,BorderLayout.CENTER);

		jtable.getColumnModel().getColumn(0).addPropertyChangeListener(new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if(evt.getPropertyName().equals("preferredWidth")){
					int width=((Integer)evt.getNewValue()).intValue();
					TableColumnModel tcm=jtable.getColumnModel();
					for(int i=0;i<tcm.getColumnCount();i++){
						TableColumn tc=tcm.getColumn(i);
						if(!tc.equals(evt.getSource())){
							System.out.println("return");
						}else{
							tc.setPreferredWidth(width);
							System.out.println("setWidth:"+width);
						}
					}
				}
				jtable.repaint();
				jtable.revalidate();
				//jtable.doLayout();
				jtable.updateUI();
				System.out.println(evt.getPropertyName()+evt.getNewValue());
			}
		});
	}
}
