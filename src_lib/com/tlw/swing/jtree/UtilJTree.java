/*
 * Created on 2006-8-31
 */
package com.tlw.swing.jtree;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
public class UtilJTree {
    //把一个TreeNode根据TreePath持久化为二进制;
    public static void serializeTreeNode(TreePath tp,String fileName){
        if(tp!=null){
            DefaultMutableTreeNode node1=(DefaultMutableTreeNode)tp.getLastPathComponent(); // get drag node
            serializeTreeNode(node1,fileName);
        }
    }
//  把一个TreeNode持久化为二进制;
    public static void serializeTreeNode(TreeNode node ,String fileName){
        try {
            java.io.FileOutputStream objfile = new java.io.FileOutputStream(
                    fileName);
            java.io.ObjectOutputStream p = new java.io.ObjectOutputStream(
                    objfile);
            p.writeObject(node);
            p.flush();
            objfile.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    //反持久化
    public static DefaultMutableTreeNode reserializeTreeNode(String fileName){
        FileInputStream objfile = null;
        try {
            objfile = new java.io.FileInputStream(fileName);
            ObjectInputStream q = new ObjectInputStream(objfile);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)q.readObject();
            return node;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    //深度科隆,复制节点和它所有的子节点
    public static DefaultMutableTreeNode cloneDeeply(DefaultMutableTreeNode node){
        DefaultMutableTreeNode return_node=(DefaultMutableTreeNode)node.clone();
        for(int i=0;i<node.getChildCount();i++){
            DefaultMutableTreeNode child=(DefaultMutableTreeNode)node.getChildAt(i);
            if(child.getChildCount()>0){
                return_node.add(cloneDeeply((DefaultMutableTreeNode)child));
            }else{
                return_node.add((DefaultMutableTreeNode)child.clone());
            }
        }
        return return_node;
    }
    //根据路径深度科隆路径的节点;
    public static DefaultMutableTreeNode cloneDeeply(TreePath path){
        return cloneDeeply((DefaultMutableTreeNode)path.getLastPathComponent());
    }
    //是否是JTree中最下边的一行,一般用于控制ScrollBar;
    public static boolean locIsOnLowermostRow(JTree tree,Point loc)
    {
       if (loc==null)
          return false;
       int height = tree.getRowHeight();
       if (height<=0)
          height = 10; // Best guess
       int bot_row = tree.getClosestRowForLocation(
          0,
          tree.getVisibleRect().y
             +tree.getVisibleRect().height
             -height);
       if (bot_row!=-1)
       {
          Rectangle bounds = tree.getRowBounds(bot_row);
          return (loc.y >= bounds.y);
       }
       return false;
    }
    //是否是JTree中最上边的一行,一般用来控制ScrollBar;
    public static boolean locIsOnUppermostRow(JTree tree,Point loc)
    {
       if (loc==null)
          return false;
       int height = tree.getRowHeight();
       if (height<=0)
          height = 10; // Best guess
       int top_row = tree.getClosestRowForLocation(
          0,
          tree.getVisibleRect().y
             +height);
       if (top_row!=-1)
       {
          Rectangle bounds = tree.getRowBounds(top_row);
          return (loc.y <= bounds.y+bounds.height);
       }
       return false;
    }
    //节点是否在路径数组中;
    public static boolean inPathes(TreeNode node,TreePath[] pathes){
        for(int i=0;i<pathes.length;i++){
            TreeNode pNode=(TreeNode)pathes[i].getLastPathComponent();
            if(node.equals(pNode)){
                return true;
            }
        }
        return false;
    }
    /**
     * 将JTree下的所有节点展开
     * @param tree
     */
    public static void expendAll(JTree tree){
	    for(int i=0;i<tree.getRowCount();i++)
	        tree.expandRow(i);
	}
    /**
     * 将JTree下的所有节点收拢
     * @param tree
     */
	public static void collapsAll(JTree tree){
	    TreeNode root=(TreeNode)tree.getModel().getRoot();
	    int count=root.getChildCount();
	    for(int i=1;i<=count;i++)
	        tree.collapseRow(i);
	}
	//一个节点是否是另一个节点的祖先;2个节点是同一个节点也认为是祖先;
    public static boolean isAncestorOf( TreeNode aPossibleParent, TreeNode aNode )
    {
      if ( aPossibleParent == null || aNode.getParent() == null )
        return false;
      else if ( aNode.getParent() == aPossibleParent )
        return true;
      else return isAncestorOf( aPossibleParent, aNode.getParent() );
    }
}
