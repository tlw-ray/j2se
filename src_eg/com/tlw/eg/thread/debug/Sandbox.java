package com.tlw.eg.thread.debug;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年1月27日
 */
public class Sandbox extends JFrame {

	boolean paused = false;
	Thread thread = new Thread() {
		public void run() {
			while (true) {
				System.out.println("running...");
			}
		}
	};

	private JButton button;

	public Sandbox() throws Exception {
		thread.start();
		setSize(300, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		add(button = new JButton("Pause"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				new Thread() {
//					public void run() {
						synchronized (thread) {
							try {
								if (button.getText().equals("Pause")) {
									thread.wait();
									button.setText("Resume");

								} else if (button.getText().equals("Resume")) {
									thread.notify();
									button.setText("Pause");
								}
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
//					}
//				}.start();
			}
		});
		setVisible(true);
	}

	public static void main(String[] args) throws Exception {
		new Sandbox();
	}
}