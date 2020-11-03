package com.tlw.eg.swing.jframe;

import java.awt.Graphics;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;


import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;
import com.tlw.swing.Shower;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-18
@version:2009-6-18
Description:模拟绘制窗体
 */
public class JPanelCamouflageFrame1 extends JPanel{
	private static final long serialVersionUID = -2147173722591016997L;
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(new com.sun.java.swing.plaf.windows.WindowsLookAndFeel());
		JPanelCamouflageFrame1 b=new JPanelCamouflageFrame1();
		Shower.show(b);
	}
	JPanel pane1=new JPanel(){
		private static final long serialVersionUID = 6296978516954533173L;
		JInternalFrame u=new JInternalFrame();
		WindowsInternalFrameTitlePane p=new WindowsInternalFrameTitlePane(u);
		{
			Border border=UIManager.getBorder("InternalFrame.border");
			setBorder(border);
		}
		public void paint(Graphics g){
			super.paint(g);
			p.setBounds(0,0,getWidth(),25);
			p.paintComponent(g);
		}
	};
	public JPanelCamouflageFrame1(){
		setLayout(null);
		add(pane1);
		pane1.setBounds(50, 70, 150, 100);
	}
}
