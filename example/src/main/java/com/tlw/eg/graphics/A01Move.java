package com.tlw.eg.graphics;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.tlw.util.UtilUi;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年1月25日
 */
public class A01Move {

	public static void main(String[] args) {
		JPanel pane=new JPanel(){
			private static final long serialVersionUID = -3464067319771638070L;
			public void paint(Graphics g){
				super.paint(g);
				g.translate(10, 10);
				g.drawLine(0, 0, 100, 100);
			}
		};
		UtilUi.show(pane, 400 ,300, "");
	}

}
