package com.tlw.eg.byt;

import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-8
Description:
 ********************************/
public class EncodeShower extends JFrame{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		String str="xml   你好   123!！";
		str2byte(str,"GBK");
		str2byte(str,"UTF-8");
		str2byte(str,"UTF-16BE");
		str2byte(str,"UTF-16LE");
		System.out.println(Integer.toHexString(11));
		System.out.println(Integer.toHexString(-11));
		System.out.println(Integer.toHexString(0));
		System.out.println(Integer.toHexString(1));
		System.out.println("-1:"+Integer.toHexString(-1));
		System.out.println(Integer.toHexString(2));
		System.out.println(Integer.toHexString(-2));
		System.out.println("256:"+Integer.toHexString(256));
		System.out.println("255:"+Integer.toHexString(255));
		
		System.out.println("0x79:"+Integer.toHexString(0x79));
		System.out.println("0x80:"+Integer.toHexString(0x80));
		System.out.println("0x81:"+Integer.toHexString(0x81));
		//BOM (byte order mark)
		System.out.println("0xFF:"+Integer.toHexString(0xFF));
		System.out.println("0xFE:"+Integer.toHexString(0xFE));
		System.out.println("0xBB:"+Integer.toHexString(0xBB));
		System.out.println("0xBF:"+Integer.toHexString(0xBF));
		System.out.println("0xEF:"+Integer.toHexString(0xEF));
		System.out.println("------------------");
		/**/
		
		for(int i=-260;i<260;i++){
			//System.out.println(Integer.toHexString(i));
		}
		/*
		byte b80=Byte.valueOf("80", 16);
		byte b79=Byte.valueOf("79",16);
		byte b81=Byte.valueOf("81",16);
		byte b00=Byte.valueOf("00",16);
		byte b01=Byte.valueOf("01",16);
		byte bff=Byte.valueOf("ff",16);
		byte bfe=Byte.valueOf("fe",16);
		System.out.println("b79"+Integer.toHexString(b79));
		System.out.println("b80"+Integer.toHexString(b80));
		System.out.println("b81"+Integer.toHexString(b81));
		System.out.println("b00"+Integer.toHexString(b00));
		System.out.println("b01"+Integer.toHexString(b01));
		System.out.println("bfe"+Integer.toHexString(bfe));
		System.out.println("bff"+Integer.toHexString(bff));
		*/
		System.out.println();
	}
	JLabel jlbName=new JLabel("分析内容:");
	JTextField jtfInput=new JTextField("xml你好123");
	JTextArea jtaOutput=new JTextArea();
	JScrollPane jscrollOutput=new JScrollPane(jtaOutput);
	
	JPanel paneNorth=new JPanel();
	public EncodeShower(){
		paneNorth.setLayout(new BorderLayout());
		paneNorth.add(jlbName,"West");
		paneNorth.add(jtfInput,"Center");
		
		add(paneNorth,"North");
		add(jscrollOutput,"Center");
		
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	private static void str2byte(String str,String charsetName){
		//已收入tlw_lib
		try {
			byte[] bytes=str.getBytes(charsetName);
			System.out.print(charsetName);
			for(int i=0;i<10-charsetName.length();i++){
				System.out.print(" ");
			}
			System.out.print(":");
			showBytes(bytes);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	private static void showBytes(byte[] bytes){
		//已收入tlw_lib
		for(byte b:bytes){
			int i=b;
			String output=Integer.toHexString(i);
			//为负数的情况即单字节大于128的情况下下会以补码的形式即ffffff开头输出
			if(output.length()>2)output=output.substring(6);
			//不足10的需要补0
			if(0<=i && i<10)output="0"+output;
			System.out.print(output.toUpperCase()+" ");
		}
		System.out.println();
	}
}
