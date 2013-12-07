package com.tlw.swing.table;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.tlw.reflect.UtilReflex;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-10
@version:2008-12-10
Descript: 将对象属性作为表格显示的tableModel。第一列为属性名称，第二列为属性值。
 */
public class JTableModel4ObjectProperty extends AbstractTableModel {
	private static final long serialVersionUID = 8776021553086098633L;
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTableModel4ObjectProperty model=new JTableModel4ObjectProperty(frame);
		JTable table=new JTable(model);
		JScrollPane jscrollTable=new JScrollPane(table);
		frame.getContentPane().add(jscrollTable);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	Object object;
	Vector getMethods;
	JTable jtable=new JTable();
	JScrollPane jscroll=new JScrollPane(jtable);
	public JTableModel4ObjectProperty(Object obj){
		this.object=obj;
		getMethods=UtilReflex.getReadAbleMethods(obj);
	}
	public int getColumnCount() {
		return 2;
	}
	public int getRowCount() {
		return getMethods.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Method method=(Method)getMethods.get(rowIndex);
		if(columnIndex==0){
			return method.getName().substring(3);
		}else{
			try {
				return method.invoke(object, null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	public String getColumnName(int column) {
		if(column==0){
			return "名称";
		}else{
			return "值";
		}
	}
	
}
