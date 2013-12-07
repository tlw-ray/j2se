package com.tlw.eg.swing.text;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-2-10
@version:2009-2-10
Description:语法高亮
 */
public class HightLight extends JFrame{
	private static final long serialVersionUID = 7385069207205899154L;
	static final String[] keywords={"SELECT","AS","FROM","WHERE","ASC","DESC","ORDER","BY","LIMIT"};
	static final String[] operator={"+","-","*","/","(",")",">","<",">=","<=","==","<>",};
	public static void main(String[] args) {
		HightLight h=new HightLight();
		h.setVisible(true);
	}
	JTextPane jtp=new JTextPane();
	public HightLight(){
		String sql="select *,id as i,name as n,value as v,PI*(1024-val/(3+0.14)) as GV from table1 as T1, (select fff1 from table2 as T2,(select * from table3 where id>100*(45.3-2*value)) as T3) as T4  order by id limit 3,100";
		setSize(400,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		jtp.setText(sql.toUpperCase());
		JScrollPane jscroll=new JScrollPane(jtp);

		Style style = jtp.addStyle("ffff", null);
		StyleConstants.setForeground(style, Color.red);
		StyleConstants.setBold(style, true);
		
		StyledDocument doc = jtp.getStyledDocument();
		doc.setCharacterAttributes(0, 6, jtp.getStyle("ffff"), false);
		
		add(jscroll);
	}
}
