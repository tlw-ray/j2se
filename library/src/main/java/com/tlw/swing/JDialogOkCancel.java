package com.tlw.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-6
@version:2009-8-6
Description:
带有OK,CANCEL按钮的模式对话框，
通过方法getOption来确认用户点击的是JOptionPane.OK还是JOptionPane.CANCEL
此对话框的目的是避免JOptionPane显示的对话框中，OK按钮的回车调用。

数据一览项目中用到，用于呈现列选择面板。
 */
public class JDialogOkCancel extends JDialog implements ActionListener,ComponentListener{
	private static final long serialVersionUID = 6534023045628785626L;
	public static void main(String[] args) {
		JDialogOkCancel pane=new JDialogOkCancel(null,"hi");
		pane.pack();
		pane.setVisible(true);
		System.out.println(pane.getOpation());
	}
	JButton jbuttonOk=new JButton("OK");
	JButton jbuttonCancel=new JButton("Cancel");
	public JDialogOkCancel(Frame root,Object message){
		super(root);
		Component comp;
		if(message instanceof Component){
			comp=(Component)message;
		}else{
			JLabel jlabel=new JLabel(message.toString());
			comp=jlabel;
		}
		
		JPanel jpanelSouth=new JPanel();
		jpanelSouth.setLayout(new GridBagLayout());
		JPanel placeHolder1=new JPanel();
		JPanel placeHolder2=new JPanel();
		JPanel placeHolder3=new JPanel();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(10,10,10,10);
		gbc.gridx=1;
		jpanelSouth.add(jbuttonOk,gbc);
		gbc.gridx=3;
		jpanelSouth.add(jbuttonCancel,gbc);
		gbc.gridx=0;
		gbc.fill=GridBagConstraints.BOTH;
		jpanelSouth.add(placeHolder1,gbc);
		gbc.gridx=2;
		jpanelSouth.add(placeHolder2,gbc);
		gbc.gridx=4;
		jpanelSouth.add(placeHolder3,gbc);
		
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(comp,BorderLayout.CENTER);
		getContentPane().add(jpanelSouth,BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(root);
		
		jbuttonOk.addActionListener(this);
		jbuttonCancel.addActionListener(this);
	}
	int buttonClicked=JOptionPane.CANCEL_OPTION;
	public int getOpation(){
		return buttonClicked;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(jbuttonOk))
			buttonClicked=JOptionPane.OK_OPTION;
		else
			buttonClicked=JOptionPane.CANCEL_OPTION;
		setVisible(false);
	}
	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentResized(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {
		int maxWidth=jbuttonOk.getWidth()>jbuttonCancel.getWidth()?jbuttonOk.getWidth():jbuttonCancel.getWidth();
		jbuttonOk.setBounds(jbuttonOk.getLocation().x, jbuttonOk.getLocation().y, maxWidth, jbuttonOk.getHeight());
		jbuttonCancel.setBounds(jbuttonCancel.getLocation().x, jbuttonCancel.getLocation().y, maxWidth, jbuttonCancel.getHeight());
	}
}