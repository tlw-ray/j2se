/*
 * Created on 2006-9-18
 */
package com.tlw.swing.jtree.autotree;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
/**
 * <p>Title_Cn: </p>
 * <p>Description: </p>
 * <p>Date: 2006-9-18</p>
 * <p>Company: qlxtgs </p>
 * <p>Copyright: Copyright (c)2006</p>
 * @author tlw_ray
 * @version 1.0
 * @since JDK 1.5bate
 */
public class DnDUtils {
    public static boolean dropBehindOrDropIn(JTree tree,TreePath mouseOverPath,Point mousePoint){
        return dropBehindOrDropIn(tree, tree.getRowForPath(mouseOverPath),mousePoint);
    }
    protected static boolean dropBehindOrDropIn(JTree tree,int mouseOverRow,Point mousePoint){
        //在拖放节点时,根据树,当前行,鼠标点信息,取得是加入进还是追加;如果鼠标处于节点80%高度处则认为加入,否则为追加
        //加入就是将节点作为子节点插入,追加就是将节点作为兄弟节点插入节点的后方;
        Rectangle boun=tree.getRowBounds(mouseOverRow);
        return (mousePoint.y<boun.height*0.75+boun.y);
    }
    public static boolean dropAllow(DefaultMutableTreeNode[] nodeDrags,DefaultMutableTreeNode nodeTarget){
        //同一颗树下,不能把父节点拖入子节点;如果被拖动的节点数组中存在目标节点的祖先,那么放弃拖动返回false;否则可以拖动返回true;
        if(nodeTarget==null || nodeDrags==null)return true;//异树拖动的情况;
        for(int i=0;i<nodeDrags.length;i++){
            DefaultMutableTreeNode cNode=nodeDrags[i];
            if(nodeTarget.isNodeAncestor(cNode))return false;//同树拖动时,被拖动的node中包括了拖动目标的祖先,则返回false认为不能拖动;
        }
        return true;
    }
}
