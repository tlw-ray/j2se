package com.tlw.tool.bnf.antlr;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-5
@version:2008-12-5
Descript:转换BNF语法到接近G语法
 */
public class Bnf2AntlrWindow extends JFrame{
	private static final long serialVersionUID = -4465530931918257295L;
	public static void main(String[] args) {
		JFrame frame=new Bnf2AntlrWindow();
		frame.setVisible(true);
	}
	JTextArea jta=new JTextArea();
	public Bnf2AntlrWindow(){
		setSize(400,300);
		//setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn1=new JButton("ToUpperCase");
		JButton btn3=new JButton("ToLowerCase");
		JScrollPane jscroll=new JScrollPane(jta);
		JButton btn2=new JButton("Convert");
		JButton btn4=new JButton("Clear!");
		GridBagLayout gbl=new GridBagLayout();
		
		setLayout(gbl);
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.gridwidth=4;
		gbc.weighty=0.1;
		gbc.weightx=0.1;
		gbc.fill=GridBagConstraints.BOTH;
		add(jscroll,gbc);
		
		gbc.gridy=1;
		gbc.weighty=0;
		gbc.gridwidth=1;
		gbc.fill=GridBagConstraints.NONE;
		add(btn1,gbc);
		gbc.gridx=1;
		add(btn2,gbc);
		gbc.gridx=2;
		add(btn3,gbc);
		gbc.gridx=3;
		add(btn4,gbc);
		
		//Events
		btn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jta.setText(jta.getText().toUpperCase());
			}
		});
		btn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jta.setText(convert(jta.getText()));
			}
		});
		btn3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jta.setText(jta.getText().toLowerCase());
			}
		});
		btn4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jta.setText("");
			}
		});
	}
	String convert(String content){
		Pattern pattern=null;
		Matcher matcher=null;
		//寻找尖括号中的内容，并替换其中的' '和':'为'_'
		pattern=Pattern.compile("<[^>^\\n]*>");
		matcher=pattern.matcher(content);
		while(matcher.find()){
			String found=matcher.group();
			found=found.replace(' ', '_');
			found=found.replaceAll(":", "_");
			found=found.replaceAll("-", "___");
			found=found.substring(1,found.length()-1);
			content=content.replaceAll(matcher.group(), found);
		}
		
		//为定义尾部加分号，(替换::=与下一个::=或输入结尾之间的回车为分号回车)
		pattern=Pattern.compile("\\n[\\s\\w]+::=");
		matcher=pattern.matcher(content);
		while(matcher.find()){
			String found=matcher.group();
			found=";"+found;
			content.replaceAll(matcher.group(),found);
		}
		content+=";";
		content=content.replaceAll("::=", ":");
		return content;
	}
}
