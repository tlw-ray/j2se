package com.tlw.eg.swing.text;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-7
@version:2009-5-7
Description:
 */
public class FormattedInput {

	public static void main(String[] args) throws ParseException {
		JFrame frame=new JFrame();
		MaskFormatter formatter = new MaskFormatter("###-####");
		JFormattedTextField jftf=new JFormattedTextField(formatter);
		jftf.setColumns(20);
		frame.getContentPane().add(jftf);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
