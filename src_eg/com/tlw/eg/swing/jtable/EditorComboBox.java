package com.tlw.eg.swing.jtable;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.tlw.util.UtilUi;


/**
 * @Author: 唐力伟 (tlw_ray@163.com)
 * @since:2009-8-5
@version:2009-8-5
Description:
 */
public class EditorComboBox extends JPanel{
	private static final long serialVersionUID = -4348571972468043728L;

	public static void main(String[] args){
		EditorComboBox pane=new EditorComboBox();
		UtilUi.show(pane, 400, 300, "");
	}
	public EditorComboBox() {
		JTable table = new JTable();
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		// Add some columns
		model.addColumn("A", new Object[] { "item1" });
		model.addColumn("B", new Object[] { "item2" });

		// These are the combobox values
		String[] values = new String[] { "item1", "item2", "item3" };

		// Set the combobox editor on the 1st visible column
		int vColIndex = 0;
		TableColumn col = table.getColumnModel().getColumn(vColIndex);
		col.setCellEditor(new MyComboBoxEditor(values));

		// If the cell should appear like a combobox in its
		// non-editing state, also set the combobox renderer
		col.setCellRenderer(new MyComboBoxRenderer(values));
		setLayout(new BorderLayout());
		add(table,BorderLayout.CENTER);
	}

	public class MyComboBoxRenderer extends JComboBox implements
			TableCellRenderer {
		private static final long serialVersionUID = -4308315201510715411L;

		public MyComboBoxRenderer(String[] items) {
			super(items);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected) {
				setForeground(table.getSelectionForeground());
				super.setBackground(table.getSelectionBackground());
			} else {
				setForeground(table.getForeground());
				setBackground(table.getBackground());
			}

			// Select the current value
			setSelectedItem(value);
			return this;
		}
	}

	public class MyComboBoxEditor extends DefaultCellEditor {
		private static final long serialVersionUID = -7989025308617798456L;
		public MyComboBoxEditor(String[] items) {
			super(new JComboBox(items));
		}
	}
}
