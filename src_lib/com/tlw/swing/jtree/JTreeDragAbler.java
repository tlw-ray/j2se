/*
 * Created on 2006-8-30
 */
package com.tlw.swing.jtree;
import java.awt.datatransfer.StringSelection;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;

import javax.swing.JTree;
import javax.swing.tree.TreePath;
public class JTreeDragAbler {
    public JTreeDragAbler(JTree tree){
        DragSource dragSource=DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(tree,
                DnDConstants.ACTION_COPY_OR_MOVE, new TreeCopyDragSourcer(tree));
    }
}
//为JTree实现最普遍的可拖出接口,仅仅是把要拖动的单一节点对象拷贝出来;
class TreeCopyDragSourcer extends DragSourceAdapter implements DragGestureListener {
    private JTree tree;
    public TreeCopyDragSourcer(JTree sender) {
        tree=sender;
    }
    public void dragGestureRecognized(DragGestureEvent dge) {
        java.awt.Point jap = dge.getDragOrigin(); // drag point
        int x = (int) jap.getX();
        int y = (int) jap.getY();
        TreePath tp = tree.getPathForLocation(x, y);
        // get drag node path
        UtilJTree.serializeTreeNode(tp,"DragNode.OBJ");
        // event start
        dge.startDrag(DragSource.DefaultCopyDrop, new StringSelection("drag"), this);
    }
}
//为JTree实现最普遍的可拖出接口,仅仅是把要拖动的单一节点对象拷贝出来;
class TreeCopyMoveDragSourcer extends DragSourceAdapter implements DragGestureListener {
    private JTree tree;
    public TreeCopyMoveDragSourcer(JTree sender) {
        tree=sender;
    }
    public void dragGestureRecognized(DragGestureEvent dge) {
        java.awt.Point jap = dge.getDragOrigin(); // drag point
        int x = (int) jap.getX();
        int y = (int) jap.getY();
        TreePath tp = tree.getPathForLocation(x, y);
        // get drag node path
        UtilJTree.serializeTreeNode(tp,"DragNode.OBJ");
        // event start
        dge.startDrag(DragSource.DefaultCopyDrop, new StringSelection("drag"), this);
    }
}
