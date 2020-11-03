package com.tlw.tools.code;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-16
@version:2008-12-16
Descript:制作一个代码注释的方框
 */
/*--------------------------------------------------------------------
|                          =制作一个代码注释的方框=
--------------------------------------------------------------------*/
public class CodeCommentMaker {
	public static void main(String[] args) {
		final JFrame frame=new JFrame();
		frame.setTitle("TLW注释生成器");
		frame.setSize(400,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.setLocationRelativeTo(null);
		/*------------------------------------------------
		|                   =界面组件声明=
		------------------------------------------------*/
		JLabel jlbText=new JLabel("Text:");
		final JTextField jtfText=new JTextField();
		JLabel jlbWidth=new JLabel("Width:");
		SpinnerNumberModel spinnerModel=new SpinnerNumberModel(70,30,200,10);
		final JSpinner jspinnerWidth=new JSpinner(spinnerModel);
		final JTextArea jtaOutput1=new JTextArea();
		JScrollPane jscrollOutput1=new JScrollPane(jtaOutput1);
		JButton jbtnCopy1=new JButton("Copy");
		final JTextArea jtaOutput2=new JTextArea();
		JScrollPane jscrollOutput2=new JScrollPane(jtaOutput2);
		JButton jbtnCopy2=new JButton("Copy");
		
		/*-------------------_界面第一行_-------------------*/
		GridBagConstraints gbc=new GridBagConstraints();
		frame.add(jlbText,gbc);
		gbc.weightx=0.1;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridx=1;
		frame.add(jtfText,gbc);
		gbc.weightx=0;
		gbc.gridx=2;
		gbc.fill=GridBagConstraints.NONE;
		frame.add(jlbWidth,gbc);
		gbc.gridx=3;
		frame.add(jspinnerWidth,gbc);
		/*-------------------_界面第二行_-------------------*/
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weighty=0.1;
		gbc.weightx=0.1;
		gbc.gridwidth=3;
		frame.add(jscrollOutput1,gbc);
		gbc.gridx=3;
		gbc.fill=GridBagConstraints.NONE;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.gridwidth=1;
		frame.add(jbtnCopy1,gbc);
		/*-------------------_界面第三行_-------------------*/
		gbc.gridx=0;
		gbc.gridy=2;
		gbc.fill=GridBagConstraints.BOTH;
		gbc.weightx=0.1;
		gbc.weighty=0.1;
		gbc.gridwidth=3;
		frame.add(jscrollOutput2,gbc);
		gbc.gridx=3;
		gbc.fill=GridBagConstraints.NONE;
		gbc.weightx=0;
		gbc.weighty=0;
		gbc.gridwidth=1;
		frame.add(jbtnCopy2,gbc);
		/*------------------------------------------------
		|                    =事件处理=
		------------------------------------------------*/
		jtfText.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e) {refresh();}
			public void insertUpdate(DocumentEvent e) {refresh();}
			public void removeUpdate(DocumentEvent e) {refresh();}
			public void refresh(){
				String input=jtfText.getText();
				int width=(Integer)jspinnerWidth.getValue();
				/*-------------------_第一种样式_-------------------*/
				String output="/*";
				for(int i=0;i<width-2;i++){
					output+="-";
				}
				output+="\n|";
				String spaces="";
				int spaceWidth=(width-6-input.length())/2;
				for(int i=0;i<spaceWidth;i++){
					spaces+=" ";
				}
				output+=spaces+"="+input+"="+"\n";
				for(int i=0;i<width-2;i++){
					output+="-";
				}
				output+="*/";
				jtaOutput1.setText(output);
				/*-------------------_第二种样式_-------------------*/
				spaces=spaces.replace(' ', '-');
				output="/*"+spaces+"_"+input+"_"+spaces+"*/";
				jtaOutput2.setText(output);
			}
		});
		jbtnCopy1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				doCopy(jtaOutput1.getText());
			}
		});
		jbtnCopy2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				doCopy(jtaOutput2.getText());
			}
		});
		jtfText.setText("注释");
		frame.setVisible(true);
	}
	public static void doCopy(String text){
		StringSelection ss=new StringSelection(text);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, ss);
	}
}
