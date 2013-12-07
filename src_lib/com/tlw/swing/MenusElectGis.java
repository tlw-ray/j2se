/*
 * Created on 2006-7-27
 *
 * TODO: 为多数电力GIS程序准备的默认工具条
 */
package com.tlw.swing;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MenusElectGis extends Menus {
    public JMenu jMenu_Export=new JMenu("导出");
    public JMenuItem jMenuItem_BMP=new JMenuItem("BMP");
    public JMenuItem jMenuItem_PNG=new JMenuItem("PNG");
    public JMenuItem jMenuItem_GIF=new JMenuItem("GIF");
    public MenusElectGis(){
        jMenu_File.addSeparator();
        jMenu_File.add(jMenu_Export);
        jMenu_Export.add(jMenuItem_BMP);
        jMenu_Export.add(jMenuItem_PNG);
        jMenu_Export.add(jMenuItem_GIF);

    }
}
