package com.tlw.swing.table;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.tlw.reflect.UtilReflex;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-6
@version:2008-12-6
Descript: 能够把相同对象集合作为表格显示的tableModel;每一列都是此类对象的一个可读无参的属性(可通过get....()获得)。
 */
public class JTableModel4ItemList extends AbstractTableModel{
	private static final long serialVersionUID = -1449254837697743863L;
	/**
	 * ObjectTableModel 使用示例
	 * @param args
	 */
	public static void main(String[] args) {
		Foo f1=new Foo(0,"Math.PI",Math.PI);
		Foo f2=new Foo(1,"Math.E",Math.E);
		Foo f3=new Foo(2,"Math.Foo3",3.0);
		List foos=new Vector();
		foos.add(f1);foos.add(f2);foos.add(f3);
		JTableModel4ItemList model=new JTableModel4ItemList(foos);
		
		JFrame f=new JFrame();
		JTable jtable=new JTable();
		JScrollPane jscroll=new JScrollPane(jtable);
		jtable.setModel(model);
		
		f.getContentPane().add(jscroll,"Center");
		f.setSize(300,300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private List objects;	//对象集合
	private List getMethods;//用来存放对象的类的各个get方法
	
	public JTableModel4ItemList(){}
	public JTableModel4ItemList(List items){setObjects(items);}
	
	public List getObjects(){return objects;}
	public void setObjects(List items){
		getMethods=UtilReflex.getReadAbleMethods(items.get(0));
		objects=items;
	}
	public int getColumnCount() {
		return getMethods.size();
	}
	public int getRowCount() {
		return objects.size();
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object obj=objects.get(rowIndex);
		Method method=(Method)getMethods.get(columnIndex);
		try {
			return method.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String getColumnName(int column) {
		Method method=(Method)getMethods.get(column);
		return method.getName().substring(3);//get
	}
	static class Foo {
		private int id;
		private String name;
		private double value;
		public Foo(){}
		public Foo(int id,String name,double value){
			this.id=id;
			this.name=name;
			this.value=value;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}
	}
}
