package com.tlw.swing.jtree.autotree;
import java.awt.Point;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.io.IOException;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.tlw.swing.jtree.UtilJTree;

public class JTreeWrapperDragDtop {
    public JTreeWrapperDragDtop(JTree tree){
        this.tree=tree;
        //tree.setRootVisible(false);
        //tree.setShowsRootHandles(true);
        myDragGesture=new MyDragGesture(tree);
        myDropTarget=new MyDropTarget(tree);
        dragSource= DragSource.getDefaultDragSource();
        dragSource.createDefaultDragGestureRecognizer(tree,
                DnDConstants.ACTION_COPY, myDragGesture);
        dropTarget = new AutoScrollingTreeDropTarget( tree,myDropTarget);
        rendererProxy=new DnDCellRendererProxy(tree.getCellRenderer());
        tree.setCellRenderer(rendererProxy);
        //new CopyPasteWrapper(tree);
    }
    boolean dndAble=true;
    public void setDndAble(boolean b){
        dndAble=b;
    }
    public boolean getDndAble(){
        return dndAble;
    }
    JTree tree;
    DropTarget dropTarget;
    DragSource dragSource;
    MyDragGesture myDragGesture;
    MyDropTarget myDropTarget;
    DnDCellRendererProxy rendererProxy;     //拖动时渲染放入和追加;
    class MyDragGesture implements DragGestureListener {
        JTree tree;
        //构造时候设定对拖动节点是移动还是拷贝;
        public MyDragGesture(JTree sourceTree) {
            tree = sourceTree;
        }
        public void dragGestureRecognized(DragGestureEvent dge) {
            if (!tree.isEnabled() || !dndAble)return;
            TreePath[] pathes = tree.getSelectionPaths();
            if(pathes==null) return;
            DefaultMutableTreeNode[] nodes = new DefaultMutableTreeNode[pathes.length];
            for (int i = 0; i < pathes.length; i++) {
                nodes[i] = (DefaultMutableTreeNode) pathes[i]
                        .getLastPathComponent();
            }
            TransferableTreeNode tNodes=new TransferableTreeNode(tree,nodes);
            //拖动开始的时候告诉本树的拖动渲染代理,当前树拖动的对象;在拖动结束后应该清空;
            rendererProxy.setDraggedNodes(nodes);
            dge.startDrag(DragSource.DefaultMoveDrop, tNodes, new MyMoveDragSource(tree,nodes));
        }
    }
    class MyMoveDragSource extends DragSourceAdapter {
        JTree tree;
        DefaultMutableTreeNode[] dragNodes;
        public MyMoveDragSource(JTree sourceTree,DefaultMutableTreeNode[] dragNodes) {
            tree = sourceTree;
            this.dragNodes=dragNodes;
        }
        public void dragDropEnd(DragSourceDropEvent dsde) {
            // 这个是拖放操作的最后一步
            if (dsde.getDropAction() == DnDConstants.ACTION_MOVE
                    && dsde.getDropSuccess()) {
                DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                for (int i = 0; i < dragNodes.length; i++) {
                    if (dragNodes[i].getParent() != null)
                    {
                        model.removeNodeFromParent(dragNodes[i]);
                    }
                }
            }
            //拖动操作彻底结束;
            rendererProxy.setDraggedNodes(null);
            rendererProxy.setDropNode(null);
            tree.repaint();
        }
    }
    class MyDropTarget extends DropTargetAdapter {
        JTree tree;
        public MyDropTarget(JTree tree) {
            this.tree = tree;
        }
        public void dragOver(DropTargetDragEvent dtde) {
            super.dragOver(dtde);
            TreePath dropPath = tree.getClosestPathForLocation( dtde.getLocation().x,
                    dtde.getLocation().y );
            if(null==dropPath)return;
            DefaultMutableTreeNode dropNode=(DefaultMutableTreeNode)dropPath.getLastPathComponent();
            rendererProxy.setDropNode(dropNode);
            try {
                Thread.sleep(50);//提供CPU空闲;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rendererProxy.setCurrentMouse(dtde.getLocation());
            tree.repaint();
        }
        public void dragExit(DropTargetEvent dte) {
            //当拖动尚未结束,但拖动目标离开此树;
            super.dragExit(dte);
            //此树的DND渲染为空
            rendererProxy.setDropNode(null);
            tree.repaint();
        }
        public void drop(DropTargetDropEvent dtde) {
            if (!tree.isEnabled() || !dndAble)return;
            //以下取得拖动需要的一些必要信息;
            Point p = dtde.getLocation();
            TreePath path = tree.getClosestPathForLocation(p.x, p.y);
            DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
            DefaultMutableTreeNode[] realDraggingNodes=null; //拖放操作被DRAG的节点
            DefaultMutableTreeNode targetNode=null;          //拖放操作的DROP目标节点
            JTree sourceTree;
            try {
                //使用TransferableTreeNode传输数据;
                TransferableTreeNode transfer=(TransferableTreeNode)(dtde.getTransferable().getTransferData(dtde.getTransferable().getTransferDataFlavors()[0]));
                sourceTree=transfer.getSourceTree();
                realDraggingNodes=(DefaultMutableTreeNode[])transfer.getSourceNode();
                //判断是copy拖动还是move拖动;
                if(sourceTree.equals(tree)){
                    //如果在自身树内拖动,则告知为MOVE状态;
                    dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                    //如果试图将节点拖到它自身身上,或者他的子节点身上那么认为不可以;放弃拖动;
                    if(!DnDUtils.dropAllow(realDraggingNodes, targetNode))return;
                }else{
                    //如果在树与树之间拖动,则告知为COPY状态;
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                }
                //执行拖放;
                DefaultMutableTreeNode[] newNodes;
                if(path!=null){
                    //拖放到了某个节点上
                    targetNode = (DefaultMutableTreeNode)path.getLastPathComponent();
                    if(DnDUtils.dropAllow(realDraggingNodes, targetNode)){
                        if(DnDUtils.dropBehindOrDropIn(tree,path,p)){
                            newNodes=dragInsert(model,realDraggingNodes,targetNode);
                            if(!tree.isExpanded(path)){tree.expandPath(path);}//拖动后自动展开
                        }else{
                            newNodes=dragBehind(model,realDraggingNodes,targetNode);
                        }
                    }else return;
                }else{
                    //拖放到了空节点上;则认为拖放到树的根节点上;
                    targetNode=(DefaultMutableTreeNode)tree.getModel().getRoot();
                    if(targetNode==null){
                        //树无根节点
                        targetNode=new DefaultMutableTreeNode("root");
                        ((DefaultTreeModel)tree.getModel()).setRoot(targetNode);
                    }
                    newNodes=dragInsert(model,realDraggingNodes,targetNode);
                }
                //拖动后自动选择;
                autoSelect(model,newNodes);
            } catch (UnsupportedFlavorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            /****善后处理****/
            //通知Dragger拖放sucess正常结束;
            dtde.dropComplete(true);
            //告知拖放目标树拖放操作完毕,否则异树拖放有残留绘制;
            rendererProxy.setDropNode(null);
            tree.repaint();
        }
        //把节点数组作为子节点加入;从第0节点加入到根节点
        private DefaultMutableTreeNode[] dragInsert(DefaultTreeModel model,DefaultMutableTreeNode[] childNodes,DefaultMutableTreeNode parent){
            DefaultMutableTreeNode[] newNodes=new DefaultMutableTreeNode[childNodes.length];//返回拖动后新生成的节点;
            for (int i = 0; i < childNodes.length; i++) {
                model.insertNodeInto(UtilJTree.cloneDeeply(childNodes[i]), parent, i);          //作为子节点,从0开始加入;
                newNodes[i]=(DefaultMutableTreeNode)parent.getChildAt(i);                   //加入到用于反馈的树新加节点集合中去;
            }
            return newNodes;
        }
        //把节点数组作为兄弟节点追加;
        private DefaultMutableTreeNode[] dragBehind(DefaultTreeModel model,DefaultMutableTreeNode[] cousinlyNodes,DefaultMutableTreeNode elder){
            DefaultMutableTreeNode parent=(DefaultMutableTreeNode)elder.getParent();//取得当前节点的父节点;
            int idx=model.getIndexOfChild(parent, elder);    //要被拖入节点所在父节点的位置
            DefaultMutableTreeNode[] newNodes=new DefaultMutableTreeNode[cousinlyNodes.length];
            for (int i = 0; i < cousinlyNodes.length; i++) {
                int at=idx+i+1;                                 //要加入的节点在父节点中应处的位置.也就是目标节点之后;
                model.insertNodeInto(UtilJTree.cloneDeeply(cousinlyNodes[i]), parent, at);
                newNodes[i]=(DefaultMutableTreeNode)parent.getChildAt(at);//加入到用于反馈的树新加节点集合中去;
            }
            return newNodes;
        }
        //根据树中新加入的节点在操作后自动展开;
        private void autoSelect(DefaultTreeModel model,DefaultMutableTreeNode[] nodes){
            TreePath[] pathes=new TreePath[nodes.length];
            for(int i=0;i<pathes.length;i++){
                pathes[i]=new TreePath(model.getPathToRoot(nodes[i]));
            }
            tree.setSelectionPaths(pathes);
        }
    }
}
