package com.tlw.eg.swing.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JWindow;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-27
Description:
 ********************************/
public class JWindowEvent{
	public static void main(String[] args) {
		WindowListener wl=new FooWindowListener();
		
		JWindow window=new JWindow();
		window.addWindowListener(wl);
		window.setSize(400,400);
		window.setVisible(true);
	}
    static class FooWindowListener implements WindowListener{
		@Override
		public void windowActivated(WindowEvent e) {
			System.out.println(e);
		}
		@Override
		public void windowClosed(WindowEvent e) {
			System.out.println(e);
		}
		@Override
		public void windowClosing(WindowEvent e) {
			System.out.println(e);
		}
		@Override
		public void windowDeactivated(WindowEvent e) {
			System.out.println(e);
		}
		@Override
		public void windowDeiconified(WindowEvent e) {
			System.out.println(e);
		}
		@Override
		public void windowIconified(WindowEvent e) {
			System.out.println(e);
		}
		@Override
		public void windowOpened(WindowEvent e) {
			System.out.println(e);
		}
    }
}
