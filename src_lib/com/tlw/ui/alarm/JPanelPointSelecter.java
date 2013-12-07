package com.tlw.ui.alarm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-26
@version:2009-3-26
Description:点选择面板
 */
public class JPanelPointSelecter extends JPanel {
	private static final long serialVersionUID = 4366555946961194013L;
	public static void main(String[] args) {
		JFrame f=new JFrame();
		JPanelPointSelecter pane=new JPanelPointSelecter();
		f.getContentPane().add(pane,BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,160);
		f.setVisible(true);
	}
	JLabel jlabelPointName=new JLabel("搜点名：");
	JLabel jlabelTimeFrom=new JLabel("从");
	JLabel jlabelTimeTo=new JLabel("到");
	JLabel jlabelAlarmLevel=new JLabel("搜报警级");
	JTextField jtfPointName=new JTextField();
	JTextField jtfTimeFrom=new JTextField();
	JTextField jtfTimeTo=new JTextField();
	JButton jbtnSetTime=new JButton("设定时间");
	JButton jbtnBaseSystem=new JButton("基本系统");
	JComboBox jcomboBaseSystem=new JComboBox();
	JButton jbtnM=new JButton("M");
	JButton jbtnR=new JButton("R");
	JButton jbtnY=new JButton("Y");
	JButton jbtnW=new JButton("W");
	JButton jbtnO=new JButton("O");
	JButton jbtnSearch=new JButton("搜索");
	JRadioButton jrbtnPointName=new JRadioButton();
	JRadioButton jrbtnSetTime=new JRadioButton();
	JRadioButton jrbtnBaseSystem=new JRadioButton();
	JRadioButton jrbtnAlarmLevel=new JRadioButton();
	
	JPanel jpaneLastRow=new JPanel();
	public JPanelPointSelecter(){
		GridBagLayout gbl2=new GridBagLayout();
		GridBagConstraints gbc2=new GridBagConstraints();
		setLayout(gbl2);
		gbc2.weighty=0.1;
		gbc2.ipadx=5;
		gbc2.anchor=GridBagConstraints.CENTER;
		//---第一列
		add(jrbtnPointName,gbc2);
		gbc2.gridy=1;
		add(jrbtnSetTime,gbc2);
		gbc2.gridy=2;
		add(jrbtnBaseSystem,gbc2);
		gbc2.gridy=3;
		add(jrbtnAlarmLevel,gbc2);
		//---第二列
		gbc2.gridx=1;
		gbc2.gridy=0;
		add(jlabelPointName,gbc2);
		gbc2.gridy=1;
		add(jbtnSetTime,gbc2);
		gbc2.gridy=2;
		add(jbtnBaseSystem,gbc2);
		gbc2.gridy=3;
		add(jlabelAlarmLevel,gbc2);
		//---不需要填充的
		gbc2.gridy=1;
		gbc2.gridx=2;
		add(jlabelTimeFrom,gbc2);
		gbc2.gridx=4;
		add(jlabelTimeTo,gbc2);
		//---需要填充的
		gbc2.fill=GridBagConstraints.HORIZONTAL;
		gbc2.weightx=0.1;
		gbc2.gridy=1;
		gbc2.gridx=3;
		add(jtfTimeFrom,gbc2);
		gbc2.gridx=5;
		add(jtfTimeTo,gbc2);
		//---需要填充，宽度为4的
		gbc2.gridwidth=4;
		gbc2.gridy=0;
		gbc2.gridx=2;
		add(jtfPointName,gbc2);
		gbc2.gridy=2;
		add(jcomboBaseSystem,gbc2);
		gbc2.gridy=3;
		add(jpaneLastRow,gbc2);

		//---最后一行的子面板
		GridBagLayout gbl3=new GridBagLayout();
		GridBagConstraints gbc3=new GridBagConstraints();
		jpaneLastRow.setLayout(gbl3);
		jpaneLastRow.add(jbtnM,gbc3);
		gbc3.gridx=1;
		jpaneLastRow.add(jbtnR,gbc3);
		gbc3.gridx=2;
		jpaneLastRow.add(jbtnY,gbc3);
		gbc3.gridx=3;
		jpaneLastRow.add(jbtnW,gbc3);
		gbc3.gridx=4;
		jpaneLastRow.add(jbtnO,gbc3);
		gbc3.weightx=0.1;
		gbc3.gridx=5;
		gbc3.anchor=GridBagConstraints.EAST;
		jpaneLastRow.add(jbtnSearch,gbc3);
		
		Border border=BorderFactory.createTitledBorder("查询");
		setBorder(border);
	}
}