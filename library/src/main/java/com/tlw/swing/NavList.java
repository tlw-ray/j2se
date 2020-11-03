package com.tlw.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.tlw.text.Helper;


public class NavList extends JPanel {
	private static final long serialVersionUID = -3702108912329866243L;
	public NavList() {
        try {
            jbInit();
            myInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println(Helper.getBeginCharacter("啊aAbB123.!"));

        JFrame f = new JFrame();
        NavList nl=new NavList();
        String[] strs=com.tlw.text.RandomChr.getRandomCHSString(1000,5);
        strs[0]="aaaa";
        strs[1]="2222";
        strs[2]="bbbb";
        strs[3]="ABCD";
        strs[4]="AbCd";
        strs[5]="aBcD";
        strs[6]="aB12,3c!.";
        nl.setStrings(strs);
        f.add(nl,BorderLayout.CENTER);
        f.setSize(300,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
    SortListModel slm=new SortListModel();
    private void myInit(){
        jList1.setModel(slm);
        jTextField1.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e) {
                int keyCode=e.getKeyCode();
                if(keyCode==KeyEvent.VK_UP){
                    int select=jList1.getSelectedIndex();
                    if(select>0) jList1.setSelectedIndex(select-1);
                }else if(keyCode==KeyEvent.VK_DOWN){
                    jList1.setSelectedIndex(jList1.getSelectedIndex()+1);
                }else if(keyCode==KeyEvent.VK_LEFT||keyCode==KeyEvent.VK_RIGHT){
                }else{
                    slm.doFilter(jTextField1.getText().toLowerCase());
                    jList1.updateUI();
                }
            }
        });
    }
    private void jbInit() throws Exception {
        jTextField1.setBorder(BorderFactory.createEtchedBorder());
        jTextField1.setText("");
        this.setLayout(borderLayout1);
        jList1.setBorder(BorderFactory.createLineBorder(Color.black));
        add(jScrollPane1, java.awt.BorderLayout.CENTER);
        jScrollPane1.getViewport().add(jList1);
        add(jTextField1, java.awt.BorderLayout.NORTH);
    }
    public void setStrings(String[] strs){
        slm.setItems(strs);
    }
    public JList getJList(){
        return jList1;
    }
    JTextField jTextField1 = new JTextField();
    BorderLayout borderLayout1 = new BorderLayout();
    JList jList1 = new JList();
    JScrollPane jScrollPane1 = new JScrollPane();
    class SortListModel extends AbstractListModel{
		private static final long serialVersionUID = -143018082983160629L;
		public String[] items;
        public String[] itemspy;
        //<String>
        public Vector showItem=new Vector();
        Random ran=new Random();
        public SortListModel(){}
        public SortListModel(String[] youItems){
            setItems(youItems);
        }
        public void setItems(String[] strs){
            itemspy=new String[strs.length];
            showItem.clear();
            items=strs;
            for(int i=0;i<strs.length;i++){
                showItem.add(strs[i]);
                itemspy[i]=com.tlw.text.Helper.getBeginCharacter(strs[i]);
            }
        }
        public int getSize() {
            return showItem.size();
        }
        public Object getElementAt(int index) {
            return showItem.get(index);
        }
        public void doFilter(String py){
            //根据py内容过滤出符合的答案
            showItem.clear();
            for (int i = 0; i < items.length; i++) {
                int k=itemspy[i].indexOf(py);
                //System.out.println("item:"+items[i]+" py:"+py+" indexOf:"+k);
                if (k == 0) {
                    showItem.add(items[i]);
                }
            }
            //System.out.println("-----------------");
        }
    }
}
