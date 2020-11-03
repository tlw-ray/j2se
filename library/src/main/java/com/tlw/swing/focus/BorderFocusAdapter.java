// Created on 2008-7-8
package com.tlw.swing.focus;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class BorderFocusAdapter implements FocusListener{
    JComponent jcomp;
    Border orgBorder=BorderFactory.createLineBorder(Color.black);
    Border focusBorder=BorderFactory.createLineBorder(Color.blue);
    public BorderFocusAdapter(JComponent comp){
        jcomp=comp;
        //orgBorder=comp.getBorder();
    }
    public void focusGained(FocusEvent e) {
        jcomp.setBorder(focusBorder);
    }
    public void focusLost(FocusEvent e) {
        jcomp.setBorder(orgBorder);
    }
}
