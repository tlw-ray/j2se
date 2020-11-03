/*
 * Created on 2006-7-27
 *
 * TODO 一个默认的工具条;类似于Word的菜单模板
 * 可供其他多数类似功能的程序使用;
 */
package com.tlw.swing;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class Menus {
    public JMenuBar jMenuBar_Main = new JMenuBar();

    public JMenu jMenu_File = new JMenu("文件");
    public JMenuItem jMenuItem_Open = new JMenuItem("打开");
    public JMenuItem jMenuItem_Save = new JMenuItem("保存");
    public JMenuItem jMenuItem_SaveAs = new JMenuItem("保存为");
    public JMenuItem jMenuItem_Print=new JMenuItem("打印");
    public JMenuItem jMenuItem_PrintView=new JMenuItem("打印预览");

    public JMenu jMenu_Edit = new JMenu("编辑");
    public JMenuItem jMenuItem_Undo=new JMenuItem("后退");
    public JMenuItem jMenuItem_Redo=new JMenuItem("前进");
    public JMenuItem jMenuItem_Copy=new JMenuItem("拷贝");
    public JMenuItem jMenuItem_Cut=new JMenuItem("剪切");
    public JMenuItem jMenuItem_Paste=new JMenuItem("粘贴");
    public JMenuItem jMenuItem_Find=new JMenuItem("查找");

    public JMenu jMenu_View = new JMenu("视图");
    public JCheckBoxMenuItem jMenuItem_ToolBar=new JCheckBoxMenuItem("工具条");
    public JCheckBoxMenuItem jMenuItem_Property=new JCheckBoxMenuItem("属性面板");
    public JMenuItem jMenuItem_CustomToolBar=new JMenuItem("自定义工具条");

    public JMenu jMenu_Tool = new JMenu("工具");
    public JMenuItem jMenuItem_Settings=new JMenuItem("设定");

    public JMenu jMenu_Help = new JMenu("帮助");
    public JMenuItem jMenuItem_Help = new JMenuItem("帮助(&F1)");
    public JMenuItem jMenuItem_About = new JMenuItem("关于");


    public Menus(){
        //文件菜单；
        jMenuBar_Main.add(jMenu_File);
        jMenu_File.add(jMenuItem_Open);
        jMenu_File.add(jMenuItem_Save);
        jMenu_File.add(jMenuItem_SaveAs);
        jMenu_File.addSeparator();
        jMenu_File.add(jMenuItem_Print);
        jMenu_File.add(jMenuItem_PrintView);
        //编辑菜单
        jMenuBar_Main.add(jMenu_Edit);
        jMenu_Edit.add(jMenuItem_Undo);
        jMenu_Edit.add(jMenuItem_Redo);
        jMenu_Edit.addSeparator();
        jMenu_Edit.add(jMenuItem_Cut);
        jMenu_Edit.add(jMenuItem_Copy);
        jMenu_Edit.add(jMenuItem_Paste);
        jMenu_Edit.addSeparator();
        jMenu_Edit.add(jMenuItem_Find);
        //视图菜单
        jMenu_View.add(jMenuItem_CustomToolBar);
        jMenu_View.addSeparator();
        jMenuBar_Main.add(jMenu_View);
        jMenu_View.add(jMenuItem_ToolBar);
        jMenu_View.add(jMenuItem_Property);
        //工具菜单
        jMenuBar_Main.add(jMenu_Tool);
        jMenu_Tool.add(jMenuItem_Settings);
        //帮助菜单
        jMenu_Help.add(jMenuItem_Help);
        jMenu_Help.addSeparator();
        jMenu_Help.add(jMenuItem_About);
        jMenuBar_Main.add(jMenu_Help);
    }
}
