package com.tlw.eg.awt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
/*使组件可以被鼠标拖动
作者:tlw_ray@163.com
 2007-8-6
*/
public class MouseDrager implements MouseListener,MouseMotionListener{
    private Point origin = new Point();
    private Component component;
    public MouseDrager(Component comp) {
        component = comp;
        comp.addMouseListener(this);
        comp.addMouseMotionListener(this);
    }
    public void mousePressed(MouseEvent e) {
        origin = e.getPoint();
    }
    public void mouseDragged(MouseEvent e) {
        Point p = component.getLocation();
        Point location = new Point(p.x + e.getX() - origin.x,
                                   p.y + e.getY() - origin.y);
        component.setLocation(location);
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    //Example
    public static void main(String[] args){
        Frame frame=new Frame();
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setLayout(null);
        Panel pane=new Panel(){
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g){
				super.paint(g);
                g.drawString("DragAble Panel!",10,20);
            }
        };
        pane.setLayout(new BorderLayout());
        pane.add(new JButton("按钮"),"South");
        pane.setBounds(30,30,150,150);
        pane.setBackground(Color.gray);
        new MouseDrager(pane);
        frame.add(pane);
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}