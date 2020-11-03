package com.tlw.ui.alarm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.tlw.ui.alarm.model.DefaultReadOnlyTableModel;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-27
@version:2009-3-27
Description:隔离信息面板

 */
public class JPanelInfoSeclusion extends JPanel {
	private static final long serialVersionUID = -2314961578518548900L;
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String sysLafName=UIManager.getSystemLookAndFeelClassName();
		UIManager.setLookAndFeel(sysLafName);
		JPanelInfoSeclusion pane=new JPanelInfoSeclusion();
		String[][] data={
				{"KNS-N01","设备","2009-03-21 13:32","2009-03-25 16:14","4天02小时42分"},
				{"KNS-N02","设备","2009-03-20 16:15","2009-03-25 16:15","5天00小时00分"},
				{"KNS-N03","设备","2009-03-20 16:15","2009-03-22 16:15","2天00小时00分"},
		};
		pane.getTableModel().setDataVector(data, pane.columnNames);
		UtilAlarm.show(pane,"隔离信息",650, 400);
	}
	//JLabel jlabelPointTable=new JLabel("机组名:");
	JLabel jlabelPointName=new JLabel("设备编码:");
	JLabel jlabelDeviceName=new JLabel("设备描述:");
	//JComboBox jcomboPointTable=new JComboBox();
	JTextField jtfPointName=new JTextField();
	JTextField jtfDeviceName=new JTextField();
	JButton jbtnSearch=new JButton("查询");
	
	JTable jtableResult=new JTable();
	JScrollPane jscrollResult=new JScrollPane(jtableResult);
	JPanel jpaneNorth=new JPanel();
	String[] columnNames={"点名","设备名","开始隔离时间","结束隔离时间","累计隔离时间"};
	public JPanelInfoSeclusion(){
		setLayout(new BorderLayout());
		add(jscrollResult,BorderLayout.CENTER);
		add(jpaneNorth,BorderLayout.NORTH);

		Border border0=BorderFactory.createEmptyBorder(20, 15, 20, 15);
		jpaneNorth.setBorder(border0);
		jbtnSearch.setMargin(new Insets(2,14,2,14));
		Border border1=BorderFactory.createEmptyBorder(0, 35, 20, 35);
		Border border2=jscrollResult.getBorder();
		Border border3=BorderFactory.createCompoundBorder(border1, border2);
		jscrollResult.setBorder(border3);
		jtableResult.setRowHeight(22);
		jtableResult.setModel(new DefaultReadOnlyTableModel(columnNames));
		
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(0,5,0,2);
		jpaneNorth.setLayout(gbl);
		//jpaneNorth.add(jlabelPointTable,gbc);
		gbc.gridx=2;
		jpaneNorth.add(jlabelPointName,gbc);
		gbc.gridx=4;
		jpaneNorth.add(jlabelDeviceName,gbc);
		gbc.gridx=6;
		jpaneNorth.add(jbtnSearch,gbc);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.insets=new Insets(0,0,0,5);
		gbc.weightx=0.1;
		gbc.gridx=1;
		//jpaneNorth.add(jcomboPointTable,gbc);
		gbc.gridx=3;
		jpaneNorth.add(jtfPointName,gbc);
		gbc.gridx=5;
		jpaneNorth.add(jtfDeviceName,gbc);
	}
	public DefaultTableModel getTableModel(){
		return (DefaultTableModel)jtableResult.getModel();
	}
}