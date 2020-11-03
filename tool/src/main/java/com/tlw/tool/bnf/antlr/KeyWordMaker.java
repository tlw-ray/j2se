package com.tlw.tool.bnf.antlr;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-15
@version:2008-12-15
Descript:关键字的处理，生成antlr中g文件中大小写无关的关键字定义。
 */
public class KeyWordMaker {
	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setTitle("antlr大小写无关关键字定义器：");
		
		JLabel jlabelInput=new JLabel("关键字:");
		final JTextField jtfInput=new JTextField();
		JLabel jlabelOutput=new JLabel("定义:");
		final JTextArea jtaOutput=new JTextArea();
		JScrollPane jscrollOutput=new JScrollPane(jtaOutput);
		
		GridBagLayout gbl=new GridBagLayout();
		frame.setLayout(gbl);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.weightx=0.1;
		gbc.anchor=GridBagConstraints.WEST;
		frame.add(jlabelInput,gbc);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridy=1;
		frame.add(jtfInput,gbc);
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridy=2;
		frame.add(jlabelOutput,gbc);
		gbc.gridy=3;
		gbc.weighty=0.1;
		gbc.fill=GridBagConstraints.BOTH;
		frame.add(jscrollOutput,gbc);
		
		jtfInput.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e) {refresh();}
			public void insertUpdate(DocumentEvent e) {refresh();}
			public void removeUpdate(DocumentEvent e) {refresh();}
			void refresh(){
				String strIn=jtfInput.getText();
				String strInUp=strIn.toUpperCase();
				String strInLow=strIn.toLowerCase();
				String strOut=strIn.toUpperCase()+" : SPACE*";
				for(int i=0;i<strIn.length();i++){
					strOut+=" ( '"+strInUp.charAt(i)+"' | '"+strInLow.charAt(i)+"' ) ";
				}
				strOut+="SPACE+ ;";
				jtaOutput.setText(strOut);
				jtaOutput.selectAll();
				//StringSelection ss=new StringSelection(strOut);
				//Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			}
		});
		frame.setVisible(true);
	}
}
