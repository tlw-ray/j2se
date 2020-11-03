package com.tlw.eg.util;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-10-13
@version:2009-10-13
Description:
 */
public class HtmlImageInJar extends JPanel {
	private static final long serialVersionUID = -6196299802106801985L;
	public static void main(String[] args) {
		HtmlImageInJar pane=new HtmlImageInJar();
		Shower.show(pane);
	}
	public HtmlImageInJar(){
		setLayout(new BorderLayout());
		String html="<font color=blue>Hello</font><img src="+getClass().getResource("inherit.gif")+" />";
		
		HTMLEditorKit kit=new HTMLEditorKit();
		Document doc=kit.createDefaultDocument();
		HTMLDocument htmlDoc=(HTMLDocument)doc;
		Element elementRoot=htmlDoc.getRootElements()[0];
		JLabel label=new JLabel();
		try {
			htmlDoc.setInnerHTML(elementRoot, html);
			System.out.println(htmlDoc.getText(0, htmlDoc.getLength()));
			label.setText(htmlDoc.getText(0, htmlDoc.getLength()));
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		add(label,BorderLayout.CENTER);
		System.out.println(getClass().getResource("inherit.gif"));
	}
}
