package com.tlw.eg.swing.jtable.edited;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-24
@version:2009-12-24
Description:可以感知被编辑过的JTable
 */
public class Main extends JPanel {
	private static final long serialVersionUID = 2559255834230029880L;
	public static void main(String[] args) throws ParseException {
		UtilUi.initUIManager();
		String[] columns=new String[]{"Integer","Double","Date","String","Boolean"};
		Format df=DateFormat.getDateTimeInstance();
		Object[][] data=new Object[][]{
				{new Integer(10),new Double(20.01),df.parseObject("2009-1-1 0:00:00"),"Hello",Boolean.TRUE},
				{new Integer(20),new Double(30.01),df.parseObject("2009-2-1 0:00:00"),"Hello1",Boolean.FALSE},
		};
		
		final DefaultTableModel modifiableModel=new DefaultTableModel();
		modifiableModel.setDataVector(data, columns);
		final JTable jtable=new JTable(){
			private static final long serialVersionUID = 9204319624102234186L;
			TableCellRenderer ren=new TableCellRendererModify();
			CellEditor4Boolean cellEditorBoolean=new CellEditor4Boolean();
			CellEditor4Date cellEditorDate=new CellEditor4Date();
			CellEditor4Number cellEditorNumber=new CellEditor4Number();
			CellEditor4String cellEditorString=new CellEditor4String();
			CellEditor4Integer cellEditorInteger=new CellEditor4Integer();
			public TableCellRenderer getCellRenderer(int row,int column){
				return ren;
			}
		    public TableCellEditor getCellEditor(int row, int column) {
		    	super.getCellEditor(row, column);
		       Object obj=getValueAt(row, column);
		       if(obj instanceof Modified){
		    	   Modified modified=(Modified)obj;
		    	   obj=modified.getValueOriginal();
		       }
		       if(obj instanceof Integer){
		    	   return cellEditorInteger;
		       }else if(obj instanceof Number){
		    	   return cellEditorNumber;
		       }else if(obj instanceof String){
		    	   return cellEditorString;
		       }else if(obj instanceof Date){
		    	   return cellEditorDate;
		       }else if(obj instanceof Boolean){
		    	   return cellEditorBoolean;
		       }else{
		    	   return super.getCellEditor(row, column);
		       }
		    }
		};
		jtable.setModel(modifiableModel);
		jtable.setColumnSelectionAllowed(true);
		JScrollPane jscrollPane=new JScrollPane(jtable);
		JFrame frame=UtilUi.show(jscrollPane, 600, 400, "");
		
		JButton jbuttonClearModify=new JButton("Clear Modify");
		jbuttonClearModify.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i=0;i<jtable.getColumnCount();i++){
					for(int j=0;j<jtable.getRowCount();j++){
						Object obj=jtable.getValueAt(j, i);
						if(obj instanceof Modified){
							Modified modified=(Modified)obj;
							jtable.setValueAt(modified.getValueOriginal(), j, i);
						}
					}
				}
				jtable.updateUI();
			}
		});
		frame.add(jbuttonClearModify,BorderLayout.NORTH);
		
		JButton jbuttonShowCellRenderers=new JButton("Show CellRenderers");
		jbuttonShowCellRenderers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i=0;i<jtable.getColumnCount();i++){
					TableColumn tc=jtable.getColumnModel().getColumn(i);
					System.out.println(tc.getCellRenderer());
				}
			}
		});
		frame.add(jbuttonShowCellRenderers,BorderLayout.SOUTH);
	}
}