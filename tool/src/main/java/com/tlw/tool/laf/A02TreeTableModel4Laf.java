package com.tlw.tool.laf;

import java.awt.Color;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import com.tlw.swing.Shower;
import com.tlw.swing.tabletree.AbstractTreeTableModel;
import com.tlw.swing.tabletree.JTreeTable;


public class A02TreeTableModel4Laf extends AbstractTreeTableModel {
	static String root="Look And Feel";
	public static void main(String[] args){
		JTreeTable jtt=new JTreeTable();
		JScrollPane jscroll=new JScrollPane(jtt);
		
		A02TreeTableModel4Laf model=new A02TreeTableModel4Laf(root);
		jtt.setTreeTableModel(model);
		
		A03TreeCellRendererLaf renderer=new A03TreeCellRendererLaf();
		jtt.tree.setCellRenderer(renderer);
		
		jtt.setDefaultEditor(Color.class, new DefaultCellEditor(new A04JTextFieldColor()));
		
		Shower.show(jscroll);
	}
	TreeMap<String,TreeSet<String>> componentPropertiesMap=A01_JTreeLaf.getComponentPropertiesMap();;
	public A02TreeTableModel4Laf(Object root) {
		super(root);
	}

	public int getColumnCount() {
		return 2;
	}

	public String getColumnName(int column) {
		if(column==0) return "属性";
		else if(column==1) return "值";
		return null;
	}

//	@Override
//	public Class<?> getColumnClass(int column) {
//		return Object.class;
//	}

//	@Override
//	public boolean isCellEditable(Object node, int column) {
//		return column==1;
//	}

	public Object getValueAt(Object node, int column) {
		if(column==1){
			Object value=UIManager.getDefaults().get(node);
			return value;
		}
		return null;
	}

	
	
	@Override
	public Object getChild(Object parent, int index) {
		if(parent==root){
			Vector<String> components=new Vector<String>(componentPropertiesMap.keySet());
			return components.get(index);
		}else if(componentPropertiesMap.get(parent)!=null){
			TreeSet<String> properties=componentPropertiesMap.get(parent);
			Vector<String> keys=new Vector<String>(properties);
			return keys.get(index);
		}else if(UIManager.getDefaults().get(parent)!=null){
			return UIManager.getDefaults().get(parent);
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if(parent==root){
			return componentPropertiesMap.keySet().size();
		}else if(componentPropertiesMap.get(parent)!=null){
			return componentPropertiesMap.get(parent).size();
		}else
			return 0;
	}
	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if(parent==root){
			Vector<String> components=new Vector<String>(componentPropertiesMap.keySet());
			return components.indexOf(child);
		}else{
			TreeSet<String> properties=componentPropertiesMap.get(parent);
			Vector<String> propertiesV=new Vector<String>(properties);
			return propertiesV.indexOf(child);
		}
	}
}