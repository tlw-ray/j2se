/*
 * Created on 2006-9-19
 */
package com.tlw.swing.jtree.autotree;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
public class Example1 extends JFrame {
    private static final long serialVersionUID = 1L;
    public static void main(String[] args) {
        Example1 t = new Example1();
        t.setVisible(true);
    }
    JTree tree1;
    JTree tree2;
    public Example1() {
        DefaultMutableTreeNode root=new DefaultMutableTreeNode("root");
        //Init Tree Data
        for(int i=0;i<5;i++){
            DefaultMutableTreeNode inode=new DefaultMutableTreeNode();
            inode.setUserObject(new Integer(i));
            for(int j=0;j<5;j++){
                DefaultMutableTreeNode jnode=new DefaultMutableTreeNode();
                inode.setUserObject(new Integer(j));
                for(int k=0;k<5;k++){
                    DefaultMutableTreeNode knode=new DefaultMutableTreeNode();
                    inode.setUserObject(new Integer(k));
                    jnode.add(knode);
                }
                inode.add(jnode);
            }
            root.add(inode);
        }

        //添加dnd控制按钮
        final JToggleButton dndAble=new JToggleButton("DND开关");
        dndAble.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                at1.setDndAble(dndAble.isSelected());
                at2.setDndAble(dndAble.isSelected());
            }
        });
        this.add(dndAble,"North");

        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JSplitPane jsp = new JSplitPane();
        this.add(jsp);
        tree1=new JTree(root);
        tree2=new JTree(com.tlw.swing.jtree.UtilJTree.cloneDeeply(root));
        JScrollPane jsb1=new JScrollPane(tree1);
        JScrollPane jsb2=new JScrollPane(tree2);
        jsp.add(jsb1, JSplitPane.LEFT);
        jsp.add(jsb2, JSplitPane.RIGHT);
        at2=new JTreeWrapperDragDtop(tree2);
        at1=new JTreeWrapperDragDtop(tree1);
    }
    JTreeWrapperDragDtop at1,at2;
}
