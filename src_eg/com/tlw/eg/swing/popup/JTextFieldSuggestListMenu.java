package com.tlw.eg.swing.popup;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-21
Description:
 ********************************/
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
 
public class JTextFieldSuggestListMenu extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField text;
	final JPopupMenu menu = new JPopupMenu();
	
	public JTextFieldSuggestListMenu(){
		setLayout(new GridLayout(2, 1));
		text = new JTextField(50);
		text.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				redrawSuggestionList();
			}
		});
		add(text);
		add(menu);
	}
	
	private void redrawSuggestionList(){
		text.requestFocus();
		
		menu.setVisible(false);
		
		menu.removeAll();
		
		String query = text.getText();
		
		if(query!=null&&query.trim().length()>0){
			for (int i = 0; i < 3; i++) {
				JMenuItem item = new JMenuItem(query+" : "+i);
				item.addActionListener(new CustomActionListener(i));
				menu.add(item);
			}			
		}
		menu.show(text, text.getLocation().x-text.getWidth(), text.getLocation().y+text.getHeight());
		
		text.requestFocus();
	}
	
	class CustomActionListener implements ActionListener{
		private int index;
		public CustomActionListener(int index){
			this.index=index;
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println(" Index : "+index);
		}
	}
	
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("SuggestList");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
		// Create and set up the content pane.
		JComponent newContentPane = new JTextFieldSuggestListMenu();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
 
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
 
	public static void main(String[] args) {
		
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
 
}

