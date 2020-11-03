package com.tlw.swing.jtree;
import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
public class IconTreeCellRenderer extends DefaultTreeCellRenderer {
    private static final long serialVersionUID = 1L;
    //请参照DefaultTreeCellRenderer源码查看区别;
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        String stringValue = tree.convertValueToText(value, sel,
                  expanded, leaf, row, hasFocus);
        this.hasFocus = hasFocus;
        IiconAble data=isIconAble(value);
        setText(stringValue);
        if(sel)
            setForeground(getTextSelectionColor());
        else
            setForeground(getTextNonSelectionColor());
        if (!tree.isEnabled()) {
            setEnabled(false);
            if(data!=null){
                setDisabledIcon(data.getIconBySize(20,20));
            }else if (leaf){
                setDisabledIcon(getLeafIcon());
            }else if (expanded){
                setDisabledIcon(getOpenIcon());
            }else{
                setDisabledIcon(getClosedIcon());
            }
       }else {
           setEnabled(true);
           if(data!=null){
               setIcon(data.getIconBySize(20, 20));
           }else if(leaf){
               setIcon(getLeafIcon());
           } else if(expanded){
               setIcon(getOpenIcon());
           } else{
               setIcon(getClosedIcon());
           }
       }
        setComponentOrientation(tree.getComponentOrientation());
        selected = sel;
        return this;
    }
    public static IiconAble isIconAble(Object defaultMutableTreeNode){
        DefaultMutableTreeNode node=(DefaultMutableTreeNode)defaultMutableTreeNode;
        Object userObj=node.getUserObject();
        if(userObj!=null){
            if(userObj instanceof IiconAble){
                return (IiconAble)userObj;
            }
        }
        return null;
    }
}
