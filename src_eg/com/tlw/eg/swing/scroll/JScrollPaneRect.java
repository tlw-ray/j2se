package com.tlw.eg.swing.scroll;

import java.awt.Graphics;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.tlw.util.UtilUi;


/**
@since 2011-6-22
@author 唐力伟 (liwei.tang@magustek.com)
 */
public class JScrollPaneRect extends JScrollPane {
	private static final long serialVersionUID = -4455138159986748763L;
	public static void main(String[] args) {
		JTextArea jta=new JTextArea();
		jta.setText("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		final JScrollPaneRect pane=new JScrollPaneRect();
		pane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				pane.repaint();
			}
			
		});
		pane.setViewportView(jta);
		UtilUi.show(pane, 400, 300, "");
	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawRect(50, 50, 100, 100);
	}
}
