package com.tlw.eg.swing.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import com.tlw.swing.Shower;


/*******************************
Author:tlw_ray
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:input assistant
 ********************************/
public class JTextFieldKindsOfPopup extends JPanel{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		JTextFieldKindsOfPopup wp=new JTextFieldKindsOfPopup();
		Shower.show(wp);
	}
	JTextField jtfWindowPopuup=new JTextField("Edit for Popup JWindow",20);
	JTextField jtfMenuPopup=new JTextField("Edit for Popup JPopupMenu",20);
	JTextField jtfPopup=new JTextField("Edit for PopupFactory Popup",20);
	Window popupWindow=new JWindow();
	JPopupMenu popupMenu=new JPopupMenu();
	Popup popup;
	JPanel popupPane=new JPanel();
	public JTextFieldKindsOfPopup(){
		add(jtfWindowPopuup,"North");
		add(jtfMenuPopup,"South");
		add(jtfPopup,"Center");
		
		//popup window
		popupWindow.setPreferredSize(new Dimension(300,300));
		popupWindow.setBackground(Color.red);
		popupWindow.setBounds(20, 20, 100, 100);
		popupWindow.setAlwaysOnTop(true);
		popupWindow.addWindowListener(new WindowListener());
		jtfWindowPopuup.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				popupWindow.setVisible(true);
				//JWindow dosen't catch windowDeactivated event;
			}
		});
		
		//popup menu
		JMenuItem menuItem1=new JMenuItem("Item1");
		JMenuItem menuItem2=new JMenuItem("Item2");
		JMenuItem menuItem3=new JMenuItem("Item3");
		popupMenu.add(menuItem1);
		popupMenu.add(menuItem2);
		popupMenu.add(menuItem3);
		jtfMenuPopup.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				popupMenu.show(jtfMenuPopup, 0, jtfMenuPopup.getHeight());
				jtfMenuPopup.requestFocus();
				//this way can resolve,input assistant
			}
		});
		
		//popup factory
		popupPane.setBackground(Color.blue);
		popupPane.setPreferredSize(new Dimension(100,100));
		jtfPopup.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				popup=PopupFactory.getSharedInstance().getPopup(JTextFieldKindsOfPopup.this, popupPane, 100, 100);
				popup.show();
				//what event can i set the popup hide()?
				//maybe popupFactory dosen't use for this ,it just use to Tooltip.
			}
		});
		
	}
	class WindowListener extends WindowAdapter{
		@Override
		public void windowDeactivated(WindowEvent e) {
			popupWindow.setVisible(false);
			System.out.println(e);
		}
		public void windowActived(WindowEvent e){
			System.out.println(e);
		}
	}
}
