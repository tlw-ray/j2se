// Created on 2008-7-7
package com.tlw.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.tlw.swing.focus.BorderFocusAdapter;


public class JLabelFocus extends JLabel {
    private static final long serialVersionUID = -7816867360244687120L;
    public static void main(String[] args) {
        JLabelFocus lf=new JLabelFocus();
        JFrame f=new JFrame();
        f.add(lf,"Center");
        JTextField jtf=new JTextField();
        BorderFocusAdapter bf=new BorderFocusAdapter(jtf);
        jtf.addFocusListener(bf);
        f.add(jtf,"North");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400,300);
        f.setVisible(true);
        
    }
    public JLabelFocus(){
        super();
        init();
    }
    public JLabelFocus(String text){
        super(text);
        init();
    }
    private void init(){
        addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                requestFocus();
            }
        });
        BorderFocusAdapter lFocus=new BorderFocusAdapter(this);
        addFocusListener(lFocus);
    }
}
