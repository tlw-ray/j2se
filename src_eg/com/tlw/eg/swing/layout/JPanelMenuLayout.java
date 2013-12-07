package com.tlw.eg.swing.layout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.plaf.basic.DefaultMenuLayout;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-6-19
@version:2009-6-19
Description:
 */
public class JPanelMenuLayout extends JPanel {
	private static final long serialVersionUID = -3733274354658348422L;
	public static void main(String[] args) {
		JPanelMenuLayout pane=new JPanelMenuLayout();
		Shower.show(pane);
	}
	public JPanelMenuLayout(){
		DefaultMenuLayout menuLayout=new DefaultMenuLayout(this,DefaultMenuLayout.Y_AXIS);
		setLayout(menuLayout);
		for(int i=0;i<5;i++){
			JButton btn=new JButton(i+"");
			add(btn);
		}
		JTree tree=new JTree();
		add(tree);
	}
}
