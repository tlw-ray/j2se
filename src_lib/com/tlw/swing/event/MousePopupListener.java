/*
 * Created on 2006-8-28
 *
 * TODO 当右键点击时处理弹出
 */
package com.tlw.swing.event;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

public class MousePopupListener extends MouseAdapter {
    JPopupMenu popup;
    public MousePopupListener(JPopupMenu po){
        super();
        popup=po;
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
