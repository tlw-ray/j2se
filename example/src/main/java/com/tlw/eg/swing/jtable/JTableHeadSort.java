package com.tlw.eg.swing.jtable;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年1月19日
 */
public class JTableHeadSort {

	public static void main(String[] args) {
		
		DefaultTableModel model = new DefaultTableModel(
			new Object[][]{
					{"abc","def"},
					{"123", "ad2"},
					{"zf1", "2ac"},
			},
			new Object[]{"Col1", "Col2"}
		);
		
		JTable jtable = new JTable(model);
		jtable.setRowSorter(new TableRowSorter<DefaultTableModel>(model));
		JScrollPane jscrollPane = new JScrollPane(jtable);
		UtilUi.show(jscrollPane, 600, 400, "");
		
	}

}
