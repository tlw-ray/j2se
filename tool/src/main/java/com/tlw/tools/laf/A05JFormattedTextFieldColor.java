package com.tlw.tools.laf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import com.tlw.swing.Shower;


public class A05JFormattedTextFieldColor extends JPanel {
	private static final long serialVersionUID = -5957988193250418848L;
	public static void main(String[] args) {
		final JFormattedTextField jtf=new JFormattedTextField();
		Shower.show(jtf);
		jtf.setValue(1000.100001);
		jtf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println(jtf.getValue().getClass());
			}
		});
	}

}
