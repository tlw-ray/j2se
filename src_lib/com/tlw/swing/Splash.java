package com.tlw.swing;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
public class Splash extends JWindow{
	private static final long serialVersionUID = 8456273114440566046L;
	static Splash splash;
    public static void main(String[] args) {
        //使用Splash范例
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                splash = new Splash();
                splash.setSize(400,300);
                splash.showInCenter();
            }
        });
        JFrame f=new JFrame();
        Random random=new Random();
        for (int i = 0; i < 5; i++) {
            int k = random.nextInt(3000);
            splash.setInfo("等待" + k + "微妙 ...");
            try {
                Thread.sleep((long) k);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,400);
        f.setVisible(true);
        splash.setVisible(false);
        splash=null;
    }
    JLabel textLabel=new JLabel();
    JLabel imgLabel=new JLabel();
    public Splash(){
        super();
        add(textLabel,BorderLayout.SOUTH);
        add(imgLabel,BorderLayout.CENTER);
    }
    public void showInCenter(){
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize);
        setLocation((screenSize.width-getWidth())/2,(screenSize.height-getHeight())/2);
        setVisible(true);
    }
    public void setInfo(String text){
        textLabel.setText(text);
    }
}
