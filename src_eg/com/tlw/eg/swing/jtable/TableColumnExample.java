package com.tlw.eg.swing.jtable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.tlw.eg.swing.JPanelAutoSize;
import com.tlw.eg.swing.VFlowLayout;
import com.tlw.swing.Shower;
import com.tlw.util.Random;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-2
@version:2009-7-2
Description:需要JDK1.6
 */
public class TableColumnExample extends JPanel implements ActionListener,ChangeListener{
	private static final long serialVersionUID = 8129948276476869547L;
	public static void main(String[] args) {
		TableColumnExample pane=new TableColumnExample();
		Shower.show(pane);
	}
	ButtonGroup bg=new ButtonGroup();
	JRadioButton jrbAutoResizeAllColumns=new JRadioButton("AUTO_RESIZE_ALL_COLUMNS");
	JRadioButton jrbAutoResizeLastColumn=new JRadioButton("AUTO_RESIZE_LAST_COLUMN");
	JRadioButton jrbAutoResizeNextColumn=new JRadioButton("AUTO_RESIZE_NEXT_COLUMN");
	JRadioButton jrbAutoResizeOff=new JRadioButton("AUTO_RESIZE_OFF");
	JRadioButton jrbAutoResizeSubsuquentColumn=new JRadioButton("AUTO_RESIZE_SUBSEQUENT_COLUMNS");
	
	JToggleButton jtbAutoCreateRowSorter=new JToggleButton("自动列排序");
	JToggleButton jtbAutoScrolls=new JToggleButton("AutoScroll");
	JToggleButton jtbColumnSelectionAllowed=new JToggleButton("列选择");
	JToggleButton jtbRowSelectionAlowed=new JToggleButton("行选择");
	JToggleButton jtbHeadReorderAllowed=new JToggleButton("列拖动");
	
	JSpinner jspinCols=new JSpinner(new SpinnerNumberModel(5,1,50,1));
	JSpinner jspinRows=new JSpinner(new SpinnerNumberModel(5,1,50,1));
	JLabel jlabelCols=new JLabel("列数:");
	JLabel jlabelRows=new JLabel("行数");
	
	JTable jtable=new JTable();
	public TableColumnExample(){
		jtbAutoCreateRowSorter.setToolTipText("点击列名时排序");
		jtbAutoScrolls.setToolTipText("拖拽选择数据出当前视图时自动滚动");
		
		jrbAutoResizeAllColumns.setToolTipText("在所有的调整大小操作中，按比例调整所有的列。");
		jrbAutoResizeLastColumn.setToolTipText("在所有的调整大小操作中，只对最后一列进行调整。 ");
		jrbAutoResizeNextColumn.setToolTipText("在 UI 中调整了一个列时，对其下一列进行相反方向的调整。 ");
		jrbAutoResizeOff.setToolTipText(" 不自动调整列的宽度；使用滚动条。 ");
		jrbAutoResizeSubsuquentColumn.setToolTipText("   在 UI 调整中，更改后续列以保持总宽度不变；此为默认行为。");
		
		jrbAutoResizeAllColumns.setSelected(true);
		bg.add(jrbAutoResizeAllColumns);
		bg.add(jrbAutoResizeLastColumn);
		bg.add(jrbAutoResizeNextColumn);
		bg.add(jrbAutoResizeOff);
		bg.add(jrbAutoResizeSubsuquentColumn);
		
		jrbAutoResizeAllColumns.setActionCommand(JTable.AUTO_RESIZE_ALL_COLUMNS+"");
		jrbAutoResizeLastColumn.setActionCommand(JTable.AUTO_RESIZE_LAST_COLUMN+"");
		jrbAutoResizeNextColumn.setActionCommand(JTable.AUTO_RESIZE_NEXT_COLUMN+"");
		jrbAutoResizeOff.setActionCommand(JTable.AUTO_RESIZE_OFF+"");
		jrbAutoResizeSubsuquentColumn.setActionCommand(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS+"");
		
		JPanel jpane1=new JPanelAutoSize();
		jpane1.add(jrbAutoResizeAllColumns);
		jpane1.add(jrbAutoResizeLastColumn);
		jpane1.add(jrbAutoResizeNextColumn);
		jpane1.add(jrbAutoResizeOff);
		jpane1.add(jrbAutoResizeSubsuquentColumn);

		JPanel jpane2=new JPanelAutoSize();
		jpane2.add(jtbAutoCreateRowSorter);
		jpane2.add(jtbAutoScrolls);
		jpane2.add(jtbColumnSelectionAllowed);
		jpane2.add(jtbRowSelectionAlowed);
		jpane2.add(jtbHeadReorderAllowed);
		
		JPanel jpane3=new JPanelAutoSize();
		jpane3.add(jlabelCols);
		jpane3.add(jspinCols);
		jpane3.add(jlabelRows);
		jpane3.add(jspinRows);
		
		JPanel paneNorth=new JPanel();
		paneNorth.setLayout(new VFlowLayout());
		paneNorth.add(jpane1);
		paneNorth.add(jpane2);
		paneNorth.add(jpane3);
		
		JScrollPane jscrollTable=new JScrollPane(jtable);
		fillData();
		
		setLayout(new BorderLayout());
		add(paneNorth,BorderLayout.NORTH);
		add(jscrollTable,BorderLayout.CENTER);
		
		jrbAutoResizeAllColumns.addActionListener(this);
		jrbAutoResizeLastColumn.addActionListener(this);
		jrbAutoResizeNextColumn.addActionListener(this);
		jrbAutoResizeOff.addActionListener(this);
		jrbAutoResizeSubsuquentColumn.addActionListener(this);
		jtbAutoCreateRowSorter.addActionListener(this);
		jtbAutoScrolls.addActionListener(this);
		jtbColumnSelectionAllowed.addActionListener(this);
		jtbRowSelectionAlowed.addActionListener(this);
		jtbHeadReorderAllowed.addActionListener(this);
		jspinCols.addChangeListener(this);
		jspinRows.addChangeListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		fillData();
	}
	public void fillData(){
		int rows=((Integer)jspinRows.getValue()).intValue(),cols=((Integer)jspinCols.getValue()).intValue();
		String[][] data=new String[rows][cols];
		String[] colNames=new String[cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				String str="";
				switch(j%5){
				case 0:str=Random.nextInt(Integer.MAX_VALUE)+"";colNames[j]="整数";break;
				case 1:str=Random.nextDouble()+"";colNames[j]="浮点数";break;
				case 2:str=DateFormat.getDateTimeInstance().format(Random.nextDate());colNames[j]="日期";break;
				case 3:str=Random.nextStringAscII(0, 30);colNames[j]="AscII";break;
				default:str=Random.nextStringZh(0, 20);colNames[j]="汉字";break;
				}
				data[i][j]=str;
			}
		}
		DefaultTableModel model=new DefaultTableModel(data,colNames);
		jtable.setModel(model);
		
		int autoResizeMode=Integer.parseInt(bg.getSelection().getActionCommand());
		jtable.setAutoResizeMode(autoResizeMode);
		jtable.setAutoCreateRowSorter(jtbAutoCreateRowSorter.isSelected());
		jtable.setAutoscrolls(jtbAutoScrolls.isSelected());
		jtable.getColumnModel().setColumnSelectionAllowed(jtbColumnSelectionAllowed.isSelected());
		jtable.setRowSelectionAllowed(jtbRowSelectionAlowed.isSelected());
		jtable.getTableHeader().setReorderingAllowed(jtbHeadReorderAllowed.isSelected());
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		fillData();
	}
}
