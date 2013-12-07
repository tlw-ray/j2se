package com.tlw.eg.swing.text;

import java.io.IOException;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-3
@version:2009-7-3
Description:暂时没法解决，见JAVA DOC RTFEditorKit:
public class RTFEditorKit extends StyledEditorKit
这是对 RTF 编辑功能的默认实现。Swing 团队未编写 RTF 支持。将来我们希望改进提供的支持。 

 */
public class RtfEncode extends JPanel {
	private static final long serialVersionUID = -8795626578193151869L;
	public static void main(String[] args) {
		RtfEncode pane=new RtfEncode();
		Shower.show(pane);
	}
	public RtfEncode(){
		URL u=ClassLoader.getSystemResource("tlw/example/swing/text/RtfEncode.htm");
		System.out.println(u.getPath());
		JTextPane jtp=new JTextPane();
		jtp.setContentType("text/htm; charset=GBK");
		try {
			jtp.setPage(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(jtp);
	}
}
