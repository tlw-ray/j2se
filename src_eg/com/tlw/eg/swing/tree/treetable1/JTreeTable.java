package com.tlw.eg.swing.tree.treetable1;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeSelectionModel;

import com.tlw.eg.swing.tree.treetable.AbstractCellEditor;
import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-14
@version:2009-7-14
Description:最简化的树表
*/
public class JTreeTable extends JPanel {
	private static final long serialVersionUID = -4901959885124475812L;
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		JTreeTable pane=new JTreeTable();
		Shower.show(pane);
	}
	JTable table=new JTable(){
		private static final long serialVersionUID = 4090281543230188365L;
		public int getEditingRow() {
			//解决Editor刷新问题
			return (getColumnClass(editingColumn) == JTree.class) ? -1:editingRow;
		}
	};
	TreeTableCellRenderer tree=new TreeTableCellRenderer();
	TreeTableModel treeTableModel=new TreeTableModel();
	public JTreeTable(){
		int rowHeight=20;
		tree.setRowHeight(rowHeight);

		table.setModel(treeTableModel);
		table.setRowHeight(rowHeight);
		table.setDefaultRenderer(JTree.class, tree);
		table.setDefaultEditor(JTree.class, new TreeTableCellEditor());
		table.setShowGrid(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				tree.setSelectionRow(e.getY()/table.getRowHeight());
			}
		});
		setLayout(new BorderLayout());
		add(new JScrollPane(table),BorderLayout.CENTER);
	}
	public class TreeTableModel extends AbstractTableModel{
		private static final long serialVersionUID = 3579787268304924817L;
		public TreeTableModel(){
			tree.addTreeExpansionListener(new TreeExpansionListener() {
			    public void treeExpanded(TreeExpansionEvent event) {  
			    	fireTableDataChanged(); 
			    }
		        public void treeCollapsed(TreeExpansionEvent event) {  
		        	fireTableDataChanged(); 
			    }
			});
			tree.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					tree.setSelectionRow(e.getY()/tree.getRowHeight());
				}
			});
		}
		@Override
		public int getColumnCount() {
			return 3;
		}
		@Override
		public int getRowCount() {
			return tree.getRowCount();
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if(columnIndex==0) return this;
			if(columnIndex==1) return tree.getPathForRow(rowIndex).getLastPathComponent().toString();
			return rowIndex;
		}
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	    	if(columnIndex==0)return true;
	    	return false;
	    }
	    public Class<?> getColumnClass(int columnIndex) {
	    	if(columnIndex==0)return JTree.class;
	    	if(columnIndex==1)return String.class;
	    	else return Integer.class;
	    }
	}
	public class TreeTableCellRenderer extends JTree implements TableCellRenderer{
		private static final long serialVersionUID = 6476562847009672387L;
		protected int visibleRow;
		public TreeTableCellRenderer(){
			//使table和tree拥有相同的选中
			setSelectionModel(new DefaultTreeSelectionModel() {
				private static final long serialVersionUID = -2835043709389611331L;
				{table.setSelectionModel(listSelectionModel);}
			});
		}
		public void setBounds(int x, int y, int w, int h) {
			super.setBounds(x, 0, w, table.getHeight());
		}
		public void paint(Graphics g) {
			g.translate(0, -visibleRow * getRowHeight());
			super.paint(g);
		}
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			if (isSelected)
				setBackground(table.getSelectionBackground());
			else
				setBackground(table.getBackground());
			visibleRow = row;
			return this;
		}
	}
	public class TreeTableCellEditor extends AbstractCellEditor implements
			TableCellEditor {
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int r, int c) {
			return tree;
		}
	}
}