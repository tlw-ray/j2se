package com.tlw.eg.swing.trans_frame;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-1-20
@version:2009-1-20
Description:
 */
public class TransparentFrame extends JFrame {
	public static void main(String[] args) {
		TransparentFrame tf=new TransparentFrame();
		AWTUtilities.setWindowOpaque(tf, false);
		tf.setSize(400,300);
		tf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tf.setVisible(true);
	}
}
