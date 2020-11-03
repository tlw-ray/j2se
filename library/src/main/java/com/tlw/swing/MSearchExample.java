package com.tlw.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-27
Description:MSearch的所有功能的例子
 ********************************/
public class MSearchExample extends JPanel {
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		MSearchExample comp=new MSearchExample();
		
		JFrame f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(comp,"Center");
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
	}
	MSearch ms=new MSearch();
	JCheckBox jckDropDownMode=new JCheckBox("【弹出|固定】模式");
	JCheckBox jckFilterMode=new JCheckBox("【过滤|导航】模式");
	SpinnerNumberModel jspinModel=new SpinnerNumberModel(8,3,15,1);
	JSpinner jspinnerRows=new JSpinner(jspinModel);
	public MSearchExample(){
		ms.setItems(new String[]{"aaa","aab","aac","W3.UNIT1.E0003","W3.UNIT1.E0004","W3.UNIT1.E0005"});
		//当按回车时，输出当前文本框内的内容。
		ms.setActionPerform(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, ms.getText());
				System.out.println(ms.getText());
			}
		});
		//设置是否下拉模式
		jckDropDownMode.setSelected(ms.getIsDropDown());
		jckDropDownMode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ms.setIsDropDownModel(jckDropDownMode.isSelected());
			}
		});
		//设置过滤模式或者索引模式
		jckFilterMode.setSelected(ms.getIsFilterMode());
		jckFilterMode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ms.setIsFilter(jckFilterMode.isSelected());
			}
		});
		//设置下拉行数
		jspinnerRows.setPreferredSize(new Dimension(20,20));
		jspinnerRows.setMaximumSize(new Dimension(150,20));
		jspinnerRows.setToolTipText("drop down item count.");
		jspinnerRows.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				int rowCount=jspinModel.getNumber().intValue();
				ms.setRows(rowCount);
			}
		});
		//初始化界面
		JPanel paneLeft=new JPanel();
		Dimension leftSize=new Dimension(200,100);
		paneLeft.setPreferredSize(leftSize);
		BoxLayout lm=new BoxLayout(paneLeft,BoxLayout.Y_AXIS); 
		paneLeft.setLayout(lm);
		paneLeft.add(jckDropDownMode);
		paneLeft.add(jckFilterMode);
		paneLeft.add(jspinnerRows);
		
		setLayout(new BorderLayout());
		add(ms,"Center");
		add(paneLeft,"West");
	}
}
