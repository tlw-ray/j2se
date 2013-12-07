package com.tlw.eg.swing.combo.inpopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.ComboPopup;

import com.tlw.util.UtilUi;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-29
@version:2009-12-29
Description:普通的ComboBox在Popup中时，由于使用的sharedPopup,它的下拉会导致popup隐藏。这里解决此问题。
 */
public class ComboInPopup extends JPanel {
	private static final long serialVersionUID = 3680275543200639693L;
	public static void main(String[] args) {
		ComboInPopup pane=new ComboInPopup();
		UtilUi.show(pane, 400, 300, "");
	}
	JPopupMenu jpopupMenu=new JPopupMenu();
	JButton jbutton=new JButton("Show JPopup");
	public ComboInPopup(){
		newComboBox.setModel(new DefaultComboBoxModel(new String[]{"aa","bb"}));
		jpopupMenu.add(newComboBox);
		jbutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jpopupMenu.show(jbutton, jbutton.getWidth()/2, jbutton.getHeight()/2);
			}
		});
		add(jbutton);
	}
	public class NewComboBox extends JComboBox {
		private static final long serialVersionUID = 1817883232691681071L;
		public void updateUI() {
	        setUI(new NewComboUI());
	    }    
	}
	public class NewComboUI extends BasicComboBoxUI{
	    protected ComboPopup createPopup() {
	        return new NewComboPopup(comboBox);
	    }
	}
	JComboBox newComboBox = new NewComboBox();
}
