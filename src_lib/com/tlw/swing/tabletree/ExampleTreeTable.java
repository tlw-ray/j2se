package com.tlw.swing.tabletree;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-15
@version:2009-7-15
Description:
 */
public class ExampleTreeTable extends JPanel{
	private static final long serialVersionUID = 3065214181131820108L;
	public static void main(String[] args){
		JTree tree=new JTree();
		MyTreeTableModel model=new MyTreeTableModel(tree.getModel().getRoot());
		JTreeTable jtt=new JTreeTable(model);
		Shower.show(jtt);
	}
	static class MyTreeTableModel extends AbstractTreeTableModel{
		public MyTreeTableModel(Object root) {
			super(root);
		}
		public int getColumnCount() {
			return 3;
		}

		public String getColumnName(int column) {
			switch (column){
			case 0:return "tree";
			case 1:return "name";
			case 2:return "row";
			}
			return "???";
		}

		public Object getValueAt(Object node, int column) {
			switch(column){
			case 0:return this;
			case 1:return node.toString();
			case 2:return new Integer(0);
			}
			return null;
		}

		public Object getChild(Object parent, int index) {
			TreeNode node=(TreeNode)parent;
			return node.getChildAt(index);
		}

		public int getChildCount(Object parent) {
			return ((TreeNode)parent).getChildCount();
		}
	}
}
