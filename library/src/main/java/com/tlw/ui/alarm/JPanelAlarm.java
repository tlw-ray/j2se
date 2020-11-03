package com.tlw.ui.alarm;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.tlw.ui.alarm.model.AbstractPagger;
import com.tlw.ui.alarm.model.DefaultAlarmTableModel;
import com.tlw.ui.alarm.model.PaggerHelper;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-25
@version:2009-3-25
Description:Alarm主面板
192.168.0.190
 */
public class JPanelAlarm extends JPanel{
	private static final long serialVersionUID = -5311316355599310740L;
	public static void main(String[] args){
		UtilAlarm.initUIManager();
		final JPanelAlarm jpanelAlarm=new JPanelAlarm();
		UtilAlarm.show(jpanelAlarm,"预警", 800, 600);
	}
	JPanel jpaneNorth=new JPanel();
	JLabel jlabelTitle=new JLabel("报警总列表");
	JLabel jlabelPageInfo=new JLabel("1 / 5");
	String[] columnNames=new String[]{"图例","时间","测量名(点名)","描述","预警值","报警值","当前值"};
	JTable jtableAlarm=new JTable(){
		private static final long serialVersionUID = -9135719659396077232L;
		{
			DefaultAlarmTableModel tableModel=new DefaultAlarmTableModel(columnNames,20);
			setModel(tableModel);
			setShowHorizontalLines(false);
			addComponentListener(new ComponentAdapter(){
				private double[] columnWidths={40,0.2,0.2,0.2,0.2,0.1,0.1};//存贮每一列的宽度，若小于1，则是窗体宽度百分比。
				public void componentResized(ComponentEvent e) {fixTableRowHeight();}
				public void componentShown(ComponentEvent e) {fixTableRowHeight();}
				private void fixTableRowHeight(){
					//设置行高
					int rowHeight=(jscrollAlarm.getHeight()-jtableAlarm.getTableHeader().getHeight())/jtableAlarm.getRowCount();
					if(rowHeight>1)jtableAlarm.setRowHeight(rowHeight-1);
					//设置列宽
					TableColumnModel tcm=jtableAlarm.getColumnModel();
					TableColumn tc0=tcm.getColumn(0);
					tc0.setMaxWidth(40);//设置第一列不可拉伸
					tc0.setMinWidth(40);
					for(int i=0;i<tcm.getColumnCount();i++){
						TableColumn tc=tcm.getColumn(i);
						int columnWidth=40;
						if(columnWidths[i]>1){
							columnWidth=(int)(columnWidths[i]);
						}else{
							columnWidth=(int)(columnWidths[i]*(jscrollAlarm.getWidth()-40));
						}
						tc.setPreferredWidth(columnWidth);
					}
				}
			});
		}
		//五行自绘制的线
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2=(Graphics2D)g;
			int linePlace=getRowHeight()*5;
			BasicStroke bs=new BasicStroke(2);
			if(linePlace>0){
				g.setColor(Color.gray);
				g2.setStroke(bs);
				int h=linePlace;
				while(h<getHeight()){
					g.drawLine(0, h-1, getWidth(), h-1);
					h+=linePlace;
				}
			}
		}
	};
	JScrollPane jscrollAlarm=new JScrollPane(jtableAlarm);
	
	JPanel jpaneSouth=new JPanel();
	JPanel jpaneModel=new JPanel();
	JPanelPointSelecter jpanePointSelecter=new JPanelPointSelecter();
	JPanel jpanePagger=new JPanel();
	
	JToggleButton jbtnModelReal=new JToggleButton("实时模式");
	JToggleButton jbtnModelHis=new JToggleButton("历史模式");
	ButtonGroup bgModel=new ButtonGroup();
	
	//JLabel jlabelChangePage=new JLabel("翻页");
	JButton jbtnNext=new JButton();
	JButton jbtnPre=new JButton();
	JButton jbtnNextMany=new JButton();
	JButton jbtnPreMany=new JButton();
	JButton jbtnEnd=new JButton();
	JButton jbtnFirst=new JButton();
	
	public JPanelAlarm(){
		jpaneNorth.setLayout(new BorderLayout());
		jpaneNorth.add(jlabelTitle,BorderLayout.WEST);
		jpaneNorth.add(jlabelPageInfo,BorderLayout.EAST);
		
		//实时模式，历史模式面板
		GridBagLayout gbl1=new GridBagLayout();
		GridBagConstraints gbc1=new GridBagConstraints();
		jpaneModel.setLayout(gbl1);
		gbc1.weighty=0.1;
		gbc1.weightx=0.1;
		gbc1.gridy=0;
		jpaneModel.add(jbtnModelReal,gbc1);
		gbc1.gridy=1;
		jpaneModel.add(jbtnModelHis,gbc1);
		Border borderModel=BorderFactory.createTitledBorder("模式");
		jpaneModel.setBorder(borderModel);
		
		//---翻页按钮面板
		GridBagLayout gbl2=new GridBagLayout();
		GridBagConstraints gbc2=new GridBagConstraints();
		jpanePagger.setLayout(gbl2);
		gbc2.weighty=0.1;

		jpanePagger.add(jbtnNext,gbc2);
		gbc2.gridx=1;
		jpanePagger.add(jbtnNextMany,gbc2);
		gbc2.gridx=2;
		jpanePagger.add(jbtnEnd,gbc2);
		gbc2.gridx=0;
		gbc2.gridy=1;
		jpanePagger.add(jbtnPre,gbc2);
		gbc2.gridx=1;
		jpanePagger.add(jbtnPreMany,gbc2);
		gbc2.gridx=2;
		jpanePagger.add(jbtnFirst,gbc2);

		Border borderSouth=BorderFactory.createTitledBorder("分页");
		jpanePagger.setBorder(borderSouth);
		
		jpaneSouth.setLayout(new BorderLayout());
		jpaneSouth.add(jpaneModel,BorderLayout.WEST);
		jpaneSouth.add(jpanePointSelecter,BorderLayout.CENTER);
		jpaneSouth.add(jpanePagger,BorderLayout.EAST);

		prettifyTable();
		linkPageAction();
		
		setLayout(new BorderLayout());
		add(jpaneNorth,BorderLayout.NORTH);
		add(jscrollAlarm,BorderLayout.CENTER);
		add(jpaneSouth,BorderLayout.SOUTH);
		
		bgModel.add(this.jbtnModelHis);
		bgModel.add(this.jbtnModelReal);
		
		exampleData();
	}
	public void prettifyTable(){
		TableColumn tc0=jtableAlarm.getColumnModel().getColumn(0);
		tc0.setCellRenderer(new TableCellRenderer(){
			JButton jbtn=new JButton();
			Color color=jbtn.getBackground();
		    public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
		    	if(value!=null){
		    		String val=value.toString();
		    		jbtn.setText(val);
		    		if(val.equals("Y")){
		    			jbtn.setBackground(Color.yellow);
		    		}else if(val.equals("R")){
		    			jbtn.setBackground(Color.red);
		    		}
		    	}else{ 
		    		jbtn.setText("");
		    		jbtn.setBackground(color);
		    	}
		    	jbtn.setMargin(new Insets(1,1,1,1));
		    	return jbtn;
		    }
		});
	}
	PaggerHelper ph=new PaggerHelper(getTableModel().pagger);
	private void linkPageAction(){
		ph.setJLabelPageInfo(this.jlabelPageInfo);
		jbtnFirst.setAction(ph.getActionFirstPage());
		jbtnEnd.setAction(ph.getActionLastPage());
		jbtnNext.setAction(ph.getActionNextPage());
		jbtnPre.setAction(ph.getActionPrePage());
		jbtnNextMany.setAction(ph.getActionNextPages());
		jbtnPreMany.setAction(ph.getActionPrePages());
	}
	public void refreshPageInfo(){
		ph.refreshPageInfo();
	}
	public DefaultAlarmTableModel getTableModel(){
		return (DefaultAlarmTableModel)jtableAlarm.getModel();
	}
	public static final SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	public void exampleData(){
		Object[][] tempData=new Object[5][7];
		tempData[0][0]="Y";
		tempData[1][0]="R";
		tempData[2][0]="R";
		tempData[3][0]="R";
		tempData[4][0]="R";
		
		tempData[0][1]="2008-09-04 15:47:18 031";
		tempData[1][1]="2008-09-04 15:47:18 031";
		tempData[2][1]="2008-09-04 15:47:18 031";
		tempData[3][1]="2008-09-04 15:47:18 031";
		tempData[4][1]="2008-09-04 15:47:18 031";
		
		tempData[0][2]="W3.UNIT1.A0003";
		tempData[1][2]="W3.UNIT1.A0003";
		tempData[2][2]="W3.UNIT1.A0003";
		tempData[3][2]="W3.UNIT1.A0003";
		tempData[4][2]="W3.UNIT1.A0003";
		
		tempData[0][3]="遥信备用199";
		tempData[1][3]="遥信备用199";
		tempData[2][3]="遥信备用199";
		tempData[3][3]="遥信备用199";
		tempData[4][3]="遥信备用199";
		
		tempData[0][4]="80";
		tempData[1][4]="80";
		tempData[2][4]="80";
		tempData[3][4]="80";
		tempData[4][4]="80";
		
		tempData[0][5]="100";
		tempData[1][5]="100";
		tempData[2][5]="100";
		tempData[3][5]="100";
		tempData[4][5]="100";

		tempData[0][6]="85";
		tempData[1][6]="107";
		tempData[2][6]="106";
		tempData[3][6]="104";
		tempData[4][6]="107";
		
		for(int i=0;i<tempData.length;i++){
			List row=new Vector(Arrays.asList(tempData[i]));
			getTableModel().appendRow(row);
		}

		new Thread(){
			DefaultAlarmTableModel model=getTableModel();
			public void run(){
				while(true){
					if(jbtnModelReal.isSelected()){
						String[] row=new String[7];
						row[1]=dateFormater.format(new Date());
						model.appendRow(Arrays.asList(row));
						jtableAlarm.repaint();
						AbstractPagger pagger=model.pagger;
						if(model.getData().size()%pagger.getPageSize()==1
								&& model.pagger.getCurrentPage()==pagger.getPageCount()-1){
							model.pagger.nextPage();
						}
						refreshPageInfo();//保持右上角页信息栏同步(1/5)
					}
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}