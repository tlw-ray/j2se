package com.tlw.swing.event.invoke_later_wait;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月9日
 * Cannot call invokeAndWait from the event dispatcher thread
 */
public class InvokeWaitOnAWT {

	public static void main(String[] args) {
		MyButton myButton=new MyButton();
		myButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SwingUtilities.invokeAndWait(new DoHello());
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		UtilUi.show(myButton, 400, 300, "");
	}

}
