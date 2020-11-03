package com.tlw.eg.swing.toolbar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
@Author liwei.tang@magustek.com
@Since 2013-7-22
JToolBar和JPanel的区别在于当空间不足以显示内部组件时；
JPanel会隐藏，JToolBar会显示；
但都不会因此改变自身的高度；
 */
public class MultiToolBar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JToolBar jtoolBar01=new JToolBar();
		JToolBar jtoolBar02=new JToolBar();
		
		JButton b1=new JButton("b1");
		
		b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Hello");
			}
		});
		
		jtoolBar01.add(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			{
				putValue(NAME,"AA");
			}
			public void actionPerformed(ActionEvent e){
				
			}
		});
		jtoolBar01.add(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			{
				putValue(NAME,"BB");
			}
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		jtoolBar02.add(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			{
				putValue(NAME,"CC");
			}
			public void actionPerformed(ActionEvent e){
				
			}
		});
		jtoolBar02.add(new AbstractAction(){
			private static final long serialVersionUID = 1L;
			{
				putValue(NAME,"DD");
			}
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
//		JPanel jpanelToolBar=new JPanel();
//		jpanelToolBar.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		JToolBar jpanelToolBar=new JToolBar();
		
		jpanelToolBar.add(b1);
		jpanelToolBar.add(jtoolBar01);
		jpanelToolBar.add(jtoolBar02);
		
		//注意：这一句会去掉JToolBar的拖动柄
		jpanelToolBar.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		
		frame.add(jpanelToolBar, BorderLayout.NORTH);
		frame.add(new JPanel(), BorderLayout.CENTER);
		
		frame.addComponentListener(new ComponentListener(){
			@Override
			public void componentResized(ComponentEvent e) {
				//期望通过重新布局，发生工具栏变高，实际上没有发生。
				frame.doLayout();
				frame.validate();
			}
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		frame.setSize(400, 300);
		frame.setVisible(true);
	}

}
