package com.tlw.swing.jtree;
import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
public class Example extends JApplet {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
        JFrame f = new JFrame() {
			private static final long serialVersionUID = 1L;

			{
                this.setSize(450, 300);
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.add(new ExamplePane("icon/"), BorderLayout.CENTER);
                //this.add(new ExamplePane("/com/tlw/swing/jtree/icon/"), BorderLayout.CENTER);
            }
        };
        f.setVisible(true);
    }
    JTextField input=new JTextField();
    JButton btn=new JButton("计算");
    public void start() {
        this.add(new ExamplePane("/com/tlw/swing/jtree/icon/"), BorderLayout.CENTER);
    }
}
