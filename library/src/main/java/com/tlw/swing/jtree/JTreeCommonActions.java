package com.tlw.swing.jtree;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.tlw.serialize.Serializer;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-7
@version:2009-5-7
Description:
一些JTree可能常会用到的Action
1.展开所有
2.合拢所有节点
3.根据JTree的名称存贮到xml文件
4.根据JTree的名称从xml文件加载
 */
public class JTreeCommonActions {
	
	
	
	JTree jtree;
	public JTreeCommonActions(JTree tree){
		jtree=tree;
	}
	
	
	
	Action actionExpendall;
	public Action getActionExpendAll(){
		if(actionExpendall==null)
			actionExpendall=new ActionExpendAll();
		return actionExpendall;
	}
	class ActionExpendAll extends AbstractAction{
		private static final long serialVersionUID = -4888772857600205755L;
		public ActionExpendAll(){
			URL url=ClassLoader.getSystemResource("tlw/swing/jtree/expandall.gif");
			ImageIcon icon=new ImageIcon(url); 
			putValue(Action.NAME,"展开");
			putValue(Action.SHORT_DESCRIPTION,"展开所有节点");
			putValue(Action.SMALL_ICON,icon);
		}
		public void actionPerformed(ActionEvent e) {
			UtilJTree.expendAll(jtree);
		}
	}
	
	
	Action actionCollspaell;
	public Action getActionCollapsAll(){
		if(actionCollspaell==null)
			actionCollspaell=new ActionCollapseAll();
		return actionCollspaell;
	}
	class ActionCollapseAll extends AbstractAction{
		private static final long serialVersionUID = -3630173131631210268L;
		public ActionCollapseAll(){
			URL url=ClassLoader.getSystemResource("tlw/swing/jtree/collapseall.gif");
			ImageIcon icon=new ImageIcon(url);
			putValue(Action.NAME,"合拢");
			putValue(Action.SHORT_DESCRIPTION,"合拢所有节点");
			putValue(Action.SMALL_ICON,icon);
		}
		public void actionPerformed(ActionEvent e) {
			UtilJTree.collapsAll(jtree);
		}
	}
	
	
	Action actionResponsitory;
	public Action getActionResponsitory(){
		if(actionResponsitory==null)actionResponsitory=new ActionResponsitory();
		return actionResponsitory;
	}
	class ActionResponsitory extends AbstractAction{
		private static final long serialVersionUID = -1000682280028536669L;
		public ActionResponsitory(){
			URL url=ClassLoader.getSystemResource("tlw/swing/repository.gif");
			ImageIcon icon=new ImageIcon(url);
			putValue(Action.NAME,"保存");
			putValue(Action.SHORT_DESCRIPTION,"保存树的内容到"+getTreePersistenceXmlFileName());
			putValue(Action.SMALL_ICON,icon);
		}
		public void actionPerformed(ActionEvent e){
			try {
				Serializer.obj2Xml(jtree.getModel().getRoot(), getTreePersistenceXmlFileName());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(jtree,
						"保存类型树到文件"+getTreePersistenceXmlFileName()+"时发生异常:"+e1.getMessage(),
						"错误",
						JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
	
	
	Action actionResponsitorySynchronize;
	public Action getActionResponsitorySynchronize(){
		if(actionResponsitorySynchronize==null)actionResponsitorySynchronize=new ActionResponsitorySynchronize();
		return actionResponsitorySynchronize;
	}
	class ActionResponsitorySynchronize extends AbstractAction{
		private static final long serialVersionUID = 956516014698710536L;
		public ActionResponsitorySynchronize(){
			URL url=ClassLoader.getSystemResource("tlw/swing/repository-synchronize.gif");
			ImageIcon icon=new ImageIcon(url);
			putValue(Action.NAME,"加载");
			putValue(Action.SHORT_DESCRIPTION,"加载树的内容从"+getTreePersistenceXmlFileName());
			putValue(Action.SMALL_ICON,icon);
		}
		public void actionPerformed(ActionEvent e){
			try {
				Object obj = Serializer.xml2Obj(getTreePersistenceXmlFileName());
				if(obj!=null){
					List objs=(List)obj;
					TreeNode root=(TreeNode)objs.get(0);
					DefaultTreeModel model=(DefaultTreeModel)jtree.getModel();
					model.setRoot(root);
				}else{
					JOptionPane.showMessageDialog(jtree, "没有已经存贮的树信息。","信息",JOptionPane.PLAIN_MESSAGE);
				}
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(jtree,
						"加载类型树从文件"+getTreePersistenceXmlFileName()+"时发生异常:"+ex.getMessage(),
						"错误",
						JOptionPane.ERROR_MESSAGE);
			} 
		}
	}
	private String getTreePersistenceXmlFileName(){
		return jtree.getName()+".xml";
	}
	
	
	
	MouseAdapter mouseRightClickSelect;
	public MouseAdapter getMouseRightClickSelect(){
		if(mouseRightClickSelect==null)mouseRightClickSelect=new MouseRightClickSelect();
		return mouseRightClickSelect;
	}
	class MouseRightClickSelect extends MouseAdapter{
        //在mouseReleased上判断先于mouseClicked;
        public void mouseReleased(MouseEvent e){
            TreePath tp=jtree.getPathForLocation(e.getX(), e.getY());
            jtree.setSelectionPath(tp);
        }
	}
	
	
	MouseAdapter mousePopupMenu;
	public MouseAdapter getMousePopupMenu(JPopupMenu popupMenu){
		if(mousePopupMenu==null)mousePopupMenu=new MousePopupMenu(popupMenu);
		return mousePopupMenu;
	}
	class MousePopupMenu extends MouseAdapter{
		JPopupMenu menu;
		public MousePopupMenu(JPopupMenu popupMenu){
			menu=popupMenu;
		}
		public void mousePressed(MouseEvent e){
			if(e.isPopupTrigger())showPopupMenu(e);
		}
		public void mouseReleased(MouseEvent e){
			if(e.isPopupTrigger())showPopupMenu(e);
		}
		void showPopupMenu(MouseEvent e){
			menu.show(jtree, e.getX(), e.getY());
		}
	}
	
	
	MouseAdapter mouseDoubleClickEdit;
	public MouseAdapter getMouseDoubleClickEdit(){
		if(mouseDoubleClickEdit==null)mouseDoubleClickEdit=new MouseDoubleClickEdit();
		return mouseDoubleClickEdit;
	}
	class MouseDoubleClickEdit extends MouseAdapter{
		public void mouseClicked(MouseEvent e){
			if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount()==2){
				jtree.startEditingAtPath(jtree.getSelectionPath());
			}
		}
	}
}
