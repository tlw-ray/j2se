package com.tlw.eg.swing.popup;
/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-23
Description:
 ********************************/
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
public class JWindowPopup extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JWindowPopup() {
        initComponents();
        new TextChooser(this,textArea);
    }
    private void initComponents() {
        setTitle("Text-Chooser Demo");
        textArea = new JTextArea();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().add(new JLabel("   Right-click to choose a text"), BorderLayout.NORTH);
        getContentPane().add(textArea, BorderLayout.CENTER);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-500)/2, (screenSize.height-350)/2, 500, 350);
    }
    public static void main(String args[]) {
        new JWindowPopup().setVisible(true);
    }
    private JTextArea textArea;
}
class TextChooser extends JWindow {
	private static final long serialVersionUID = 1L;
	public TextChooser(JFrame parent, JTextArea textArea){
        super(parent);
        this.parent=parent;
        this.textArea=textArea;
        initChooser();
        this.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent e) {
			}

			@Override
			public void focusLost(FocusEvent e) {
				System.out.println(e);
			}
        	
        });
    }
    private void initChooser(){
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent evt) {
                textAreaMouseReleased(evt);
            }
        });
        popupScrollPane = new JScrollPane();
        popupList = new JList();
        popupList.setModel(new AbstractListModel() {
			private static final long serialVersionUID = 1L;
			String[] strings = { "java ", "is ", "cool ", "and ", "nice ", "to ", "have ", "with ", "no ", "error ", ".\n" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        popupList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                popupListAction(evt);
            }
        });
        popupScrollPane.setViewportView(popupList);
        add(popupScrollPane);
    }
    private void popupListAction(ListSelectionEvent evt) {
        if (evt.getValueIsAdjusting() == false) {
            textArea.append((String)popupList.getSelectedValue());
            try{
                textArea.setCaretPosition(textArea.getLineEndOffset(textArea.getLineCount()-1));
            }catch(BadLocationException ex){ex.printStackTrace();}
            setVisible(false);
            textArea.requestFocus();
        }
    }
    private void textAreaMouseReleased(MouseEvent evt) {
        Point pnt = evt.getPoint();
        int x = (int)pnt.getX()+(int)parent.getX();
        int y = (int)pnt.getY()+(int)parent.getY()+20;
        if( !contains(x,y) ){
            setVisible(false);
        }
        if(evt.isPopupTrigger()){
            setBounds(x,y,200,100);
            setVisible(true);
        }
    }
    private JFrame parent;
    private JTextArea textArea;
    private JScrollPane popupScrollPane;
    private JList popupList;
}

