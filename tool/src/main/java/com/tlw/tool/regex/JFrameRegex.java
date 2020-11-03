package com.tlw.tool.regex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-10
@version:2009-6-10
Description:
 */
public class JFrameRegex extends JFrame implements ActionListener{
	private static final long serialVersionUID = 3990201045595439149L;
	public static void main(String[] args) {
		new JFrameRegex();
	}
	JLabel jlabelPattern=new JLabel("正则:");
	JTextField jtfPattern=new JTextField();
	JButton jbtnRun=new JButton("匹配");
	JLabel jlabelOrigin=new JLabel("原文:");
	JTextPane jtpOrigin=new JTextPane(){
		private static final long serialVersionUID = 525880386033438973L;
		public boolean getScrollableTracksViewportWidth(){
		   return true;
		}
	};
	Style styleHighLight,styleNormal;
	public JFrameRegex(){
		jbtnRun.addActionListener(this);
		JScrollPane jscroll=new JScrollPane(jtpOrigin);
		JPanel paneNorth=new JPanel();
		paneNorth.setLayout(new BorderLayout());
		paneNorth.add(jlabelPattern,BorderLayout.WEST);
		paneNorth.add(jtfPattern,BorderLayout.CENTER);
		paneNorth.add(jbtnRun,BorderLayout.EAST);
		paneNorth.setBorder(BorderFactory.createEtchedBorder());
		JPanel paneCenter=new JPanel();
		paneCenter.setLayout(new BorderLayout());
		paneCenter.add(jlabelOrigin,BorderLayout.WEST);
		paneCenter.add(jscroll,BorderLayout.CENTER);
		paneNorth.setBorder(BorderFactory.createEtchedBorder());
		add(paneNorth,BorderLayout.NORTH);
		add(paneCenter,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,300);
		setVisible(true);
		
		jtfPattern.setText("/[\\w\\d\\.]+-");
		jtpOrigin.setText("SIP/extensionnumber-uniquechannelid/aaa-1dfsadfadsf/dooooo-vSIP/meng9999.802-088c2-d18");

		styleNormal=jtpOrigin.getStyledDocument().addStyle("normal", null);
		styleHighLight = jtpOrigin.getStyledDocument().addStyle("highLight", null);
		StyleConstants.setForeground(styleHighLight, new Color(153,103,0));
		StyleConstants.setBold(styleHighLight, true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Pattern pattern=Pattern.compile(jtfPattern.getText());
		Matcher match=pattern.matcher(jtpOrigin.getText());
		jtpOrigin.getStyledDocument().setCharacterAttributes(0, jtpOrigin.getText().length(), styleNormal, true);
		while(match.find()){
			int start=match.start();
			int end=match.end();
			jtpOrigin.getStyledDocument().setCharacterAttributes(start,end-start,styleHighLight,true);
			System.out.println("Pattern:"+jtfPattern.getText());
			System.out.println("Input:"+jtpOrigin.getText());
			System.out.println("FoundAt["+start+"-"+end+"]:\t"+match.group());
		}
		System.out.println("--------------------");
	}
}