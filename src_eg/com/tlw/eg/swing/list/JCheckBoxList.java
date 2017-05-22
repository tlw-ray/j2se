package com.tlw.eg.swing.list;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.tlw.util.UtilUi;


/**
@since 2010-9-19
@version 2010-9-19
@author 唐力伟 (tlw_ray@163.com)
 */
public class JCheckBoxList extends JList {
	private static final long serialVersionUID = -3899857613336960259L;
	public static void main(String[] args) {
		JCheckBoxList pane=new JCheckBoxList();
		pane.setListData(new String[]{"aaa","bbb","ccc"});
		pane.setCellRenderer(new ListCheckBoxRenderer());
		UtilUi.show(pane, 300, 400, "");
	}
	static class ListCheckBoxRenderer extends JCheckBox implements ListCellRenderer{
		private static final long serialVersionUID = -3799486120111657955L;
		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			setText(value.toString());
			setSelected(isSelected);
			return this;
		}
	}
}
