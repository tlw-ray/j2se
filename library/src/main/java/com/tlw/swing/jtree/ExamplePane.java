/*
 * Created on 2006-10-1
 */
package com.tlw.swing.jtree;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
public class ExamplePane extends JPanel {
    private static final long serialVersionUID = 1L;
    JSplitPane jsp = new JSplitPane();
    JTree tree1;
    JTree tree2;
    public ExamplePane(String baseDir) {
        this.setLayout(new BorderLayout());
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        for (int i = 0; i < 5; i++) {
            DefaultMutableTreeNode iNode = new DefaultMutableTreeNode(
                    new IconItem(baseDir + i + ".gif"));
            for (int j = 0; j < 5; j++) {
                DefaultMutableTreeNode jNode = new DefaultMutableTreeNode(
                        new IconItem(baseDir + j
                                + ".gif"));
                for (int k = 0; k < 5; k++) {
                    DefaultMutableTreeNode kNode = new DefaultMutableTreeNode(
                            new IconItem(baseDir + k
                                    + ".gif"));
                    jNode.add(kNode);
                }
                iNode.add(jNode);
            }
            root.add(iNode);
        }
        tree1 = new JTree(root);
        tree2 = new JTree(UtilJTree.cloneDeeply(root));
        tree1.setCellRenderer(new IconTreeCellRenderer());
        tree2.setCellRenderer(new IconTreeCellRenderer());
        new com.tlw.swing.jtree.autotree.JTreeWrapperDragDtop(tree1);
        new com.tlw.swing.jtree.autotree.JTreeWrapperDragDtop(tree2);
        jsp.add(new JScrollPane(tree1), JSplitPane.LEFT);
        jsp.add(new JScrollPane(tree2), JSplitPane.RIGHT);
        jsp.setDividerLocation(200);
        this.add(jsp, BorderLayout.CENTER);
        this.setVisible(true);
    }

    class IconItem implements IiconAble {
        transient ImageIcon ico;

        String icoUri;

        public IconItem(String icoStr) {
            this.setIcon(icoStr);
        }

        public ImageIcon getIcon() {
            return ico;
        }

        public ImageIcon getIconBySize(int width, int height) {
            Image img = ico.getImage();
            img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }

        public void setIcon(String IconUri) {
            java.net.URL u=Example.class.getResource(IconUri);
            ico=new ImageIcon(u);
            icoUri = IconUri;
        }

        public String toString() {
            return icoUri.substring(icoUri.length() - 5);
        }
    }
}
