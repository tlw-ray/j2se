package com.tlw.ui;
/*
 * Created on 2006-8-29
 * tlw_ray@163.com
 * 可以标示为图标的;
 */
import javax.swing.ImageIcon;
public interface IiconAble {
    public ImageIcon getIcon();
    public ImageIcon getIconBySize(int width,int height);
    public void setIcon(String IconUri);
}
