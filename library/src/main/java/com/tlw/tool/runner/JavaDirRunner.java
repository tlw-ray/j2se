package com.tlw.tool.runner;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
//用来快速浏览某目录下的jar,class,java,并提供直接运行它们的方式
public class JavaDirRunner extends JFrame{
	private static final long serialVersionUID = 1L;
	public JavaDirRunner() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JavaDirRunner r = new JavaDirRunner();
        r.setSize(500,500);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        r.setVisible(true);
    }

    private void jbInit() throws Exception {
        titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                white, new Color(148, 145, 140)), "Java");
        jPanel2.setLayout(borderLayout1);
        jPanel3.setLayout(borderLayout3);
        jPanel1.setLayout(borderLayout2);
        jPanel4.setBorder(BorderFactory.createEtchedBorder());
        jPanel4.setLayout(borderLayout5);
        jPanel6.setBorder(BorderFactory.createEtchedBorder());
        jPanel6.setLayout(borderLayout7);
        jPanel7.setBorder(BorderFactory.createEtchedBorder());
        jPanel7.setLayout(borderLayout8);
        jTextArea1.setPreferredSize(new Dimension(62, 200));
        jPanel8.setLayout(borderLayout4);
        jPanel8.setPreferredSize(new Dimension(200, 109));
        jPanel2.setPreferredSize(new Dimension(200, 132));
        jPanel5.setBorder(BorderFactory.createEtchedBorder());
        jPanel5.setLayout(borderLayout6);
        jLabel1.setText("Path:");
        jPanel3.setPreferredSize(new Dimension(62, 120));
        jLabel2.setText("Console:");
        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jPanel1.add(jTextField1, java.awt.BorderLayout.CENTER);
        jPanel1.add(jButton1, java.awt.BorderLayout.EAST);
        jPanel1.add(jLabel1, java.awt.BorderLayout.WEST);
        this.getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);
        jScrollPane1.getViewport().add(jList1);
        jPanel3.add(jTextArea1, java.awt.BorderLayout.CENTER);
        jPanel8.add(jTabbedPane1, java.awt.BorderLayout.CENTER);
        jTabbedPane1.add(jPanel5, "Jar");
        jTabbedPane1.add(jPanel4, "Manifest");
        jTabbedPane1.add(jPanel6, "Class");
        jTabbedPane1.add(jPanel7, "Java");
        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        jPanel5.add(jScrollPane3, java.awt.BorderLayout.CENTER);
        jPanel6.add(jScrollPane4, java.awt.BorderLayout.CENTER);
        jPanel7.add(jScrollPane5, java.awt.BorderLayout.CENTER);
        jPanel3.add(jLabel2, java.awt.BorderLayout.NORTH);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        this.getContentPane().add(jPanel8, java.awt.BorderLayout.EAST);
        jButton1.setText("Open");
    }

    JPanel jPanel1 = new JPanel();
    JButton jButton1 = new JButton();
    JPanel jPanel2 = new JPanel();
    JScrollPane jScrollPane1 = new JScrollPane();
    JList jList1 = new JList();
    BorderLayout borderLayout1 = new BorderLayout();
    JPanel jPanel3 = new JPanel();
    TitledBorder titledBorder1 = new TitledBorder("");
    BorderLayout borderLayout2 = new BorderLayout();
    JTextField jTextField1 = new JTextField();
    JLabel jLabel1 = new JLabel();
    BorderLayout borderLayout3 = new BorderLayout();
    JPanel jPanel8 = new JPanel();
    JTabbedPane jTabbedPane1 = new JTabbedPane();
    JPanel jPanel4 = new JPanel();
    JPanel jPanel5 = new JPanel();
    JPanel jPanel6 = new JPanel();
    JPanel jPanel7 = new JPanel();
    JTextArea jTextArea1 = new JTextArea();
    BorderLayout borderLayout4 = new BorderLayout();
    JLabel jLabel2 = new JLabel();
    JScrollPane jScrollPane2 = new JScrollPane();
    JScrollPane jScrollPane3 = new JScrollPane();
    JScrollPane jScrollPane4 = new JScrollPane();
    JScrollPane jScrollPane5 = new JScrollPane();
    BorderLayout borderLayout5 = new BorderLayout();
    BorderLayout borderLayout6 = new BorderLayout();
    BorderLayout borderLayout7 = new BorderLayout();
    BorderLayout borderLayout8 = new BorderLayout();
}
