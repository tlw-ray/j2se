package com.tlw.tools.dbf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-16
@version:2009-6-16
Description:
 */
public class DBFViewer extends JPanel {
	private static final long serialVersionUID = -5041848065024260588L;
	public static void main(String[] args) {
		DBFViewer viewer=new DBFViewer();
		Shower.show(viewer);
	}
	JFileChooser jfc=new JFileChooser();
	JButton jbtnFile=new JButton("打开");
	JTable jtable=new JTable();
	JScrollPane jscrollTable=new JScrollPane(jtable);
	public DBFViewer(){
		setLayout(new BorderLayout());
		add(jbtnFile,BorderLayout.NORTH);
		add(jscrollTable,BorderLayout.CENTER);
		jbtnFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int result=jfc.showOpenDialog(DBFViewer.this);
				if(result==jfc.APPROVE_OPTION){
					File file=jfc.getSelectedFile();
					try {
						DBFReader reader=new DBFReader(new FileInputStream(file));
						int fieldCount=reader.getFieldCount();
						Vector fields=new Vector();
						for(int i=0;i<fieldCount;i++){
							fields.add(reader.getField(i).getName());
						}
						Vector data=new Vector();
						while(reader.hasNextRecord()){
							Object[] objs=reader.nextRecord();
							List row=Arrays.asList(objs);
							Vector vrow=new Vector(row);
							data.add(vrow);
						}
						DefaultTableModel model=new DefaultTableModel(data,fields);
						jtable.setModel(model);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (JDBFException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
