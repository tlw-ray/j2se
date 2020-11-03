package com.tlw.swing.jtree.autotree;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
public class TransferableTreeNode implements Transferable {
    public final static int OBJ = 0;
    public final static int STRING = 1;
    private static DataFlavor[] supportedDataFlavors;
    public TransferableTreeNode(JTree aTree, DefaultMutableTreeNode[] aNode) {
        supportedDataFlavors = new DataFlavor[] {
                TransferableTreeNode.getDefaultFlavor(),
                DataFlavor.stringFlavor };
        setSourceTree(aTree);
        setSourceNode(aNode);
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        for (int i = 0; i < supportedDataFlavors.length; i++) {
            if (flavor.equals(supportedDataFlavors[i]))
                return true;
        }
        return false;
    }
    public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (flavor.equals(supportedDataFlavors[OBJ])) {
            return this;
        } else if (flavor.equals(DataFlavor.stringFlavor)) {
            return getSourceNode().toString();
        } else
            throw new UnsupportedFlavorException(flavor);
    }

    public DataFlavor[] getTransferDataFlavors() {
        return supportedDataFlavors;
    }
    public static DataFlavor getDefaultFlavor(){
        return new DataFlavor(TransferableTreeNode.class, "TreeNodeFlavor");
    }
    /**
     * Holds value of property sourceTree.
     */
    private JTree sourceTree;
    /**
     * Getter for property sourceTree.
     *
     * @return Value of property sourceTree.
     */
    public JTree getSourceTree() {
        return this.sourceTree;
    }
    /**
     * Setter for property sourceTree.
     *
     * @param sourceTree
     *            New value of property sourceTree.
     */
    public void setSourceTree(JTree sourceTree) {
        this.sourceTree = sourceTree;
    }
    /**
     * Holds value of property sourceNode.
     */
    private DefaultMutableTreeNode[] sourceNode;

    /**
     * Getter for property sourceNode.
     *
     * @return Value of property sourceNode.
     */
    public DefaultMutableTreeNode[] getSourceNode() {
        return this.sourceNode;
    }
    /**
     * Setter for property sourceNode.
     *
     * @param sourceNode
     *            New value of property sourceNode.
     */
    public void setSourceNode(DefaultMutableTreeNode[] sourceNode) {
        this.sourceNode = sourceNode;
    }
    /**
     * Holds value of property nodeWasExpanded.
     */
    private boolean nodeWasExpanded;
    /**
     * Getter for property nodeWasExpanded.
     *
     * @return Value of property nodeWasExpanded.
     */
    public boolean isNodeWasExpanded() {
        return this.nodeWasExpanded;
    }
    /**
     * Setter for property nodeWasExpanded.
     *
     * @param nodeWasExpanded
     *            New value of property nodeWasExpanded.
     */
    public void setNodeWasExpanded(boolean nodeWasExpanded) {
        this.nodeWasExpanded = nodeWasExpanded;
    }
}
