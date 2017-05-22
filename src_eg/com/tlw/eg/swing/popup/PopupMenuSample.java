package com.tlw.eg.swing.popup;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:
 ********************************/
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.MenuSelectionManager;
import javax.swing.table.DefaultTableModel;
 
 
public class PopupMenuSample extends JFrame {
	private static final long serialVersionUID = 1L;
	JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();
 
    JPopupMenu popup = new JPopupMenu();
    JMenuItem item;
 
    private String[] columnNames = {"Type", "Address"};
    private Object[][] data;
    private DefaultTableModel model = new DefaultTableModel(data, columnNames);
 
    BorderLayout borderLayout1 = new BorderLayout();
 
 
    public PopupMenuSample() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    public static void main(String args[]) {
        PopupMenuSample frame = new PopupMenuSample();
        frame.pack();
        frame.setSize(250, 150);
        frame.setVisible(true);
    }
 
    private void jbInit() throws Exception {
        this.getContentPane().setLayout(borderLayout1);
        scrollPane.getViewport().add(table);
        this.getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
 
        //Adds Data to Model
        table.setModel(model);
        Object[] data = {"One", "Two", "Three"};
        model.addRow(data);
        Object[] data1 = {"Test One", "Test Two", "Test Three"};
        model.addRow(data1);
 
        // Adds Item to Popup menu
        popup.add(item = new JMenuItem("Day One"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        popup.add(item = new JMenuItem("Day Two"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        popup.add(item = new JMenuItem("Day Three"));
        item.setHorizontalTextPosition(JMenuItem.LEFT);
        popup.addSeparator();
        popup.add(item = new JMenuItem("New . . ."));
        
        popup.addMouseListener(new MouseAdapter() {
            public void mouseExited(MouseEvent e) {
                if (!popup.getBounds().contains(e.getPoint())) {
                    MenuSelectionManager.defaultManager().clearSelectedPath();
                    //popup.setVisible(false);
                }
            }
        });
 
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedColumn() == 0) {
                    Rectangle rect = table.getCellRect(table.getSelectedRow(),
                            0, true);
                    Point pnt = rect.getLocation();
                    double douX = pnt.getX();
                    double douY = pnt.getY();
                    int intX = (int) douX;
                    int intY = (int) douY;
                    popup.show(table, intX + -45, intY - 50);
                    popup.grabFocus();
                    if (e.getClickCount() == 2) {
 
                    }
                }
            }
        });
    }
 
}
