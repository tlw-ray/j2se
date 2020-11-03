package com.tlw.tools.xml.tree;

import javax.swing.JPanel;
import javax.swing.event.TreeModelListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-19
@version:2009-7-19
Description:
 */
public class HtmlDocumentTree extends JPanel {
	private static final long serialVersionUID = 634001727869386587L;
	public static void main(String[] args) {
		HtmlDocumentTree tree=new HtmlDocumentTree();
		Shower.show(tree);
	}
	public HtmlDocumentTree(){
		String path="D:\\Project\\InterSpider\\data\\Page1.htm";
		
	}
	class TreeModelHtml implements TreeModel{
		HTMLDocument doc;
		@Override
		public void addTreeModelListener(TreeModelListener l) {
		}

		@Override
		public Object getChild(Object parent, int index) {
			return null;
		}

		@Override
		public int getChildCount(Object parent) {
			return 0;
		}

		@Override
		public int getIndexOfChild(Object parent, Object child) {
			return 0;
		}

		@Override
		public Object getRoot() {
			return doc;
		}

		@Override
		public boolean isLeaf(Object node) {
			return false;
		}

		@Override
		public void removeTreeModelListener(TreeModelListener l) {
		}

		@Override
		public void valueForPathChanged(TreePath path, Object newValue) {
		}
	}
}
