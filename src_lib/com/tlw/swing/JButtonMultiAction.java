package com.tlw.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;

/**
@since 2010-4-9
@version 2010-4-9
@author 唐力伟 (tlw_ray@163.com)
多状态按钮
 */
public class JButtonMultiAction extends JButton {
	private static final long serialVersionUID = 4610579880971456029L;
	public static void main(String[] args) {
		JButtonMultiAction btn=new JButtonMultiAction();
		Action action1=new AbstractAction(){
			private static final long serialVersionUID = 2719843232840725158L;
			{
				putValue(NAME,"ACTION1");
			}
			public void actionPerformed(ActionEvent e){
				System.out.println("a1");
			}
		};
		Action action2=new AbstractAction(){
			private static final long serialVersionUID = 2719843232840725158L;
			{
				putValue(NAME,"ACTION2");
			}
			public void actionPerformed(ActionEvent e){
				System.out.println("a2");
			}
		};
		btn.addAction(action1);
		btn.addAction(action2);
		Shower.show(btn, 50, 55);
	}
	private Vector actions=new Vector();//ImageIcon
	private int currentActionIndex=0;
	public JButtonMultiAction(){
		this.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(actions.size()>0){
					if(currentActionIndex++>=actions.size()-1){
						currentActionIndex=0;
					}
					Action currentAction=(Action)actions.get(currentActionIndex);
					setAction(currentAction);
				}
			}
		});
	}
	public void addAction(Action action){
		actions.add(action);
		Action currentAction=(Action)actions.get(0);
		setAction(currentAction);
	}
}