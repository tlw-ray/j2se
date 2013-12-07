/* (swing1.1beta3) */
package com.tlw.swing.table.cn;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/** ColumnGroup.
 @author Nobuo Tamemasa
 @version 1.0 10/20/98
 */
class ColumnGroup {
	
	protected TableCellRenderer renderer;

	protected Vector v;

	protected String text;

	protected int margin = 0;

	public ColumnGroup(String text) {
		this(null, text);
	}

	public ColumnGroup(TableCellRenderer renderer, String text) {
		if (renderer == null) {
			this.renderer = new DefaultTableCellRenderer() {
				private static final long serialVersionUID = 8230797500365256194L;

				public Component getTableCellRendererComponent(JTable table,
						Object value, boolean isSelected, boolean hasFocus,
						int row, int column) {
					JTableHeader header = table.getTableHeader();
					if (header != null) {
						setForeground(header.getForeground());
						setBackground(header.getBackground());
						setFont(header.getFont());
					}
					setHorizontalAlignment(JLabel.CENTER);
					setText((value == null) ? "" : value.toString());
					setBorder(UIManager.getBorder("TableHeader.cellBorder"));
					return this;
				}
			};
		} else {
			this.renderer = renderer;
		}
		this.text = text;
		v = new Vector();
	}

	/**
	 * param obj TableColumn or ColumnGroup
	 */
	public void add(Object obj) {
		if (obj == null) {
			return;
		}
		v.addElement(obj);
	}

	/**
	 * param c TableColumn 
	 * param v ColumnGroups
	 */
	public Vector getColumnGroups(TableColumn c, Vector g) {
		g.addElement(this);
		if (v.contains(c))
			return g;
		Enumeration em = v.elements();
		while (em.hasMoreElements()) {
			Object obj = em.nextElement();
			if (obj instanceof ColumnGroup) {
				Vector groups = (Vector) ((ColumnGroup) obj).getColumnGroups(c,
						(Vector) g.clone());
				if (groups != null)
					return groups;
			}
		}
		return null;
	}

	public TableCellRenderer getHeaderRenderer() {
		return renderer;
	}

	public void setHeaderRenderer(TableCellRenderer renderer) {
		if (renderer != null) {
			this.renderer = renderer;
		}
	}

	public Object getHeaderValue() {
		return text;
	}

	public Dimension getSize(JTable table) {
		Component comp = renderer.getTableCellRendererComponent(table,
				getHeaderValue(), false, false, -1, -1);
		int height = comp.getPreferredSize().height;
		int width = 0;
		Enumeration em = v.elements();
		while (em.hasMoreElements()) {
			Object obj = em.nextElement();
			if (obj instanceof TableColumn) {
				TableColumn aColumn = (TableColumn) obj;
//				if(GroupHeader.EMPTY_LABEL.equals(aColumn.getHeaderValue())){
//					height=comp.getPreferredSize().height+aColumn.getHeaderRenderer()
//						.getTableCellRendererComponent(table,
//								getHeaderValue(), false, false, -1, -1).getPreferredSize().height;
//					//height=
//				}
				width += aColumn.getWidth();
				width += margin;
			} else {
				width += ((ColumnGroup) obj).getSize(table).width;
			}
		}
		return new Dimension(width, height);
	}

	public void setColumnMargin(int margin) {
		this.margin = margin;
		Enumeration em = v.elements();
		while (em.hasMoreElements()) {
			Object obj = em.nextElement();
			if (obj instanceof ColumnGroup) {
				((ColumnGroup) obj).setColumnMargin(margin);
			}
		}
	}
}
