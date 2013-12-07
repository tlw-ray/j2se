package com.tlw.show.sort;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-4-9
@version:2009-4-9
Description:
 */
public class JFrameSortItem extends JFrame{
	private static final long serialVersionUID = -8221792626923159838L;
	public static void main(String[] args) {
		JFrameSortItem frame=new JFrameSortItem();
		frame.setVisible(true);
	}
	List paneSorts = new Vector();
 
	JButton jbtnStart=new JButton("Run");
	JButton jbtnInit=new JButton("Init");
	JPanel jpaneCenter=new JPanel();
	public JFrameSortItem(){
		jbtnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for(int i=0;i<paneSorts.size();i++){
					JPanelSortItem pane=(JPanelSortItem)paneSorts.get(i);
					if(pane.getData()==null){
						JOptionPane.showMessageDialog(JFrameSortItem.this, "请先点击'init'按钮初始化排序数据后,再点击'run'按钮查看排序过程。");
						break;
					}
					pane.startSort();
				}
			}
		});
		jbtnInit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int[] data=null;
				for(int i=0;i<paneSorts.size();i++){
					JPanelSortItem pane=(JPanelSortItem)paneSorts.get(i);
					pane.algorithm.stop();
					if(data==null){
						data=getRandomArray(pane.getWidth(),pane.getHeight()-pane.offset);
					}
					pane.setData((int[])data.clone());
					//pane.setData(data);//这里不考虑线程安全,即对数据进行多线程排序
					pane.repaint();
				}
			}
		});
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmBidirBubbleSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmBubbleSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmHeapSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmImprovedMergeSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmImprovedQuickSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmInsertSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmMergeSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmMyQuickSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmSelectionSort"));
		paneSorts.add(new JPanelSortItem("tlw.show.sort.AlgorithmShellSort"));
		
		
		

		setSize(800, 300);
		getContentPane().add(jpaneCenter,BorderLayout.CENTER);
		getContentPane().add(jbtnStart,BorderLayout.NORTH);
		getContentPane().add(jbtnInit,BorderLayout.SOUTH);
		
		jpaneCenter.setLayout(new GridLayout(2,5));
		for(int i=0;i<paneSorts.size();i++){
			JPanelSortItem pane=(JPanelSortItem)paneSorts.get(i);
			jpaneCenter.add(pane);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static int[] getRandomArray(int width,int height){
		int a[] = new int[height / 2];
		double f = width/ (double) a.length;
		for (int i = a.length; --i >= 0;) {
			a[i] = (int) (i * f);
		}
		for (int i = a.length; --i >= 0;) {
			int j = (int) (i * Math.random());
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}
		return a;
	}
}