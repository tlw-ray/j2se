package com.tlw.eg.swing.laf;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalButtonUI;


import com.sun.java.swing.plaf.windows.WindowsButtonUI;
import com.tlw.util.UtilUi;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-22
@version:2009-8-22
Description:
 */
public class DiffButtons extends JPanel {
	private static final long serialVersionUID = -5079361074040851355L;
	public static void main(String[] args) {
		UIManager.installLookAndFeel("Metal", "javax.swing.plaf.metal.MetalLookAndFeel");
//		UIManager.installLookAndFeel("Windows","com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//		UIManager.installLookAndFeel("Nimbus","com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		UtilUi.initUIManager();
		DiffButtons pane=new DiffButtons();
		UtilUi.show(pane, 400, 300, "");
	}
	JButton btnWindows=new JButton("Windows");
	JButton btnMetal=new JButton("Metal");
	JButton btnNimbus=new JButton("Nimbus");
	public DiffButtons(){
		btnWindows.setUI(new WindowsButtonUI());
		btnMetal.setUI(new MetalButtonUI());
		add(btnWindows);
		add(btnMetal);
		add(btnNimbus);
	}
}
