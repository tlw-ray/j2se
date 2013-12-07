package com.tlw.swing.jtree.autotree;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;

class DnDCellRendererProxy extends Component implements TreeCellRenderer {
    private static final long serialVersionUID = 8013034362106928179L;
    private TreeCellRenderer originalTreeCellRenderer;
    private DnDBorderFactory borderFactory;
    private DefaultMutableTreeNode[] draggedNodes;
    private DefaultMutableTreeNode dropNode;
    private boolean fetchBorder;
    private Border originalBorder;
    private Point currentMouse;
    public DnDCellRendererProxy(TreeCellRenderer trueCellRenderer) {
        originalTreeCellRenderer = trueCellRenderer;
        borderFactory = new DnDBorderFactory();
        fetchBorder = true;
    }
    public TreeCellRenderer getOriginalTreeCellRenderer() {
        return originalTreeCellRenderer;
    }
    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {
        Component c = originalTreeCellRenderer.getTreeCellRendererComponent(
                tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode nodeToRender = (DefaultMutableTreeNode) value;
        if (c instanceof JComponent) {
            if (fetchBorder) {
                fetchBorder = false;
                originalBorder = ((JComponent) c).getBorder();
            }
            //jc是nodeToRender在树内显示的组件,一般是JLabel;
            JComponent jc = (JComponent) c;
                if(nodeToRender.equals(dropNode)){
                    if(!DnDUtils.dropAllow(draggedNodes,nodeToRender)){
                        jc.setBorder(borderFactory.getDropNotAllowedBorder());
                    }else if(DnDUtils.dropBehindOrDropIn(tree,row,currentMouse)){
                        jc.setBorder(borderFactory.getDropInAllowedBorder());
                    }else{
                        jc.setBorder(borderFactory.getDropBehindAllowedBorder());
                    }
                }else{
                    jc.setBorder(originalBorder);
                }
        }
        return c;
    }
    public DefaultMutableTreeNode[] getDraggedNodes() {
        return this.draggedNodes;
    }
    //在Drag操作的开始设定draggedNode;
    public void setDraggedNodes(DefaultMutableTreeNode[] draggedNodes) {
        this.draggedNodes = draggedNodes;
    }
    public TreeNode getDropNode() {
        return this.dropNode;
    }
    //在拖动过程中鼠标经过时设定dropNode;
    public void setDropNode(DefaultMutableTreeNode dropNode) {
        this.dropNode = dropNode;
    }
    public void setCurrentMouse(Point currentMouse) {
        this.currentMouse = currentMouse;
    }
}
