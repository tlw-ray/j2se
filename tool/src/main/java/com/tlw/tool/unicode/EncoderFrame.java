package com.tlw.tool.unicode;

import java.awt.BorderLayout;
import java.io.UnsupportedEncodingException;
import java.util.Formatter;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EncoderFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		final JTextField textField = new JTextField();
		final JComboBox<String> combo = new JComboBox<String>(new String[]{"GBK", "UTF8", "Unicode"});
		final JTextArea textArea = new JTextArea();
		
		textField.getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {
				textChanged(e);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				textChanged(e);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				textChanged(e);
			}
			public void textChanged(DocumentEvent e){
				String text = textField.getText();
				String charset = (String)combo.getSelectedItem();
				try {
					byte[] bytes = text.getBytes(charset);
					String info = getByteString(bytes);
					textArea.setText(info);
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
			}
			
			public String getByteString(byte[] bytes){
				Formatter formatter = new Formatter();
				for (byte b : bytes) {
				    formatter.format("%02X ", b);
				}
				String hex = formatter.toString();
				formatter.close();
				return hex;
			}
		});
		
		frame.add(textField, BorderLayout.NORTH);
		frame.add(textArea, BorderLayout.CENTER);
		frame.add(combo, BorderLayout.EAST);
		
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}

}
