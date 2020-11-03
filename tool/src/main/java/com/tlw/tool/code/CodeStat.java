package com.tlw.tool.code;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
public class CodeStat extends JFrame{
	private static final long serialVersionUID = -3113525838976189473L;
	public CodeStat() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CodeStat c = new CodeStat();
        c.setSize(500,400);
        c.setVisible(true);
    }

    private void jbInit() throws Exception {
        btnSaveHtml.setText("另存为html");
        this.getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        jPanel1.setLayout(borderLayout1);
        jPanel1.add(jtfDir, java.awt.BorderLayout.CENTER);
        jPanel1.add(btnStat, java.awt.BorderLayout.EAST);
        this.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jtaReport);
        this.getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);
        jPanel2.add(btnSaveHtml);

        btnStat.setText("stat");
        this.setTitle("java 代码量统计器");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jtaReport.setContentType("text/html");
        jtaReport.setText("<html>请粘贴入要统计<font color=red>代码行数</font>的目录，点击stat按钮进行统计！<br />谢谢使用。*_*</html>");
        jtfDir.setText("C:");
        btnStat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                output="";
                output+="<html><table border=1><tr backcolor=gray><td>编号</td><td>名称</td><td>信息</td><td>简介</td></tr>";
                stat();
                output+="</table><br>目录数:<font color=red>"+dirCount+"</font><br>总文件数:<font color=red>"+fileCount+"</font><br>总字符数:<font color=red>"+charCount+"</font><br>总行数:<font color=red>"+lineCount+"</font></html>";
                jtaReport.setText(output);
            }
        });
        this.btnSaveHtml.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(jfc.showSaveDialog(CodeStat.this)==JFileChooser.APPROVE_OPTION){
                    FileWriter fw=null;
                    try {
                        fw = new FileWriter(jfc.getSelectedFile());
                        System.out.println(fw.getEncoding());
                        System.out.println(output);
                        fw.write(output);
                        fw.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }finally{
                        if (fw != null) {
                            try {
                                fw.close();
                            } catch (IOException ex1) {
                                ex1.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }
    JFileChooser jfc=new JFileChooser();
    JPanel jPanel1 = new JPanel();
    JButton btnStat = new JButton();
    JTextField jtfDir = new JTextField();
    BorderLayout borderLayout1 = new BorderLayout();
    JScrollPane jScrollPane1 = new JScrollPane();
    JTextPane jtaReport = new JTextPane();
    void stat(){
        File f=new File(jtfDir.getText());
        charCount=0;
        lineCount=0;
        dirCount=0;
        fileCount=0;
        if(f.isDirectory()){
            throwDir(f);
        }
    }
    int charCount;
    int lineCount;
    int dirCount;
    int fileCount;
    int id;
    String output="";
    JPanel jPanel2 = new JPanel();
    JButton btnSaveHtml = new JButton();
    void throwDir(File dir){
        dirCount++;
        output+="<tr><td>["+(id++)+"]</td><td><font color=blue>目录:/"+dir.getName()+"</font></td><td></td><td></td></tr>";
        File[] files=dir.listFiles();
        for(File f:files){
            if(f.isDirectory())throwDir(f);
            else if(isCodeFile(f)){
                fileCount++;
                FileReader fr=null;
                BufferedReader br=null;
                try {
                    fr= new FileReader(f);
                    br=new BufferedReader(fr);
                    String line=null;
                    int fileCharCount=0;
                    int fileLineCount=0;
                    while((line=br.readLine())!=null){
                        fileCharCount+=line.length();
                        fileLineCount++;
                    }
                    charCount+=fileCharCount;
                    lineCount+=fileLineCount;
                    output+="<tr><td>["+(id++)+"]</td><td><font color=green>"+f.getName()+"</font></td><td>字符数 ["+fileCharCount+"],行数 ["+fileLineCount+"]</td><td></td></tr>";
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }finally{
                    try {
                        if(br!=null)br.close();
                    } catch (IOException ex1) {
                    }
                    try {
                        if(fr!=null)fr.close();
                    } catch (IOException ex2) {
                    }
                }
            }
        }
    }
    boolean isCodeFile(File f){
        String fileName=f.getName();
        int idx=fileName.lastIndexOf(".");
        String ext=fileName.substring(idx+1);
        if(ext.equalsIgnoreCase("java") ||
           ext.equalsIgnoreCase("jsp") ||
           ext.equalsIgnoreCase("property") ||
           ext.equalsIgnoreCase("xml") ||
           ext.equalsIgnoreCase("mf"))
            return true;
        else
            return false;
    }
}
