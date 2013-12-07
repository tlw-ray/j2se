/*
 * Created on 2008-5-7
 */
package com.tlw.swing;

import java.awt.Component;

import javax.swing.JFrame;

public class Shower {
    public static void show(Component comp){
        show(comp,800,600);
    }
    public static void show(Component comp,String title){
    	show(comp,title,800,600);
    }
    public static void show(Component comp,int width,int height){
    	show(comp,"",width,height);
    }
    public static void show(Component comp,String title,int width,int height){
        JFrame f=new JFrame();
        f.setTitle(title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(width,height);
        f.add(comp,"Center");
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
