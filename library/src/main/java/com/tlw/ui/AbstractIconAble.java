package com.tlw.ui;

import java.awt.Image;

import javax.swing.ImageIcon;
//部分实现;
public abstract class AbstractIconAble implements IiconAble{
    public ImageIcon getIconBySize(int width,int height){
        Image img=getIcon().getImage();
        img=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
}
