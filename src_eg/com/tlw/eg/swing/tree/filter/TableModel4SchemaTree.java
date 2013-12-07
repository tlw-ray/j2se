package com.tlw.eg.swing.tree.filter;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tlw.swing.tabletree.AbstractTreeTableModel;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-14
@version:2009-7-14
Description:
 */
public class TableModel4SchemaTree extends AbstractTreeTableModel{
	private static final long serialVersionUID = -2396935681055876548L;
	public TableModel4SchemaTree(Object root) {
		super(root);
	}
	public int getColumnCount() {
		return 9;//Field property count;
	}
	String[] names=new String[]{"tree","type","length","unique","access","level","default","values","style"};
	public String getColumnName(int column) {
		return names[column];
	}
	public Object getValueAt(Object node, int column) {
		Node docNode=(Node)node;
		if(docNode.getNodeName().equalsIgnoreCase("Field")){
			Node namedItem= docNode.getAttributes().getNamedItem(names[column]);
			if(namedItem!=null)
				return namedItem.getNodeValue();
		}
		return null;
	}
	public Object getChild(Object parent, int index) {
		Node nodeParent=(Node)parent;
		NodeList nl=nodeParent.getChildNodes();
		for(int i=0;i<nl.getLength();i++){
			Node node=nl.item(i);
			if(node.getAttributes()!=null){
				index--;
				if(index<0)return node;
			}
		}
		return null;
	}
	public int getChildCount(Object parent) {
		Node nodeParent=(Node)parent;
		NodeList nl=nodeParent.getChildNodes();
		int childCount=0;
		int childNodeCount=nl.getLength();
		for(int i=0;i<childNodeCount;i++){
			Node node=nl.item(i);
			if(node.getAttributes()!=null)childCount++;
		}
		return childCount;
	}
}
