package com.tlw.tool.code;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

class CodeCopyer extends JFrame {
	private static final long serialVersionUID = -1619230208344332553L;

	public static void main(String[] args){
		CodeCopyer cc=new CodeCopyer ();
		cc.setVisible(true);
	}
	JTextPane jtp=new JTextPane();
	JScrollPane jsp=new JScrollPane(jtp);
	JButton btnClear=new JButton("Clear");
	JButton btnFilterRepeat=new JButton("FilterRepeat");
	JButton btnTrimLines=new JButton("TrimLines");
	JPanel paneSouth=new JPanel();

	public CodeCopyer() {
			
		btnClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jtp.setText("");	
			}	
		});
		btnFilterRepeat.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				filterRepeat();
			}
		});
		btnTrimLines.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				trimLines();
			}
		});
		GridLayout gridLayout=new GridLayout();
		paneSouth.setLayout(gridLayout);
		paneSouth.add(btnFilterRepeat);
		paneSouth.add(btnTrimLines);
		paneSouth.add(btnClear);

		add(paneSouth,"South");
		add(jsp,"Center");
		setAlwaysOnTop(true);
		setSize(800, 600);
		setLocation(920,530);
		setTitle("CodeCopyer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void trimLines(){
		String str=jtp.getText();
		String[] strs=str.split("\n");
		String output="";
		for(String s:strs){
			output+=s.trim()+"\n";
		}
		jtp.setText(output);
	}
	public void filterRepeat(){
		String str=jtp.getText();
		String[] strs=str.split("\n");
		Set<String> set=new HashSet<String>();
		for(String s:strs){
			try{
				set.add(s.trim());	
			}catch(Exception ex){
				System.out.println("reduplicate item:"+s);
			}
		}
		String output="";
		List<String> lst=new Vector<String>(set);
		Collections.sort(lst);
		for(String s:lst){
				output+=
				s+"\n";
		}
		jtp.setText(output);
	}
}
