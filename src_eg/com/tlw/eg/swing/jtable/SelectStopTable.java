package com.tlw.eg.swing.jtable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-27
@version:2009-4-27
Description:
 */
public class SelectStopTable extends JFrame {
	private static final long serialVersionUID = -1000744389611010355L;
	public static void main(String[] args) {
		SelectStopTable sst=new SelectStopTable();
		sst.setVisible(true);
	}
	Random random=new Random();
	DefaultTableModel model=new DefaultTableModel();
	JTable jtable=new JTable();
	JScrollPane jscroll=new JScrollPane(jtable);
	JButton jbtnStop=new JButton("Stop");
	JButton jbtnStart=new JButton("Start");
	boolean running=false;
	public SelectStopTable(){
		Vector<String> vCols=new Vector<String>();
		Vector<Vector<String>> vRows=new Vector<Vector<String>>();
		model.setDataVector(vRows, vCols);
		vCols.add("Random");
		jtable.setModel(model);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		getContentPane().add(jscroll,BorderLayout.CENTER);
		getContentPane().add(jbtnStop,BorderLayout.NORTH);
		getContentPane().add(jbtnStart,BorderLayout.SOUTH);
		setSize(600,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		new Thread(){
			public void run(){
				while(true){
					if(running){
						String val=random.nextInt()+"";
						Vector<String> row=new Vector<String>();
						row.add(val);
						model.addRow(row);
						System.out.println("Runing!");
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		jtable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(e.getValueIsAdjusting());
				System.out.println(e.getFirstIndex());
			}
		});
		jbtnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				running=true;
			}
		});
		jbtnStop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				running=false;
			}
		});
	}
	
}
