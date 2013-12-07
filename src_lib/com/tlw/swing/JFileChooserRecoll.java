package com.tlw.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
//自动记忆上次打开的文件路径的JFileChooser;
//记录于默认路径下指定文件名的xml格式properties文件中；
public class JFileChooserRecoll extends JFileChooser{
	private static final long serialVersionUID = 3950411075448559577L;
	public static final String DefaultConfigFileName="jfileChooser.xml";
    File configFile;
    String key="CURRENT_PATH";
    Properties properties;
    public JFileChooserRecoll(){
        this(DefaultConfigFileName);
    }
    public JFileChooserRecoll(String configFileName){
        super();
        configFile=new File(configFileName);
        properties=new Properties();
        if(configFile.exists()){
            try {
                properties.loadFromXML(new FileInputStream(configFile));
                String path=properties.get(key).toString();
                setSelectedFile(new File(path));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void approveSelection(){
        super.approveSelection();
        save();
    }
    public void save(){
        properties.setProperty(key,getSelectedFile().getPath());
        try {
            properties.storeToXML(new FileOutputStream(configFile), "");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        final JFrame f=new JFrame();
        JButton btn=new JButton("打开");
        final JFileChooserRecoll cho=new JFileChooserRecoll(JFileChooserRecoll.DefaultConfigFileName);
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(cho.showOpenDialog(f)==JFileChooser.APPROVE_OPTION){
                    cho.save();
                }
            }
        });
        f.add(btn);
        f.setSize(500,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
