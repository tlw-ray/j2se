// Created on 2008-6-16
package com.tlw.swing;

import java.util.List;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

public class NullAbleComboBoxModel extends AbstractListModel implements ComboBoxModel{
    private static final long serialVersionUID = -328666438632371768L;
    public static void main(String[] args){
    	//<String>
        Vector v=new Vector();
        for(int i=0;i<10;i++){
            v.add(i+"");
        }
        NullAbleComboBoxModel model=new NullAbleComboBoxModel(v);
        JComboBox combo=new JComboBox();
        combo.setModel(model);
        Shower.show(combo,200,50);
        combo.setSelectedItem("3");
    }
    List list;
    Object selectedItem;
   public NullAbleComboBoxModel(List items){
        list=items;
    }
    public Object getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Object anItem) {
        for(int i=0;i<list.size();i++){
        	Object item=list.get(i);
            if(item.equals(anItem)){
                selectedItem=item;
                return;
            }
        }
        selectedItem=null;
    }

    public Object getElementAt(int index) {
        if(index<=0)return null;
        return list.get(index-1);
    }

    public int getSize() {
        return list.size()+1;
    }
}
