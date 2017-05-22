package com.tlw.eg.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-5
@version:2009-6-5
Description:
 */
public class ButtonGroupStateChange extends JPanel{
	private static final long serialVersionUID = -378696841547090076L;
	public static void main(String[] args) {
		ButtonGroupStateChange pane=new ButtonGroupStateChange();
		Shower.show(pane);
	}
	AbstractButton btn1=new JToggleButton("b1");
	AbstractButton btn2=new JCheckBox("b2");
	AbstractButton btn3=new JRadioButton("b3");
	AbstractButton btn4=new JButton("b4");
	AbstractButton btn5=new JToggleButton("b5");
	AbstractButton btn6=new JCheckBox("b6");
	AbstractButton btn7=new JRadioButton("b7");
	AbstractButton btn8=new JButton("b8");
	public ButtonGroupStateChange(){
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
		add(btn6);
		add(btn7);
		add(btn8);
		final ButtonGroup bg=new ButtonGroup();
		bg.add(btn1);
		bg.add(btn2);
		bg.add(btn3);
		bg.add(btn4);
		bg.add(btn5);
		bg.add(btn6);
		bg.add(btn7);
		bg.add(btn8);
		btn1.setSelected(true);
		ActionListener il=new ActionListener(){
			ButtonModel currentButton;
			{
				currentButton=bg.getSelection();
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton ab=(AbstractButton)e.getSource();
				ButtonModel bm=ab.getModel();
				if(bm.equals(currentButton)){
					return;
				}else{
					System.out.println(ab.getText()+" Clicked!");
					currentButton=bg.getSelection();
				}
			}
		};
		btn1.addActionListener(il);
		btn2.addActionListener(il);
		btn3.addActionListener(il);
		btn4.addActionListener(il);
		btn5.addActionListener(il);
		btn6.addActionListener(il);
		btn7.addActionListener(il);
		btn8.addActionListener(il);
	}
}
