package com.tlw.tools.laf;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class A03TreeCellRendererLaf extends DefaultTreeCellRenderer {
	private static final long serialVersionUID = -608400297648051897L;
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
				row, hasFocus);
		if(leaf){
			String propertyFullName=(String)value;
			String propertyName=propertyFullName.split("\\.")[1];
			setText(propertyName);
		}
		return this;
	}
}