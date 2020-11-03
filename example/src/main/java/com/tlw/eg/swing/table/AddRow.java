package com.tlw.eg.swing.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年9月18日
 */
public class AddRow {

	public static void main(String[] args) {
		final DefaultTableModel model=new DefaultTableModel();
		model.addColumn("xx");
		JTable table=new JTable(model);
		JButton btn=new JButton("Add");
		JPanel pane=new JPanel();
		pane.add(table);
		pane.add(btn);
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[]{"aa"});
			}
		});
		UtilUi.showPack(pane, "");
		
	}

}
