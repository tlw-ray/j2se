package com.tlw.eg.swing;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.tlw.util.UtilUi;


/**
@Author liwei.tang@magustek.com
@Since 2013-6-20
 */
public class ToogleAction extends JToolBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1652061453299221965L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JToolBar jtoolBar=new JToolBar();
		ActionToggle at0=new ActionToggle();
		JToggleButton at1=new JToggleButton("toggle2");
		jtoolBar.add(at0);
		jtoolBar.add(at1);
		UtilUi.showPack(jtoolBar, "");
	}
	static class ActionToggle extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 9210830526563466489L;
		
		public ActionToggle(){
			putValue(Action.NAME, "Toggle");
			putValue(Action.SELECTED_KEY,true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			Boolean selected=(Boolean)getValue(Action.SELECTED_KEY);
			System.out.println(selected);
			putValue(Action.SELECTED_KEY,!selected);
		}
		
	}
}
