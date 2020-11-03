/*
 * Created on 2006-8-29
 * tlw_ray@163.com
 * 可以标示为图标的;
 */
package com.tlw.swing.jtree;
import javax.swing.ImageIcon;
public interface IiconAble {
    ImageIcon getIcon();
    ImageIcon getIconBySize(int width,int height);
    void setIcon(String IconUri);
}
