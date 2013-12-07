package com.tlw.eg.swing;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.Popup;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-21
Description:一个浮动的面板
 ********************************/
public class FloatPanel extends JFrame{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		FloatPanel f=new FloatPanel();
		f.setVisible(true);
	}
	JTextField jtf=new JTextField("",20);
	JPanel pane=new JPanel();
	JButton btn=new JButton("hi");
	PopupManager popManager=new PopupManager();
	JMenuBar mb=new JMenuBar();
	JMenu mu=new JMenu("文件");
	JMenuItem item1=new JMenuItem("Item1");
	JMenuItem item2=new JMenuItem("Item2");
	JPopupMenu popupMenu=new JPopupMenu();
	public FloatPanel(){
		mb.add(mu);
		mu.add(item1);
		mu.add(item2);
		setJMenuBar(mb);
		
		popupMenu.add(pane);
		JMenuItem menu1=new JMenuItem("asdf");
		popupMenu.add(menu1);
		
		add(jtf,"North");
		add(btn,"West");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();

		pane.setPreferredSize(new Dimension(jtf.getWidth(), 200));
		//jtf.addMouseListener(popManager);
		jtf.setComponentPopupMenu(popupMenu);
		System.out.println(jtf.getAccessibleContext());
		System.out.println(this.getAccessibleContext());
		System.out.println(pane.getAccessibleContext());
		System.out.println(popupMenu.getAccessibleContext());
		System.out.println(item1.getAccessibleContext());
	}
	class PopupManager extends MouseAdapter{
		Popup popup;
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
				showPopup();
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
		}
		private void showPopup(){
			popupMenu.show(jtf, jtf.getX(), jtf.getY()+jtf.getHeight());
			popupMenu.setSize(jtf.getWidth(),300);
		}
	}
}
