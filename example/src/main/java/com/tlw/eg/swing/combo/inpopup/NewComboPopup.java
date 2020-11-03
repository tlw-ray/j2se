package com.tlw.eg.swing.combo.inpopup;

import java.awt.Container;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.Popup;
import javax.swing.plaf.basic.BasicComboPopup;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-12-29
@version:2009-12-29
Description:
 */
public class NewComboPopup extends BasicComboPopup{
	private static final long serialVersionUID = 8329936018356759430L;
	JComboBox combo;
    private MenuElement[]elements;
    /** Creates a new instance of MonthComboPopup */
    public NewComboPopup(JComboBox combo) {
        super(combo);
        this.combo=combo;
    }
    public void setVisible(boolean b) {
        if (b == isVisible())
            return;
        if(b) {
            if (isPopupMenu()) {
                if(getSubElements().length>0){
                    MenuElement me[] = new MenuElement[2];
                    me[0]=getSubElements()[0];
                    me[1]=(MenuElement)this;
                    MenuSelectionManager.defaultManager().setSelectedPath(me);
                }else{
                    MenuElement me[] = new MenuElement[1];
                    me[0]=(MenuElement)this;
                    MenuSelectionManager.defaultManager().setSelectedPath(me);
                }
            }
            firePopupMenuWillBecomeVisible();
            setThePopup(invokeGetPopup());
            firePropertyChange("visible", Boolean.FALSE, Boolean.TRUE);
        } else{
            Boolean doCanceled = (Boolean)getClientProperty("JPopupMenu.firePopupMenuCanceled");
            if (doCanceled != null && doCanceled == Boolean.TRUE) {
                putClientProperty("JPopupMenu.firePopupMenuCanceled", Boolean.FALSE);
                firePopupMenuCanceled();
            }
            getSelectionModel().clearSelection();
            if(getThePopup() != null) {
                Popup p=getThePopup();
                firePopupMenuWillBecomeInvisible();
                p.hide();
                setThePopup(null);
                firePropertyChange("visible", Boolean.TRUE, Boolean.FALSE);
            }
        }
    }
    public void hide() {
        MenuSelectionManager manager = MenuSelectionManager.defaultManager();
        MenuElement [] selection = manager.getSelectedPath();
        Vector<MenuElement> tmp=new Vector<MenuElement>();
        for ( int i = 0 ; i < selection.length ; i++ ) {
            if ( selection[i] != this )
                tmp.add(selection[i]);
        }
        selection=new MenuElement[tmp.size()];
        for(int i=0;i<tmp.size();i++)
            selection[i]=tmp.get(i);
        manager.setSelectedPath(selection);
        comboBox.repaint();
    }
    private boolean isPopupMenu() {
        return !(getInvoker() instanceof JMenu);
    }
    void setThePopup(Popup p){
        try{
            popupField.set(this, p);
        }catch(Exception e){}
    }
    Popup getThePopup(){
        try{
            return (Popup)popupField.get(this);
        }catch(Exception e){}
        return null;
    }
    Popup invokeGetPopup(){
        try{
            return (Popup)getPopupMethod.invoke(this);
        }catch(Exception e){
            return null;
        }
    }
    static Field popupField;
    static Method getPopupMethod;
    static Class popupClass=JPopupMenu.class;
    static{
        try{
            popupField=popupClass.getDeclaredField("popup");
            popupField.setAccessible(true);
            getPopupMethod=popupClass.getDeclaredMethod("getPopup");
            getPopupMethod.setAccessible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void initialize(final JComboBox combo) {
        Container parent=combo.getParent();
        Vector<MenuElement> tmp = new Vector<MenuElement>();
        while(parent!=null){
            if(parent instanceof MenuElement){
                tmp.add((MenuElement)parent);
            }
            parent=parent.getParent();
        }
        elements=new MenuElement[tmp.size()];
        for(int i=0;i<tmp.size();i++)
            elements[i]=tmp.get(i);
    }
    public MenuElement[] getSubElements() {
        if(elements==null)
            initialize(combo);
        return elements;
    }
}