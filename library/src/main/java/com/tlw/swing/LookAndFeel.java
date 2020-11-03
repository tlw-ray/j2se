package com.tlw.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LookAndFeel {
    static String LAF_SYNTH="Synth";
    static String LAF_JGOODIES="jGoodies1.3";
    static String LAF_DEFAULT="Default";
    public static String[][] synthLaf={
                                      {"syntheticaBlackMoon","de.javasoft.plaf.synthetica.SyntheticaBlackMoon"},
                                      {"syntheticaBlackStar","de.javasoft.plaf.synthetica.syntheticaBlackStar"},
                                      {"syntheticaBlueIce","de.javasoft.plaf.synthetica.syntheticaBlueIce"},
                                      {"syntheticaBlueMoon","de.javasoft.plaf.synthetica.syntheticaBlueMoon"},
                                      {"syntheticaBlueSteel","de.javasoft.plaf.synthetica.syntheticaBlueSteel"},
                                      {"syntheticaGreenDream","de.javasoft.plaf.synthetica.syntheticaGreenDream"},
                                      {"syntheticaSilverMoon","de.javasoft.plaf.synthetica.syntheticaSilverMoon"},
    };
    public static String[][] jGoodiesLaf={
    {"Plastic3D","com.jgoodies.looks.plastic.Plastic3DLookAndFeel"},
    {"Plastic","com.jgoodies.looks.plastic.PlasticLookAndFeel"},
    {"PlasticXP","com.jgoodies.looks.plastic.PlasticXPLookAndFeel"}
    };
    public static String[][] defaultLaf={
                                      {"Metal","javax.swing.plaf.metal.MetalLookAndFeel"}
    };

    public static JMenu getDefaultLafMenu(JFrame jframe){
        return getLafMenu(LAF_DEFAULT,jframe);
    }
    public static JMenu getJGoodiesLafMenu(JFrame jframe){
        return getLafMenu(LAF_JGOODIES,jframe);
    }
    public static JMenu getSynthLafMenu(JFrame jframe){
        return getLafMenu(LAF_SYNTH,jframe);
    }
    static JMenu getLafMenu(String lafName,JFrame jframe){
        JMenu menu = new JMenu(lafName);
        String lafs[][]=null;
        if(lafName.equalsIgnoreCase(LAF_JGOODIES))lafs=jGoodiesLaf;
        else if(lafName.equalsIgnoreCase(LAF_SYNTH))lafs=synthLaf;
        else if(lafName.equalsIgnoreCase(LAF_DEFAULT))lafs=defaultLaf;
        LafChangeListener ll = new LafChangeListener(jframe);
        if(lafs!=null){
            for (int i = 0; i < lafs.length; i++) {
                JMenuItem item = new JMenuItem(lafs[i][0]);
                item.setActionCommand(lafs[i][1]);
                item.addActionListener(ll);
                menu.add(item);
            }
        }
        return menu;
    }
    static class LafChangeListener implements ActionListener{
        JFrame _owner;
        public LafChangeListener(JFrame owner){
            _owner=owner;
        }
        public void actionPerformed(ActionEvent e) {
            try {
                System.out.println(e.getActionCommand());
                Class.forName(e.getActionCommand());
                UIManager.setLookAndFeel(e.getActionCommand());
                SwingUtilities.updateComponentTreeUI(_owner);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null,"选中的LookAndFeel并未包含在ClassPath中，请修正ClassPath后重新运行。","错误：",JOptionPane.ERROR_MESSAGE);
            }catch(UnsupportedLookAndFeelException ex){
                JOptionPane.showMessageDialog(null,"不被支持的LookAndFeel。\n详情:"+ex.getMessage(),"错误：",JOptionPane.ERROR_MESSAGE);
            }catch(InstantiationException ex){
                ex.printStackTrace();
            }catch(IllegalAccessException ex){
                ex.printStackTrace();
            }
        }
    }
    //Example:
    public static void main(String[] args){
        JFrame frame=new JFrame();
        JMenuBar menuBar=new JMenuBar();
        frame.setJMenuBar(menuBar);
        menuBar.add(LookAndFeel.getDefaultLafMenu(frame));
        menuBar.add(LookAndFeel.getJGoodiesLafMenu(frame));
        frame.setSize(500,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
