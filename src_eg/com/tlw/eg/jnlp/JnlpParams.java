package com.tlw.eg.jnlp;

import javax.swing.JFrame;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-18
@version:2009-5-18
Description:
 */
public class JnlpParams extends JFrame{
	private static final long serialVersionUID = -7289677066244238907L;
	public static void main(String[] args) {
		JnlpParams f=new JnlpParams();
		f.setVisible(true);
	}
	public JnlpParams(){
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
