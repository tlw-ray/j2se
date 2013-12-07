package com.tlw.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class UIExtends {
    public static void main(String[] args) {
        //flashComponentExample();
    	setUIFont(new Font("宋体",Font.PLAIN,12));
    }
    public static void flashComponentExample(){
        JFrame frame=new JFrame();
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gl=new GridLayout(5,5,0,10);
        frame.setLayout(gl);
        //Flash Component Example;
        JPanel pane1=new JPanel();
        final JButton btnFlash=new JButton("闪烁");
        final JLabel lb=new JLabel("Hello!");
        final JComboBox cb=new JComboBox(new Object[]{"111","222","333"});
        //lb.setOpaque(true);
        lb.setBorder(BorderFactory.createBevelBorder(1));
        btnFlash.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                flashComponent(lb,lb.getBackground(), Color.yellow, 12, 200);
                flashComponent(btnFlash,btnFlash.getBackground(), Color.red, 6, 400);
                flashComponent(cb,null, Color.blue, 2, 600);
            }
        });
        pane1.setBorder(BorderFactory.createTitledBorder("闪烁控件例子"));
        pane1.add(lb);
        pane1.add(cb);
        pane1.add(btnFlash);
        frame.add(pane1);
        frame.setVisible(true);
    }
	/**
	 * 闪烁某个控件
	 * @param comp	要被闪烁的控件
	 * @param baseColor	组件基本颜色
	 * @param flashColor 组件闪烁颜色
	 * @param count 闪烁次数
	 * @param flashSpanMicrosecond 闪烁时间间隔，时间单位为毫秒
	 */
    public static void flashComponent(final Component comp,final Color baseColor,final Color flashColor,final int count,final int flashSpanMicrosecond){
        if(!comp.isOpaque()){
            String msg="开发期错误:需要闪烁的"+comp.getClass()+"类型对象"+comp.getName()+"是一个透明对象(isOpaque()=false)，闪烁可能不正常。";
            System.err.println(msg);
        }
        new Thread() {
            public void run() {
                //这里 使用baseColor比较安全，最终控件会恢复baseColor;
                //以前的版本中省略掉baseColor,由于控件不是线程安全的，
                //在多个线程同时闪烁控件时就会发生最终恢复颜色不正确的情况.
                Color orgColor;
                if(baseColor==null)
                     orgColor= comp.getBackground();
                else
                    orgColor=baseColor;
                for (int i = 0; i < count * 2; i++) {
                    Color col = comp.getBackground();
                    if(col==orgColor)
                        comp.setBackground(flashColor);
                    else
                        comp.setBackground(orgColor);
                    try {
                        Thread.sleep(flashSpanMicrosecond);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                comp.setBackground(orgColor);
            }
        }.start();
    }
    //设定swing界面的所有字体为。（注意:要在构建窗体之前运行，否则无效）
    public static void setUIFont(Font font) {
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
            	System.out.println(key);
                UIManager.put(key, font);
        }
    }
    public static void changeUIFontSize(int change){
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource){
            	FontUIResource fur=(FontUIResource)value;
            	UIManager.put(key, new FontUIResource(fur.getFontName(),fur.getStyle(),fur.getSize()+change));
            }
        }
    }
    public static void setMetalNoBold(){
        //设定swing界面非bold字体样式。注意：需要在窗体界面加载前运行。
        UIManager.put("swing.boldMetal", Boolean.FALSE);
    }
	public static void showUIDefault(){
		System.out.println("-----<UIDefaults>--------");
		UIDefaults uiDefaults=UIManager.getDefaults();
		Iterator it=uiDefaults.keySet().iterator();
		while(it.hasNext()){
			Object obj=it.next();
			System.out.println(obj+":"+uiDefaults.get(obj));
		}
		System.out.println("-----</UIDefaults>--------");
	}
}
