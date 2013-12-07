package com.tlw.eg.swing.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-21
Description:
 ********************************/
public class PopupMenu extends JFrame{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		PopupMenu f=new PopupMenu();
		f.setVisible(true);
	}
	JPopupMenu pop=new JPopupMenu();
	JMenuItem mi1=new JMenuItem("aa");
	JMenuItem mi2=new JMenuItem("bb");
	JPanel pane=new JPanel();
	public PopupMenu(){
		pane.setBackground(Color.red);
		pane.setPreferredSize(new Dimension(200,200));
		pop.add(mi1);
		pop.add(mi2);
		pop.add(pane);
		
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(new PopupListener(pop));
	}
    class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }
}
