package com.tlw.eg.print.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-23
@version:2009-7-23
Description:
 */
public class PrintUI extends JPanel implements ActionListener{
	private static final long serialVersionUID = 8259027142214038694L;
	public static void main(String[] args) {
		PrintUI pane=new PrintUI();
		Shower.show(pane);
	}
	BufferedImage bi=new BufferedImage(800, 600, BufferedImage.TYPE_3BYTE_BGR);
	JButton jbtnPreview=new JButton("预览");
	JPanel jpanelCenter=new JPanel(){
		private static final long serialVersionUID = -3207817437177653159L;
		public void paint(Graphics g){
			super.paint(g);
			super.paint(bi.getGraphics());
		}
	};
	public PrintUI(){
		jpanelCenter=new JPanel();
		Border border=BorderFactory.createLineBorder(Color.black);
		for(int i=0;i<100;i++){
			JLabel label=new JLabel(i+"");
			label.setPreferredSize(new Dimension(50,20));
			label.setBorder(border);
			jpanelCenter.add(label);
			JTextField jtf=new JTextField(i+"",20);
			jtf.setBorder(border);
			jpanelCenter.add(jtf);
		}
		setLayout(new BorderLayout());
		add(jbtnPreview,BorderLayout.NORTH);
		add(jpanelCenter,BorderLayout.CENTER);
		jbtnPreview.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel pane=new JPanel(){
			private static final long serialVersionUID = -3207817437177653159L;
			public void paint(Graphics g){
				super.paint(g);
				jpanelCenter.printAll(g);
			}
		};
		pane.setPreferredSize(new Dimension(800,600));
		JOptionPane.showMessageDialog(this, pane);
	} 
}
