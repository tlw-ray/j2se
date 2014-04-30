package com.tlw.eg.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.tlw.util.UtilUi;
//这个按钮用来解决用户对按钮狂点的问题
//用户快速点击按钮不会触发fetch，只会触发repaint
//用户停止点击后，触发fetch和repaint.
public class JButtonDelay {

	public static void main(String[] args) {
		JButton jbutton=new JButton("Delay");
		
		jbutton.addActionListener(new ActionListener(){
			long oldClick=System.currentTimeMillis();
			long newClick=System.currentTimeMillis();
			long minmumClickSpan=5000;
			long clickSpan=0;
			boolean begin=false;
			@Override
			public void actionPerformed(ActionEvent e) {
				oldClick=newClick;
				newClick=System.currentTimeMillis();
				clickSpan=newClick-oldClick;
				//check is clicking
				if(clickSpan<minmumClickSpan){
					System.out.println("repaint()...");
				}else{
					begin=true;
					new Thread(){
						public void run(){
							while(begin){
								//check is clicking timeout
								if(System.currentTimeMillis()-newClick>minmumClickSpan){
									System.out.println("fetchData()...");
									System.out.println("repaint()...");
									begin=false;
								}else{
									try {
										sleep(50);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}
					}.start();
				}
			}
		});
		
		UtilUi.show(jbutton, 400, 300, "");
	}

}
