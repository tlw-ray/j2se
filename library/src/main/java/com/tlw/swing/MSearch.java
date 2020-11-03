package com.tlw.swing;

import java.awt.BorderLayout;
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

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/*******************************
Author:tlw
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:自动提示输入内容，比JTextFieldSearch复杂一些
导航模式，过滤模式
导航模式：当必须从备选项中选择一项时使用导航模式。
过滤模式：当不必从备选项中选择一项，备选项仅仅供参考提示的情况使用过滤模式。
弹出模式，选择模式

 ********************************/
public class MSearch extends JComponent {
	private static final long serialVersionUID = -2371045114180199147L;
	/*---------user interface element-----------*/
	private JTextField jtfInput=new JTextField();
	private JList jlistReservior=new JList();
	private JScrollPane jscroll4List=new JScrollPane(jlistReservior);
	private JPopupMenu jpmenu4List=new JPopupMenu();
	/*---------self property--------------*/
	private boolean isDropDownMode=true;//下拉模式对应静态呈现模式
	private boolean isFilterMode=true;//过滤模式对应导航模式
	private int rows=8;//弹出备选框的行数
	private String[] items;//备选内容
	private ActionListener actionPerform;//当用户确认选中内容时执行的
	/*---------constructor--------------*/
	public MSearch(){
		initUI();
		initEvent();
	}
	public MSearch(String[] strs){
		initUI();
		initEvent();
		setItems(strs);
	}
	private void initUI(){
		jtfInput.setColumns(20);
		setLayout(new BorderLayout());
		add(jtfInput,"North");
		refreshDropMode();
	}
	private void initEvent(){
		jtfInput.getDocument().addDocumentListener(inputChange);
		jtfInput.addKeyListener(inputKeyListener);
		jtfInput.addActionListener(inputActionListener);
		jtfInput.addComponentListener(resizeListener);
		jlistReservior.addMouseMotionListener(mouseOverShow);
		jlistReservior.addMouseListener(clickSelect);
	}
	//filter items which start with user input;
	protected void doFilter(){
		String txt=jtfInput.getText().toLowerCase();
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
	//refresh and popup the list;
	protected void refreshPopup(){
		if(isDropDownMode){
			if(jpmenu4List==null){
				jpmenu4List=new JPopupMenu();
				jpmenu4List.add(jscroll4List);
			}
			jpmenu4List.show(jtfInput, jtfInput.getX(), jtfInput.getY()+jtfInput.getHeight());
			jtfInput.requestFocus();
		}
	}
	//listening user jtfInput change;
	private DocumentListener inputChange=new InputChange();
	class InputChange implements DocumentListener{
		public void changedUpdate(DocumentEvent e) {inputChanged();}
		public void insertUpdate(DocumentEvent e) {inputChanged();}
		public void removeUpdate(DocumentEvent e) {inputChanged();}
		private void inputChanged(){
			doFilter();
			refreshPopup();
		}
	}
	//listening user mouse double click jtfInput
	private DoubleClickDropDown clickDropDown=new DoubleClickDropDown();
	class DoubleClickDropDown extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getButton()==MouseEvent.BUTTON1 
					&& e.getClickCount()==2){
				refreshPopup();
			}
		}
	}
	//listening user click drop down list menu ,select item;
	private ClickSelect clickSelect=new ClickSelect();
	class ClickSelect extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			selectInPopup();
		}
	}
	protected void selectInPopup(){
		if(jlistReservior.getSelectedIndex()>=0 && 
				jlistReservior.getSelectedIndex()<jlistReservior.getModel().getSize()){
			jtfInput.setText(jlistReservior.getSelectedValue().toString());
			
		}
		jpmenu4List.setVisible(false);
	}
	//listening user control key;
	private KeyListener inputKeyListener=new InputKeyListener();
	class InputKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int selected=jlistReservior.getSelectedIndex();
			if(e.getKeyCode()==KeyEvent.VK_UP){
				if(selected>0){
					int nowSelected=selected-1;
					jlistReservior.setSelectedIndex(nowSelected);
					scrollToVisible(nowSelected);
				}
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN){
				int nowSelected=jlistReservior.getSelectedIndex();
				if(isDropDownMode && !jpmenu4List.isVisible()){
					refreshPopup();
				}else{
					if(selected<(jlistReservior.getModel().getSize()-1)){
						nowSelected+=1;
						jlistReservior.setSelectedIndex(nowSelected);
						scrollToVisible(nowSelected);
					}
				}
			}else if(e.getKeyCode()==KeyEvent.VK_ENTER){
				//过滤意味着以用户输入内容为主，能从备选中选择选，否则不选
				//导航意味着以备选内容为主，必须从备选中选
				if(isDropDownMode && jpmenu4List.isVisible()){
					//如果是下拉模式必须先处理下拉的隐藏
					selectInPopup();
					return;
				}else{
					if(jlistReservior.getModel().getSize()>0 && jlistReservior.getSelectedIndex()>-1){
						String inputStr=jtfInput.getText();
						String selectStr=jlistReservior.getSelectedValue().toString();
						if(!inputStr.equalsIgnoreCase(selectStr)){
							//如果有选中内容并且内容与选中内容不同，应先使内容与选中内容相同
							jtfInput.setText(selectStr);
							return;
						}
					}
				}
				if(actionPerform!=null)
					actionPerform.actionPerformed(null);
			}
		}
	}
	//对输入信息确认的Action也就是输入文本框回车的响应
	private ActionListener inputActionListener=new ActionListenerInput();
	class ActionListenerInput implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	//listening list mouse over select
	private MouseMotionAdapter mouseOverShow=new MouseOverSelect();
	class MouseOverSelect extends MouseMotionAdapter{
		public void mouseMoved(MouseEvent e){
			int selected=jlistReservior.getSelectedIndex();
			int current=jlistReservior.locationToIndex(e.getPoint());
			if(selected!=current)
				jlistReservior.setSelectedIndex(current);
		}
	}
	//listening when jtfInput resize
	private ComponentListener resizeListener=new ResizeListener();
	class ResizeListener extends ComponentAdapter{
		public void componentResized(ComponentEvent e){
			refreshSize();
		}
	}
	/*--------------weather drop down mode---------------------*/
	public boolean getIsDropDown(){
		return isDropDownMode;
	}
	/**
	 * 设定下拉模式，或固定模式。设定后自动刷新界面。
	 * @param dropDown true 表示下拉模式，false 表示固定模式
	 */
	public void setIsDropDownModel(boolean dropDown){
		isDropDownMode=dropDown;
		refreshDropMode();
		validate();
		repaint();
	}
	protected void refreshDropMode(){
		if(isDropDownMode){
			remove(jscroll4List);
			jpmenu4List.add(jscroll4List);
			jtfInput.addMouseListener(clickDropDown);
		}else{
			jpmenu4List.remove(jscroll4List);
			add(jscroll4List,"Center");
			jtfInput.removeMouseListener(clickDropDown);
		}
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
	/*--------------------drop down items-------------------------*/
	/**
	 * 设定下备选内容
	 */
	public void setItems(String[] strs){
		items=strs;
		refreshItems();
	}
	public String[] getItems(){
		return items;
	}
	protected void refreshItems(){
		//if is filter mode ,items may be ordered;
		if(items!=null && items.length>0){
			Comparator cmp = Collator.getInstance(Locale.getDefault());
			java.util.Arrays.sort(items, cmp); 
		}
		jlistReservior.setListData(items);
	}
	/*---------------------weather filter mode------------------------*/
	/**
	 * 设定过滤模式或者导航模式
	 */
	public void setIsFilter(boolean isFilter){
		isFilterMode=isFilter;
		refreshItems();
	}
	public boolean getIsFilterMode(){
		return isFilterMode;
	}
	/*---------------------size property---------------------------------*/
	public int getRows(){
		return rows;
	}
	public void setRows(int rowCount){
		rows=rowCount;
		refreshSize();
		validate();
		repaint();
	}
	public int getColumns(){
		return jtfInput.getColumns();
	}
	public void setColumns(int colCount){
		jtfInput.setColumns(colCount);
		validate();
		repaint();
	}
	protected void refreshSize(){
		if(items!=null && items.length>0){
			int rowHeight=(int)jlistReservior.getCellBounds(0, 0).getHeight();
			if(rowHeight<=0)rowHeight=20;
			int height=rowHeight*rows+3;
			Dimension size=new Dimension(jtfInput.getWidth(), height);
			jscroll4List.setPreferredSize(size);
		}
	}
	/*--------------------input text property-------------------*/
	public String getText(){
		return jtfInput.getText();
	}
	public void setText(String text){
		jtfInput.setText(text);
	}
	/*-------------------get text component---------------------*/
	public JTextField getJTextFieldInput(){return jtfInput;}
	/*-------------------get list component---------------------*/
	public JList getJListReservior(){return jlistReservior;}
	/*-------------------get popup component -------------------*/
	public JPopupMenu getJPopupMenu4List(){return jpmenu4List;}
	/*-------------------get perform action --------------------*/
	public ActionListener getActionPerform(){return actionPerform;}
	public void setActionPerform(ActionListener action){actionPerform=action;}
}