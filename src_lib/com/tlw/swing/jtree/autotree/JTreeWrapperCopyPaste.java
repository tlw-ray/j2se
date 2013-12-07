package com.tlw.swing.jtree.autotree;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
public class JTreeWrapperCopyPaste {
    JTree tree;
    DefaultTreeModel model;
    public JTreeWrapperCopyPaste(JTree tree){
        this.tree=tree;
        model=(DefaultTreeModel)tree.getModel();
        iMap=tree.getInputMap();
        aMap=tree.getActionMap();
        initHotKey();
    }
    InputMap iMap;
    ActionMap aMap;
    private void initHotKey(){
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_MASK), "Copy");
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_MASK), "Cut");
        iMap.put(KeyStroke.getKeyStroke((char)KeyEvent.VK_DELETE), "Del");
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_MASK), "Paste");
        iMap.put(KeyStroke.getKeyStroke((char)KeyEvent.VK_INSERT), "Insert");
        aMap.put("Copy",getActionCopy());
        aMap.put("Cut",getActionCut());
        aMap.put("Del",new ActionDel());
        aMap.put("Paste",getActionPaste());
        aMap.put("Insert", getActionInsert());
    }
    Clipboard clipBoard;
    public void copySelecting(){
        clipBoard=Toolkit.getDefaultToolkit().getSystemClipboard();
        TreePath[] path=tree.getSelectionPaths();
        if(null!=path){
            DefaultMutableTreeNode[] nodes=new DefaultMutableTreeNode[path.length];
            for(int i=0;i<path.length;i++){
                nodes[i]=(DefaultMutableTreeNode)
                (com.tlw.swing.jtree.UtilJTree.cloneDeeply(
                        (DefaultMutableTreeNode)path[i].getLastPathComponent()));
            }
            TransferableTreeNode tNode=new TransferableTreeNode(tree,nodes);
            clipBoard.setContents(tNode, null);
        }
    }
    public void pasteSelected(){
        clipBoard=Toolkit.getDefaultToolkit().getSystemClipboard();
        TreePath path=tree.getSelectionPath();
        Object obj = null;
        try {
            obj=clipBoard.getData(TransferableTreeNode.getDefaultFlavor());
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(null==obj)return;
        System.out.println(obj);
        TransferableTreeNode trans=(TransferableTreeNode)obj;
        DefaultMutableTreeNode[] clipNodes=trans.getSourceNode();
        if(clipNodes!=null && path!=null){
            for(int i=0;i<clipNodes.length;i++){
                model.insertNodeInto(com.tlw.swing.jtree.UtilJTree.cloneDeeply(clipNodes[i]),
                        (DefaultMutableTreeNode)path.getLastPathComponent(),i);

            }
            tree.expandPath(path);
        }
    }
    public void delSelecting(){
        TreePath[] path=tree.getSelectionPaths();
        if(path!=null){
        	Component root=SwingUtilities.getRootPane(tree);
        	int result=JOptionPane.showConfirmDialog(root, "确认删除?","注意",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.OK_OPTION){
	        	for(int i=0;i<path.length;i++){
	                model.removeNodeFromParent((MutableTreeNode)path[i].getLastPathComponent());
	            }
            }
        }
    }
    public void cutSelecting(){
        copySelecting();
        delSelecting();
    }
    public void insertIntoSelectingAndEdit(Object obj){
    	MutableTreeNode newChild=new DefaultMutableTreeNode(obj);
    	model.insertNodeInto(newChild, (MutableTreeNode)tree.getSelectionPath().getLastPathComponent(), 0);
    	tree.expandPath(tree.getSelectionPath());
    	tree.setSelectionRow(tree.getSelectionRows()[0]+1);
    	tree.startEditingAtPath(tree.getSelectionPath());
    }
    
    
    Action actionCopy;
    public Action getActionCopy(){
    	if(actionCopy==null)actionCopy=new ActionCopy();
    	return actionCopy;
    }
    class ActionCopy extends AbstractAction{
        private static final long serialVersionUID = 1L;
        public ActionCopy(){
        	URL url=ClassLoader.getSystemResource("tlw/swing/copy_edit.gif");
        	ImageIcon icon=new ImageIcon(url);
        	putValue(Action.NAME,"复制");
        	putValue(Action.SHORT_DESCRIPTION,"复制选中的节点。");
        	putValue(Action.SMALL_ICON,icon);
        }
        public void actionPerformed(ActionEvent e) {
            copySelecting();
        }
    }
    
    
    Action actionCut;
    public Action getActionCut(){
    	if(actionCut==null)actionCut=new ActionCut();
    	return actionCut;
    }
    class ActionCut extends AbstractAction{
        private static final long serialVersionUID = 1L;
        public ActionCut(){
        	URL url=ClassLoader.getSystemResource("tlw/swing/cut_edit.gif");
        	ImageIcon icon=new ImageIcon(url);
        	putValue(Action.NAME,"剪切");
        	putValue(Action.SHORT_DESCRIPTION,"将选中节点剪切。");
        	putValue(Action.SMALL_ICON,icon);
        }
        public void actionPerformed(ActionEvent e) {
            cutSelecting();
        }
    }
    
    
    Action actionDelete;
    public Action getActionDelete(){
    	if(actionDelete==null)actionDelete=new ActionDel();
    	return actionDelete;
    }
    class ActionDel extends AbstractAction{
        private static final long serialVersionUID = 1L;
        public ActionDel(){
        	URL url=ClassLoader.getSystemResource("tlw/swing/delete_edit.gif");
        	ImageIcon icon=new ImageIcon(url);
        	putValue(Action.NAME,"删除");
        	putValue(Action.SHORT_DESCRIPTION,"删除选中节点。");
        	putValue(Action.SMALL_ICON,icon);
        }
        public void actionPerformed(ActionEvent e) {
            delSelecting();
        }
    }
    
    
    Action actionPaste;
    public Action getActionPaste(){
    	if(actionPaste==null)actionPaste=new ActionPaste();
    	return actionPaste;
    }
    class ActionPaste extends AbstractAction{
        private static final long serialVersionUID = 1L;
        public ActionPaste(){
        	URL url=ClassLoader.getSystemResource("tlw/swing/paste_edit.gif");
        	ImageIcon icon=new ImageIcon(url);
        	putValue(Action.NAME,"粘贴");
        	putValue(Action.SHORT_DESCRIPTION,"将复制或者剪切的节点粘贴入选中节点。");
        	putValue(Action.SMALL_ICON,icon);
        }
        public void actionPerformed(ActionEvent e) {
            pasteSelected();
        }
    }
    
    Action actionInsert;
    public Action getActionInsert(){
    	if(actionInsert==null)actionInsert=new ActionInsert();
    	return actionInsert;
    }
    class ActionInsert extends AbstractAction{
		private static final long serialVersionUID = 1717612679321150166L;
		public ActionInsert(){
    		URL url=ClassLoader.getSystemResource("tlw/swing/task_new.gif");
    		ImageIcon icon=new ImageIcon(url);
    		putValue(Action.NAME,"新建");
    		putValue(Action.SHORT_DESCRIPTION,"新建一个未命名节点，插入到当前选中节点。");
    		putValue(Action.SMALL_ICON,icon);
    	}
    	public void actionPerformed(ActionEvent e){
    		insertIntoSelectingAndEdit("未命名");
    	}
    }
    
    Action actionEdit;
    public Action getActionEdit(){
    	if(actionEdit==null)actionEdit=new ActionEdit();
    	return actionEdit;
    }
    class ActionEdit extends AbstractAction{
		private static final long serialVersionUID = -8608223789080807400L;
		public ActionEdit(){
    		URL url=ClassLoader.getSystemResource("tlw/swing/my_editor.gif");
    		ImageIcon icon=new ImageIcon(url);
    		putValue(Action.NAME,"编辑");
    		putValue(Action.SHORT_DESCRIPTION,"编辑选中节点。");
    		putValue(Action.SMALL_ICON,icon);
    	}
    	public void actionPerformed(ActionEvent e){
    		if(!tree.isEditable()){
    			JOptionPane.showMessageDialog(tree, "无法编辑！","错误",JOptionPane.ERROR_MESSAGE);
    		}else{
    			tree.startEditingAtPath(tree.getSelectionPath());
    		}
    	}
    }
}
