/*
 * Created on 2006-9-12
 */
package com.tlw.swing.jtree.treewrapper;

import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
/**
 * DnDCellRendererProxy is a TreeCellRenderer that proxies operations to a true
 * TreeCellRenderer, but that draws a border around specific TreeNodes.
 *
 * @author Antonio Vieiro (antonio@antonioshome.net), $Author: antonio $
 * @version $Revision: 1.4 $
 */
class DnDCellRendererProxy extends Component implements TreeCellRenderer,DropState {
    private static final long serialVersionUID = 8013034362106928179L;
    private int dropState;
    private TreeCellRenderer originalTreeCellRenderer;
    private DnDBorderFactory borderFactory;
    private TreeNode draggedNode;
    private TreeNode dropNode;
    private boolean fetchBorder;
    private Border originalBorder;

    private Point currentMouse;
    /**
     * Creates a new instance of DragAndDropCellRenderer.
     *
     * @param trueCellRenderer
     *            the original cell renderer.
     */
    public DnDCellRendererProxy(TreeCellRenderer trueCellRenderer,JTree tree) {
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
        TreeNode nodeToRender = (TreeNode) value;
        if (c instanceof JComponent) {
            if (fetchBorder) {
                fetchBorder = false;
                originalBorder = ((JComponent) c).getBorder();
            }
            //System.out.println(currentMouse);
            JComponent jc = (JComponent) c;
            if(dropNode!=null){
                if(nodeToRender.equals(dropNode)){
                    Rectangle boun=tree.getRowBounds(row);
                    if(nodeToRender.equals(draggedNode)){
                        jc.setBorder(borderFactory.getDropNotAllowedBorder());
                    }else if(currentMouse.y<boun.height*0.75+boun.y){
                        jc.setBorder(borderFactory.getDropInAllowedBorder());
                    }else {
                        jc.setBorder(borderFactory.getDropBehindAllowedBorder());
                    }
                }else{
                    jc.setBorder(originalBorder);
                }
            }
        }
        return c;
    }

    public TreeNode getDraggedNode() {
        return this.draggedNode;
    }
    public void setDraggedNode(TreeNode draggedNode) {
        this.draggedNode = draggedNode;
    }
    public TreeNode getDropNode() {
        return this.dropNode;
    }
    public void setDropNode(TreeNode dropNode) {
        this.dropNode = dropNode;
        if (dropNode == null) {
		}
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[DnDCellRendererProxy for : ").append(
                originalTreeCellRenderer).append("]");
        return sb.toString();
    }
    private boolean dropBehindAllowed;
    public boolean isDropBehindAllowed() {
        return this.dropBehindAllowed;
    }
    public void setDropBehindAllowed(boolean dropBehindAllowed) {
        this.dropBehindAllowed = dropBehindAllowed;
        if (!dropBehindAllowed) {
		}
    }
    private boolean dropInAllowed;
    public boolean isDropInAllowed(){
        return this.dropInAllowed;
    }
    public void setShadowImage(Image anImage) {
    }
    public int getDropState() {
        return dropState;
    }
    public void setDropState(int dropState) {
        this.dropState = dropState;
    }
    /**
     * @param currentMouse the currentMouse to set
     */
    public void setCurrentMouse(Point currentMouse) {
        this.currentMouse = currentMouse;
    }
}
