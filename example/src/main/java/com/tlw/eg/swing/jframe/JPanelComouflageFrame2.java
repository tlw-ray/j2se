package com.tlw.eg.swing.jframe;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UnsupportedLookAndFeelException;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-19
@version:2009-6-19
Description:
 */
public class JPanelComouflageFrame2 extends JPanel{
	private static final long serialVersionUID = 1088762204659694026L;
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		JPanelComouflageFrame2 b=new JPanelComouflageFrame2();
		Shower.show(b);
		b.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		System.out.println("ok");
		b.updateUI();
	}
	JRootPane pane1=new JRootPane();
	public JPanelComouflageFrame2(){
		setLayout(null);
		
		pane1.setLayout(new BorderLayout());
		pane1.setBackground(Color.red);
		pane1.setBounds(50, 70, 150, 100);
		pane1.setWindowDecorationStyle(JRootPane.PLAIN_DIALOG );
		pane1.setOpaque(true);
		pane1.add(new JLabel("aaa"));
		pane1.setBorder(BorderFactory.createRaisedBevelBorder());

		add(pane1);
	}
}
