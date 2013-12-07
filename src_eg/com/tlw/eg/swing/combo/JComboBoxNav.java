package com.tlw.eg.swing.combo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;

import com.tlw.util.UtilUi;


/**
@since 2011-1-17
@author 唐力伟 (tlw_ray@163.com QQ:22348536)
 */
public class JComboBoxNav extends JComboBox{
	private static final long serialVersionUID = 1142105648825840451L;
	public static void main(String[] args) {
		UtilUi.initUIManager();
		String[] patternExamples = {
		         "AAA",
		         "BBB",
		         "aaa",
		         "你好",
		         "再见",
		};
		final JComboBoxNav patternList = new JComboBoxNav();
		patternList.setEditable(true);
		patternList.setItems(Arrays.asList(patternExamples));
		patternList.refreshUI();
		UtilUi.show(patternList, 300, 100, "");
	}
	{
		if(getEditor().getEditorComponent() instanceof JTextComponent){
			JTextComponent editor=(JTextComponent)getEditor().getEditorComponent();
			editor.getDocument().addDocumentListener(new DocumentListener(){
				public void changedUpdate(DocumentEvent e) {
					textChanged(e);}
				public void insertUpdate(DocumentEvent e) {
					textChanged(e);}
				public void removeUpdate(DocumentEvent e) {
					textChanged(e);}
				private void textChanged(DocumentEvent e){try {
					doNavigate(e.getDocument().getText(0, e.getDocument().getLength()));
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}}
			});
		}
	}
	List items;
	Comparator comparator;
	public void setItems(List items){
		this.items=items;
	}
	public List getItems(){
		return items;
	}
	public Comparator getComparator() {
		return comparator;
	}
	public void setComparator(Comparator comparator) {
		this.comparator = comparator;
	}
	public void refreshUI(){
		if(comparator==null){
			Collections.sort(items);
		}else{
			Collections.sort(items, comparator);
		}
		DefaultComboBoxModel model=new DefaultComboBoxModel(new Vector(items));
		setModel(model);
	}
	private void doNavigate(String nav){
		//do navigate;
		System.out.println(nav);
		if(this.isVisible())setPopupVisible(true);
	}
}