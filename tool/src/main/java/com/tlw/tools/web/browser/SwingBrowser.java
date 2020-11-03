package com.tlw.tools.web.browser;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.tlw.swing.Shower;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-25
@version:2009-7-25
Description:
 */
public class SwingBrowser extends JPanel implements ActionListener{
	private static final long serialVersionUID = 8849748375792088092L;
	JTextField jtfUrl=new JTextField();
	JLabel jlabelUrl=new JLabel("URL:");
	JButton jbtnGo=new JButton("GO");
	JTextPane jtpBrowser=new JTextPane();
	public static void main(String[] args) {
		SwingBrowser browser=new SwingBrowser();
		Shower.show(browser);
	}
	public SwingBrowser(){
		jtpBrowser.setContentType("text/html;charset=GB2312");
		jtfUrl.setText("file:\\\\\\D:\\app.java\\workspace\\TLW-20090725-PROJECT-INFO\\WebContent\\A7项目大事记.htm");
		
		JPanel paneNorth=new JPanel();
		paneNorth.setLayout(new BorderLayout());
		paneNorth.add(jlabelUrl,BorderLayout.WEST);
		paneNorth.add(jtfUrl,BorderLayout.CENTER);
		paneNorth.add(jbtnGo,BorderLayout.EAST);
		
		setLayout(new BorderLayout());
		add(paneNorth,BorderLayout.NORTH);
		add(new JScrollPane(jtpBrowser),BorderLayout.CENTER);
		
		jbtnGo.addActionListener(this);
		jtfUrl.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try {
			jtpBrowser.setPage(jtfUrl.getText());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage());
		}finally{
			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
