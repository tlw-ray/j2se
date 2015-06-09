package com.tlw.swing.event.invoke_later_wait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月9日
 */
public class InvokeLaterOnAWT {

	public static void main(String[] args) {
		MyButton myButton=new MyButton();
		myButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new DoHello());
			}
		});
		UtilUi.show(myButton, 400, 300, "");
	}

}
