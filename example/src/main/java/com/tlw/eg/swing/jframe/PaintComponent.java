package com.tlw.eg.swing.jframe;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-19
@version:2009-6-19
Description:
 */
public class PaintComponent extends JPanel {
	private static final long serialVersionUID = 5707115782048114262L;
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
		PaintComponent pane=new PaintComponent();
		Shower.show(pane);
	}
	JButton btn=new JButton("HHI");
	public PaintComponent(){
		setLayout(null);
		btn.setBounds(200,200,60,30);
		btn.setForeground(Color.blue);
		add(btn);
	}
	public void paint(Graphics g){
		super.paint(g);
		btn.paint(g);
	}
}
