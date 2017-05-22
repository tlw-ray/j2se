package com.tlw.eg.swing.jtable;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-10-19
@version:2009-10-19
Description:输出JTable中所显示的内容
 */
public class JTableOutputItShown extends JPanel {
	private static final long serialVersionUID = -7441303458175468181L;
	JTable jtable=new JTable();
	JScrollPane jscroll=new JScrollPane(jtable);
	JButton jbtnShow=new JButton("输出表格中显示的内容");
	public static void main(String[] args) {
		JTableOutputItShown pane=new JTableOutputItShown();
		UtilUi.show(pane, 400, 300, "");
	}
	public JTableOutputItShown(){
		setLayout(new BorderLayout());
		DefaultTableModelClassed model=new DefaultTableModelClassed(new Object[][]{{new Date()}},new String[]{"时间"});
		jtable.setModel(model);
		jtable.setDefaultRenderer(Date.class, new TableCellRenderer4Date());
		
		jbtnShow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TableModel model=jtable.getModel();
				TableColumnModel columnModel=jtable.getColumnModel();
				int columnCount=columnModel.getColumnCount();
				TableCellRenderer[] renderers=new TableCellRenderer[columnCount];
				int[] modelIndexes=new int[columnCount];
				for(int i=0;i<columnCount;i++){
					TableColumn tableColumn=columnModel.getColumn(i);
					renderers[i]=tableColumn.getCellRenderer();
					modelIndexes[i]=tableColumn.getModelIndex();
				}
				for(int i=0;i<model.getRowCount();i++){
					for(int j=0;j<columnModel.getColumnCount();j++){
						Object value=model.getValueAt(i, j);
						TableCellRenderer renderer=jtable.getDefaultRenderer(model.getColumnClass(j));
						Component comp=renderer.getTableCellRendererComponent(jtable, value, false, false, i, j);
						JLabel label=(JLabel)comp;
						System.out.print(label.getText()+",");
					}
					System.out.println();
				}
			}
		});
		add(jscroll,BorderLayout.CENTER);
		add(jbtnShow,BorderLayout.NORTH);
	}
	class DefaultTableModelClassed extends DefaultTableModel{
		private static final long serialVersionUID = 8610127373802604548L;
		public DefaultTableModelClassed(Object[][] data,Object[] head){
			super(data,head);
		}
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Date.class;
		}
	}
	class TableCellRenderer4Date extends DefaultTableCellRenderer{
		private static final long serialVersionUID = -7788753320736976996L;
		@Override
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
					row, column);
			setText(DateFormat.getDateTimeInstance().format(value));
			return this;
		}
	}
}
