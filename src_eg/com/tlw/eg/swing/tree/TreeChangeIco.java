package com.tlw.eg.swing.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-11-13
Description:
 ********************************/
public class TreeChangeIco extends JFrame{
	private static final long serialVersionUID = 1605583367656591531L;

	public static void main(String[] args) {
		JFrame f=new JFrame();
		final JTree tree=new JTree();
		JScrollPane scroll=new JScrollPane(tree);
		JButton btn=new JButton("Change!");
		
		btn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				TreeNode tNode=(TreeNode)tree.getModel().getRoot();
				DefaultMutableTreeNode childNode1=(DefaultMutableTreeNode)tNode.getChildAt(1).getChildAt(1);
				childNode1.setUserObject(Math.random());
				tree.repaint();
			}
		});
		
		f.getContentPane().add(btn,"South");
		f.getContentPane().add(scroll);
		f.setSize(400,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
