package com.tlw.eg.swing.loading;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-8-27
@version:2009-8-27
Description:
 */
public class JLabelLoading extends JLabel{
	private static final long serialVersionUID = 3327909986376417088L;
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		frame.setSize(600,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final JButton jbuttonStart=new JButton("Start");
		final JButton jbuttonStop=new JButton("Stop");
		final JLabelLoading jlabelLoading=new JLabelLoading();
		jlabelLoading.setText("加载中");
		jbuttonStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				jlabelLoading.startLoading();
			}
		});
		jbuttonStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				jlabelLoading.stopLoading();
			}
		});
		
		frame.add(jlabelLoading,BorderLayout.CENTER);
		frame.add(jbuttonStart,BorderLayout.NORTH);
		frame.add(jbuttonStop,BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	String originalText;
	static final int DOT_COUNT=15;
	int dotCount=1;
	Timer timer=new Timer(150,new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			if(dotCount%DOT_COUNT==0){
				dotCount=1;
				setText(originalText);
			}else{
				dotCount++;
				setText(getText()+'.');
			}
		}
	});
	public void startLoading(){originalText=getText();timer.start();}
	public void stopLoading(){timer.stop();setText(originalText);dotCount=1;}
}
