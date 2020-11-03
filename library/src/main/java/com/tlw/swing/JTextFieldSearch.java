package com.tlw.swing;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author 唐力伟
 * @since 2008-11-21
 * @version 2008-11-21
 * @Description	带有下拉框提示有导航功能的JTextField
 */
public class JTextFieldSearch extends JTextField{
	private static final long serialVersionUID = 4514612065216651718L;
	public static void main(String[] args) {
		//JTextFieldSearch使用示例
		String[] strs=new String[]{"123","aaa","bbb","ccc","aab","aacba","321","3312","你好","你好123"};
		final JTextFieldSearch jtfs=new JTextFieldSearch(strs);
		jtfs.setColumns(15);
		
		final JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(jtfs,"Center");
		f.pack();
		f.setVisible(true);
		//*正确的调用：
		jtfs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(!jtfs.getIsHideDropDown()){//注意：这里用来判断是否是用来隐藏下拉的操作，如果不是才能触发事件
					//这里放入要处理事件的代码，此处仅仅是弹出一个对话框，对话框内显示选中的内容。
					JOptionPane.showMessageDialog(f, jtfs.getText());
				}
			}
		});//*/
		/*//错误的调用：不良好的客户感觉
		jtfs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(f, jtfs.getText());
			}
		});//*/
	}
	private String[] items=new String[0];
	private int dropDownRowCount=8;//弹出备选框的行数
	private boolean isFilterMode=false;//过滤模不匹配或是导航到匹配
	private boolean isHideDropDown=false;//告知此次对TextField的确认(回车)操作是否是用来隐藏下拉提示菜单的
	/*---------界面元素---------*/
	public JList jlistReservior=new JList();
	public JScrollPane jscroll4List=new JScrollPane(jlistReservior);
	public JPopupMenu jpmenu4List=new JPopupMenu();
	public JTextFieldSearch(){
		//初始化界面
		jpmenu4List.add(jscroll4List);
		//初始化事件响应
		getDocument().addDocumentListener(contentChange);
		addKeyListener(inputKeyListener);
		addComponentListener(resizeListener);
		addMouseListener(doubleClickDropDown);
		jlistReservior.addMouseMotionListener(mouseOverShow);
		jlistReservior.addMouseListener(clickSelect);
	}
	public JTextFieldSearch(String[] strs){
		this();
		setItems(strs);
	}
	public JTextFieldSearch(String[] strs,int dropDownRowCount,boolean isFilterMode){
		this(strs);
		setDropDownRowCount(dropDownRowCount);
		setIsFilterMode(isFilterMode);
	}
	//内容变化
	private DocumentListener contentChange=new ContentChange();
	class ContentChange implements DocumentListener{
		public void changedUpdate(DocumentEvent e) {inputChanged();}
		public void insertUpdate(DocumentEvent e) {inputChanged();}
		public void removeUpdate(DocumentEvent e) {inputChanged();}
		private void inputChanged(){
			String txt=getText().toLowerCase();
			if(txt.equals(""))return;//内容为空时不弹出下拉提示，为PointInfo项目特殊需要。
			showPopup();
			if(isFilterMode){
				if(items==null){
					throw new RuntimeException("please use setItems() to initialize items for select。");
				}
				List fileteredList=new Vector();//<String>
				for(int i=0;i<items.length;i++){
					if(items[i].toLowerCase().startsWith(txt)){
						fileteredList.add(items[i]);
					}
				}
				jlistReservior.setListData(fileteredList.toArray());
				jlistReservior.setSelectedIndex(0);
			}else{
				jlistReservior.setSelectedIndex(-1);
				for(int i=0;i<items.length;i++){
					if(items[i].toLowerCase().startsWith(txt)){
						jlistReservior.setSelectedValue(items[i], true);
						return;
					}
				}
			}
		}
	}
	//双击弹出下拉提示
	private DoubleClickDropDown doubleClickDropDown=new DoubleClickDropDown();
	class DoubleClickDropDown extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getButton()==MouseEvent.BUTTON1 
					&& e.getClickCount()==2){
				showPopup();
			}
		}
	}
	//点击弹出下拉，选中内容
	private ClickSelect clickSelect=new ClickSelect();
	class ClickSelect extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			selectInPopup();
		}
	}
	//键盘输入监听处理上，下键;
	private KeyListener inputKeyListener=new InputKeyListener();
	class InputKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int selected=jlistReservior.getSelectedIndex();
			if(e.getKeyCode()==KeyEvent.VK_UP){
				if(selected>0){
					jlistReservior.setSelectedIndex(selected-1);
					scrollToVisible(selected-1);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				if(!jpmenu4List.isVisible()){
					showPopup();
				}else{
					if(selected<(jlistReservior.getModel().getSize()-1)){
						jlistReservior.setSelectedIndex(selected+1);
						scrollToVisible(selected+1);
					}
				}
			}else if(e.getKeyCode()==KeyEvent.VK_ENTER){
				if(jpmenu4List.isVisible()){
					selectInPopup();
					isHideDropDown=true;
				}else{
					isHideDropDown=false;
				}
			}
		}
	}
	//下拉菜单的鼠标划过选中
	private MouseMotionAdapter mouseOverShow=new MouseOverSelect();
	class MouseOverSelect extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e){
			int selected=jlistReservior.getSelectedIndex();
			int current=jlistReservior.locationToIndex(e.getPoint());
			if(selected!=current)
				jlistReservior.setSelectedIndex(current);
		}
	}
	//组件尺寸改变时变化下拉框尺寸
	private ComponentListener resizeListener=new ResizeListener();
	class ResizeListener extends ComponentAdapter{
		public void componentResized(ComponentEvent e){
			refreshSize();
		}
	}
	protected void showPopup(){
		//刷新下拉框中的内容及选中行
		jpmenu4List.show(this, 0, getHeight());
		requestFocus();
	}
	protected void refreshSize(){
		//刷新下拉框应有的宽度和高度
		if(items!=null && items.length>0){
			int rowHeight=(int)jlistReservior.getCellBounds(0, 0).getHeight();
			if(rowHeight<=0)rowHeight=20;
			int height=rowHeight*dropDownRowCount+3;
			Dimension size=new Dimension(getWidth(), height);
			jscroll4List.setPreferredSize(size);
		}
	}
	protected void refreshItems(){
		//在导航模式时，下拉框中的内容应该是有序的
		if(items!=null && items.length>0){
			Comparator cmp = Collator.getInstance(Locale.getDefault());
			java.util.Arrays.sort(items, cmp); 
		}
		jlistReservior.setListData(items);
	}
	protected void selectInPopup(){
		//从下拉框中选中内容
		if(jlistReservior.getSelectedIndex()>=0 && 
				jlistReservior.getSelectedIndex()<jlistReservior.getModel().getSize()){
			setText(jlistReservior.getSelectedValue().toString());
			
		}
		jpmenu4List.setVisible(false);
	}
	protected void scrollToVisible(int idx){
		//滚动到可见
		if(jlistReservior.getParent() instanceof JViewport){
			JViewport vp=(JViewport)jlistReservior.getParent();
			Rectangle rect = jlistReservior.getCellBounds(idx, idx);
	        Point pt = vp.getViewPosition();
	        rect.setLocation(rect.x - pt.x, rect.y - pt.y);
	        vp.scrollRectToVisible(rect);
		}
	}
	/*---------------属性--------------*/
	/**
	 * 设定过滤模式或者导航模式
	 */
	public void setIsFilterMode(boolean isFilter){
		isFilterMode=isFilter;
		refreshItems();
	}
	public boolean getIsFilterMode(){
		return isFilterMode;
	}
	/**
	 * 设定备选内容
	 */
	public void setItems(String[] strs){
		items=strs;
		refreshItems();
	}
	public String[] getItems(){
		return items;
	}
	/**
	 * 设定下拉框内显示备选内容的行数
	 * @param rowCount 下拉框内显示备选内容的行数
	 */
	public void setDropDownRowCount(int rowCount){
		this.dropDownRowCount=rowCount;
		refreshSize();
		validate();
		repaint();
	}
	public int getDropDownRowCount(){
		return dropDownRowCount;
	}
	/**
	 * 处理下拉菜单。一般用于调用时添加对回车或者ActionListener处理时，判断此次操作时用来隐藏下拉菜单(true)的，还是确认输入(false)的。
	 * @return 如果返回true表明此次操作是隐藏下拉菜单，否则返回false。
	 */
	public boolean getIsHideDropDown(){
		return isHideDropDown;
	}
}
