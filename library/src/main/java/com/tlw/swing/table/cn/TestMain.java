package com.tlw.swing.table.cn;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class TestMain {
	public static void main(String[] args) {
		final JTable table = createTable();
		JFrame frame = new JFrame("复杂JTable");
		frame.setSize(600, 350);
		frame.setLayout(new GridBagLayout());
		frame.add(new JScrollPane(table),new GBC(0,0).setWeight(100,100).setFill(GBC.BOTH));
		JButton printButton = new JButton("Print");
		printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					table.print();
				} catch (java.awt.print.PrinterException e) {
					e.printStackTrace();
				}
			}
		});	
		
		frame.add(printButton,new GBC(0,1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public static JTable createTable() {
		TableContent tableContent = new TableContent();
		tableContent.append("测试项目1", 1, 8);
		tableContent.append("测试", 1, 2);
		tableContent.append("数量");
		tableContent.println();
		tableContent.append("比率");
		tableContent.println();
		tableContent.append("编码", 1, 2);
		tableContent.append("数量");
		tableContent.append("跨多行多列",3,4);
		tableContent.println();
		tableContent.append("比率");
		tableContent.println();
		tableContent.append("设计", 1, 2);
		tableContent.append("数量");
		tableContent.println();
		tableContent.append("比率");
		tableContent.println();
		tableContent.append("需求", 1, 2);
		tableContent.append("数量");
		tableContent.println();
		tableContent.append("比率");
		tableContent.println();
		//<GroupHeader>
		List list=new ArrayList();
		list.add(new GroupHeader("BUG类型",3,7));
		list.add(new GroupHeader("阶段",1,2));
		return DTable.create(tableContent
				,new Object[]{"被测试项目",GroupHeader.EMPTY_LABEL,GroupHeader.EMPTY_LABEL,"逻辑错误","文本错误","接口错误","程序错误","合计"}
				,list);
		
	}
	
}

/**
 * 该类来自于《Java核心技术》
 * @author Administrator
 *
 */
class GBC extends GridBagConstraints {
	private static final long serialVersionUID = 6554283739534807968L;

	/**
	 Constructs a GBC with a given gridx and gridy position and
	 all other grid bag constraint values set to the default.
	 @param gridx the gridx position
	 @param gridy the gridy position
	 */
	public GBC(int gridx, int gridy) {
		this.gridx = gridx;
		this.gridy = gridy;
	}

	/**
	 Constructs a GBC with given gridx, gridy, gridwidth, gridheight
	 and all other grid bag constraint values set to the default.
	 @param gridx the gridx position
	 @param gridy the gridy position
	 @param gridwidth the cell span in x-direction
	 @param gridheight the cell span in y-direction
	 */
	public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
		this.gridx = gridx;
		this.gridy = gridy;
		this.gridwidth = gridwidth;
		this.gridheight = gridheight;
	}

	/**
	 Sets the anchor.
	 @param anchor the anchor value
	 @return this object for further modification
	 */
	public GBC setAnchor(int anchor) {
		this.anchor = anchor;
		return this;
	}

	/**
	 Sets the fill direction.
	 @param fill the fill direction
	 @return this object for further modification
	 */
	public GBC setFill(int fill) {
		this.fill = fill;
		return this;
	}

	/**
	 Sets the cell weights.
	 @param weightx the cell weight in x-direction
	 @param weighty the cell weight in y-direction
	 @return this object for further modification
	 */
	public GBC setWeight(double weightx, double weighty) {
		//super.w
		this.weightx = weightx;
		this.weighty = weighty;
		return this;
	}

	/**
	 Sets the insets of this cell.
	 @param distance the spacing to use in all directions
	 @return this object for further modification
	 */
	public GBC setInsets(int distance) {
		this.insets = new Insets(distance, distance, distance, distance);
		return this;
	}

	/**
	 Sets the insets of this cell.
	 @param top the spacing to use on top
	 @param left the spacing to use to the left
	 @param bottom the spacing to use on the bottom
	 @param right the spacing to use to the right
	 @return this object for further modification
	 */
	public GBC setInsets(int top, int left, int bottom, int right) {
		this.insets = new Insets(top, left, bottom, right);
		return this;
	}

	/**
	 Sets the internal padding
	 @param ipadx the internal padding in x-direction
	 @param ipady the internal padding in y-direction
	 @return this object for further modification
	 */
	public GBC setIpad(int ipadx, int ipady) {
		this.ipadx = ipadx;
		this.ipady = ipady;
		return this;
	}
}
