package com.tlw.tools.xml.tree;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-19
@version:2009-7-19
Description:
 */
public class XmlTree extends JPanel {
	private static final long serialVersionUID = -8915767322097671968L;
	public static void main(String[] args) {
		XmlTree tree=new XmlTree();
		Shower.show(tree);
	}
	JTree tree=new JTree();
	public XmlTree(){
		try {
			String path="D:\\Project\\InterSpider\\data\\Page1.htm";
			Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(path);
			TreeModelXml model=new TreeModelXml(doc);
			tree.setModel(model);
			JScrollPane jsp=new JScrollPane(tree);
			setLayout(new BorderLayout());
			add(jsp,BorderLayout.CENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class TreeModelXml implements TreeModel{
		Node root;
		public TreeModelXml(Node doc){
			root=doc;
		}
		@Override
		public void addTreeModelListener(TreeModelListener l) {
		}

		@Override
		public Object getChild(Object parent, int index) {
			Node parentNode=(Node)parent;
			return parentNode.getChildNodes().item(index);
		}

		@Override
		public int getChildCount(Object parent) {
			Node parentNode=(Node)parent;
			return parentNode.getChildNodes().getLength();
		}

		@Override
		public int getIndexOfChild(Object parent, Object child) {
			Node parentNode=(Node)parent;
			NodeList nl=parentNode.getChildNodes();
			for(int i=0;i<nl.getLength();i++){
				if(nl.equals(child))return i;
			}
			return -1;
		}

		@Override
		public Object getRoot() {
			return root;
		}

		@Override
		public boolean isLeaf(Object node) {
			Node parentNode=(Node)node;
			return parentNode.getAttributes()==null || parentNode.getAttributes().getLength()==0;
		}

		@Override
		public void removeTreeModelListener(TreeModelListener l) {
		}

		@Override
		public void valueForPathChanged(TreePath path, Object newValue) {
		}
	}
}
