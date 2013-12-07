package com.tlw.eg.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.tlw.swing.Shower;




/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-22
Description:
 ********************************/
public class NavList extends JPanel{
	private static final long serialVersionUID = 1L;
	public static void main(String[] args) {
		NavList f=new NavList();
		Shower.show(f);
	}
	JTextField jtfInput=new JTextField("",20);
	JList jlistItems=new JList();
	JScrollPane jscroll4List=new JScrollPane(jlistItems);
	String[] items,filteredItems;
	JPopupMenu jpopup;
	NavList(){
		//initialize user interface
		setLayout(new BorderLayout());
		add(jtfInput,"North");
		if(!isDropDown)add(jscroll4List,"Center");
		
		//initialize data
		Font[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		List<String> fontNames=new Vector<String>();
		for(int i=0;i<fonts.length;i++){
			fontNames.add(fonts[i].getName());
		}
		items=fontNames.toArray(new String[0]);
		jlistItems.setListData(items);
		//initialize event
		DocumentListener inputChange=new InputChange();
		jtfInput.getDocument().addDocumentListener(inputChange);
		if(isDropDown){
			DoubleClickDropDown clickDropDown=new DoubleClickDropDown();
			jtfInput.addMouseListener(clickDropDown);
			ClickSelect clickSelect=new ClickSelect();
			jlistItems.addMouseListener(clickSelect);
		}
		
	}
	//filter items who start with user input;
	private void doFilter(){
		String txt=jtfInput.getText().toLowerCase();
		if(isFilterItems){
			List<String> fileteredList=new Vector<String>();
			for(int i=0;i<items.length;i++){
				if(items[i].toLowerCase().startsWith(txt)){
					fileteredList.add(items[i]);
				}
			}
			jlistItems.setListData(fileteredList.toArray());
		}else{
			for(int i=0;i<items.length;i++){
				if(items[i].toLowerCase().startsWith(txt)){
					jlistItems.setSelectedValue(items[i], true);
				}
			}
		}
	}
	private void doPopup(){
		if(jpopup==null){
			jpopup=new JPopupMenu();
			jpopup.add(jscroll4List);
		}
		jpopup.show(jtfInput, jtfInput.getX(), jtfInput.getY()+jtfInput.getHeight());
		jtfInput.requestFocus();
	}
	//listening user jtfInput change;
	class InputChange implements DocumentListener{
		public void changedUpdate(DocumentEvent e) {doFilter();doPopup();}
		public void insertUpdate(DocumentEvent e) {doFilter();doPopup();}
		public void removeUpdate(DocumentEvent e) {doFilter();doPopup();}
	}
	//listening user mouse double click jtfInput
	class DoubleClickDropDown extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getButton()==MouseEvent.BUTTON1 
					&& e.getClickCount()==2
					&& isDropDown){
				doPopup();
			}
		}
	}
	class ClickSelect extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			jtfInput.setText(jlistItems.getSelectedValue().toString());
			int id=jlistItems.getSelectedIndex();
			System.out.println(id);
			jpopup.setVisible(false);
		}
	}
	/*component property*/
	
	/*component user interface property*/
	private boolean isDropDown=true;
	private boolean isFilterItems=true;
	
	public void setIsDropDown(boolean dropDown){isDropDown=dropDown;}
	public boolean getIsDropDown(){return isDropDown;}
	public void setIsFilterItems(boolean filterItems){isFilterItems=filterItems;}
	public boolean getIsFilterItems(){return isFilterItems;}
	/*下拉框或弹出框容纳的列数*/
	private int dropDownRowCount=30;
	public void setDropDownRows(int rowCount){
		dropDownRowCount=rowCount;
		featDropDownSize();
	}
	private void featDropDownSize(){
		int fontHeight=jlistItems.getFontMetrics(jlistItems.getFont()).getHeight();
		Dimension size=new Dimension(jtfInput.getWidth(),fontHeight*dropDownRowCount);
		jscroll4List.setPreferredSize(size);
	}
	public int getDropDownRows(){return dropDownRowCount;}
}