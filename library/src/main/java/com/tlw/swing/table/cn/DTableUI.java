package com.tlw.swing.table.cn;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.TableCellRenderer;

class DTableUI extends BasicTableUI {	
	public void paint(Graphics g, JComponent c) {
//		Rectangle r = g.getClipBounds();
//		int firstRow = table.rowAtPoint(new Point(0, r.y));
//		int lastRow = table.rowAtPoint(new Point(0, r.y + r.height));
//		if (lastRow < 0)
//			lastRow = table.getRowCount() - 1;
//		for (int i = firstRow; i <= lastRow; i++)
//			paintRow(i, g);
		for(int i=0;i< table.getRowCount();i++){
			paintRow(i, g);
		}
	}

	private void paintRow(int row, Graphics g) {
		Rectangle clipBounds = g.getClipBounds();
		GridSplit gridSplit = ((DTable) table).getGridSplit();
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (gridSplit.isVisible(row, i)) {
				Rectangle rec = table.getCellRect(row, i, true);
				if (rec.intersects(clipBounds)) {
					paintCell(row, i, g, rec);
				}
			}
		}
	}

	private void paintCell(int row, int column, Graphics g, Rectangle area) {
		int verticalMargin = table.getRowMargin();
		int horizontalMargin = table.getColumnModel().getColumnMargin();

		Color c = g.getColor();
		g.setColor(table.getGridColor());
		g.drawRect(area.x, area.y, area.width - 1, area.height - 1);		
		g.setColor(c);

		area.setBounds(area.x + horizontalMargin / 2, area.y + verticalMargin
				/ 2, area.width - horizontalMargin, area.height
				- verticalMargin);

		if (table.isEditing() && table.getEditingRow() == row
				&& table.getEditingColumn() == column) {
			Component component = table.getEditorComponent();
			component.setBounds(area);
			component.validate();
		} else {
			TableCellRenderer renderer = table.getCellRenderer(row, column);
			Component component = table.prepareRenderer(renderer, row, column);
			if (component.getParent() == null)
				rendererPane.add(component);
			rendererPane.paintComponent(g, component, table, area.x, area.y,area.width, area.height, true);
		}
	}
	
}
