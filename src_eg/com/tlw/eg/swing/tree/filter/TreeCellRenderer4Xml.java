package com.tlw.eg.swing.tree.filter;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.w3c.dom.Node;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-14
@version:2009-7-14
Description:
 */
public class TreeCellRenderer4Xml extends DefaultTreeCellRenderer{
	private static final long serialVersionUID = 1988085007201726441L;
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		Node node=(Node)value;
		String nodeName=node.getNodeName();
		String nodeText=getText();
		if(nodeName.equalsIgnoreCase("RDT")){
			nodeText="RDT version="+node.getAttributes().getNamedItem("version").getNodeValue();
		}else if(nodeName.equalsIgnoreCase("RecordType")){
			nodeText="RecordType : "+node.getAttributes().getNamedItem("name").getNodeValue();
			nodeText+="     [type="+node.getAttributes().getNamedItem("type").getNodeValue()+"]";
		}else if(nodeName.equalsIgnoreCase("Field")){
			nodeText="["+node.getAttributes().getNamedItem("name").getNodeValue()+
			"] "+node.getAttributes().getNamedItem("desc").getNodeValue();
		}else{
			nodeText="<openPlant Schema>";
		}
		setText(nodeText);
		return this;
	}
}
