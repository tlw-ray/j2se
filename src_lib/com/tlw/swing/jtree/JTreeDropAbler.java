/*
 * Created on 2006-8-30
 *
 *
 */
package com.tlw.swing.jtree;
import java.awt.Point;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
public class JTreeDropAbler {
    public JTreeDropAbler(JTree tree){
        new DropTarget(tree,DnDConstants.ACTION_COPY_OR_MOVE, new TreeCopyDragTargeter(tree));
    }
}
//拷贝拖放操作,用于增量修改，无论如何拖放都是对节点的COPY操作；
class TreeCopyDragTargeter extends DropTargetAdapter {
    JTree tree;
    public TreeCopyDragTargeter(JTree sender) {
        tree=sender;
    }
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY); // accept drop
        Point ap = dtde.getLocation(); // get event point
        int x = (int) ap.getX();
        int y = (int) ap.getY();
        DefaultMutableTreeNode parnode;
        TreePath tp = tree.getPathForLocation(x, y);
        if (tp != null) {
            try {
                // get drop tree node with treepath
                DefaultMutableTreeNode no1 = (DefaultMutableTreeNode) tp.getLastPathComponent();
                parnode = no1;
                int index = parnode.getChildCount();
                // get drop tree mode
                DefaultTreeModel tm = (DefaultTreeModel) tree.getModel();
                DefaultMutableTreeNode node2 = new DefaultMutableTreeNode();
                node2 = UtilJTree.reserializeTreeNode("DragNode.OBJ");
                tm.insertNodeInto(node2, parnode, index);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
