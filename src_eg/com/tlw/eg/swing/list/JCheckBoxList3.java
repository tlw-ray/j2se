package com.tlw.eg.swing.list;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.tlw.util.UtilUi;


/**
@since 2010-10-9
@version 2010-10-9
@author 唐力伟 (tlw_ray@163.com)
 */
public class JCheckBoxList3 extends JPanel {
	private static final long serialVersionUID = -1618657948430753632L;
	public static void main(String[] args) {
		JCheckBoxList3 pane=new JCheckBoxList3();
		UtilUi.show(pane, 400, 300, "");
	}
	Object selected;
	public JCheckBoxList3(){
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=GridBagConstraints.RELATIVE;
		for(int i=0;i<5;i++){
			JCheckBoxLabeled jcheckBox=new JCheckBoxLabeled();
			jcheckBox.getJlabel().setText(i+"");
			jcheckBox.getJlabel().addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					JLabel jlabel=(JLabel)e.getSource();
					selected=jlabel.getParent();
					changeColor();
				}
			});
			jcheckBox.getJcheckBox().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					selected=null;
					changeColor();
				}
			});
			add(jcheckBox,gbc);
		}
	}
	private void changeColor(){
		for(Component comp:getComponents()){
			if(comp instanceof JCheckBoxLabeled){
				JCheckBoxLabeled ck=(JCheckBoxLabeled)comp;
				if(ck==selected){
					ck.setBackground(UIManager.getColor("List.selectionBackground"));
				}else{
					ck.setBackground(UIManager.getColor("CheckBox.background"));
				}
			}
		}
	}
	static class JCheckBoxLabeled extends JPanel{
		private static final long serialVersionUID = -890633229226804150L;
		JCheckBox jcheckBox=new JCheckBox();
		JLabel jlabel=new JLabel();
		{
			setLayout(new BorderLayout());
			add(jlabel,BorderLayout.CENTER);
			add(jcheckBox,BorderLayout.WEST);
		}
		public JCheckBox getJcheckBox() {
			return jcheckBox;
		}
		public void setJcheckBox(JCheckBox jcheckBox) {
			this.jcheckBox = jcheckBox;
		}
		public JLabel getJlabel() {
			return jlabel;
		}
		public void setJlabel(JLabel jlabel) {
			this.jlabel = jlabel;
		}
	}
}