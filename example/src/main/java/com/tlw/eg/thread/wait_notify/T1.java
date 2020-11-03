package com.tlw.eg.thread.wait_notify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.tlw.util.UtilUi;


/**
 * @author liwei.tang@magustek.com
 * @since 2016年1月27日
 */
public class T1 implements Runnable{

	public static void main(String[] args) throws InterruptedException {
		final T1 t1 = new T1();
		final Thread thread = new Thread(t1);
		thread.start();
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				JButton btn = new JButton("wait");
				btn.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						t1.setStatus(Status.STOP);
						//如果这里t1.wait()只能阻塞主线程
					}
				});
				
				JButton btn1 = new JButton("see");
				btn1.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						new Thread(){
							public void run(){
								t1.setStatus(Status.RUN);
							}
						}.start();
					}	
				});
				
				JPanel pane =new JPanel();
				pane.add(btn);
				pane.add(btn1);
				UtilUi.show(pane, 400, 300, "");
			}
		});
	}
	
	public void run(){
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "running...");
			if(status == Status.STOP){
				synchronized(this){
					try {
						System.out.println("0");
						wait();
						System.out.println("3");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	enum Status{
		STOP, RUN
	}
	Status status;
	public void setStatus(Status status){
		System.out.println(Thread.currentThread());
		if(status == Status.RUN){
			synchronized(this){
				System.out.println("1"+this.toString());
				notify();
				System.out.println("2");
			}
		}
		this.status = status;
	}

}
