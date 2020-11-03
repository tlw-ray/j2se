package com.tlw.swing.event.invoke_later_wait;

import javax.swing.SwingUtilities;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月9日
 * 线程中调用invokeLater()，可以看出最终DoHello在AWT-EventQueue-0上被执行
 */
public class InvokeLaterOnThread {

	public static void main(String[] args) {
		UtilUi.show(new MyButton(), 400, 300, "");

		new Thread(){
			public void run(){
				System.out.println("Start at: "+Thread.currentThread());
				SwingUtilities.invokeLater(new DoHello());
				System.out.println("Finish at: "+Thread.currentThread());
			}
		}.start();
	}

}
