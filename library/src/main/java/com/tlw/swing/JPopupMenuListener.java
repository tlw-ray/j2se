package com.tlw.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/*******************************
Author:tlw
E-Mail:tlw_ray@163.com
Date:2008-10-21
Description:
 ********************************/
public class JPopupMenuListener extends MouseAdapter {
    JPopupMenu popup;

    public JPopupMenuListener(JPopupMenu popupMenu) {
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