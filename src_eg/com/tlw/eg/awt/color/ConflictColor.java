// Created on 2008-7-8
package com.tlw.eg.awt.color;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class ConflictColor {
    //存在问题，似乎理论上的互补色，红色应对应绿色。
    //应根据理论知识对此程序进行改进。
    public static void main(String[] args) {
        //Validation
        JPanel pane=new JPanel();
        Color[] colors=new Color[]{
            new Color(0,0,0),
            new Color(0,0,255),
            new Color(0,255,0),
            new Color(255,0,0),
            new Color(32,32,32),
            new Color(64,0,0),
            new Color(0,64,0),
            new Color(0,0,64)
        };
        Border border=BorderFactory.createBevelBorder(1);
        for(Color color:colors){
            Color rColor=getConfilictColor(color);
            JLabel label1=new JLabel();
            label1.setText(color.toString());
            label1.setOpaque(true);
            label1.setBackground(color);
            label1.setForeground(rColor);
            label1.setBorder(border);
            
            JLabel label2=new JLabel();
            label2.setText(color.toString());
            label2.setOpaque(true);
            label2.setBackground(rColor);
            label2.setForeground(color);
            label2.setBorder(border);
            
            pane.add(label1);
            pane.add(label2);
        }
        GridLayout gLayout=new GridLayout(colors.length,2);
        pane.setLayout(gLayout);
        JFrame f=new JFrame();
        f.setSize(600,400);
        JScrollPane scroll=new JScrollPane(pane);
        f.add(scroll,"Center");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    public static Color getConfilictColor(Color color){
        int r=color.getRed();
        int g=color.getGreen();
        int b=color.getBlue();
        return new Color(255-r,255-g,255-b);
    }
}
