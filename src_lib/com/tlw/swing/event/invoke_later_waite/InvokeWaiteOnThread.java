package com.tlw.swing.event.invoke_later_waite;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月9日
 */
public class InvokeWaiteOnThread {

	public static void main(String[] args) {
		UtilUi.show(new MyButton(), 400, 300, "");

		new Thread(){
			public void run(){
				System.out.println("Start at: "+Thread.currentThread());
				try {
					SwingUtilities.invokeAndWait(new DoHello());
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Finish at: "+Thread.currentThread());
			}
		}.start();
	}

}
