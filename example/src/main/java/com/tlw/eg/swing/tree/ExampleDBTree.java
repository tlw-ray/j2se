package com.tlw.eg.swing.tree;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * @author tlw_ray
 * @since 2013-8-23
 */
public class ExampleDBTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String FIELD_ID="ID";
		final String FIELD_PARENT_ID="PARENT_ID";
		
		final JFrame frame=new JFrame();
		final JTree jtree=new JTree();
		
		//1.构建表格数据。
		final List<Map<String, Integer>> table=new Vector<Map<String, Integer>>();
		
		//子节点0
		Map<String, Integer> row0=new HashMap<String,Integer>();
		row0.put(FIELD_ID, 0);
		row0.put(FIELD_PARENT_ID, null);
		table.add(row0);
		
		//子节点1
		Map<String, Integer> row1=new HashMap<String,Integer>();
		row1.put(FIELD_ID, 1);
		row1.put(FIELD_PARENT_ID, 0);
		table.add(row1);
		
		//子节点2
		Map<String, Integer> row2=new HashMap<String,Integer>();
		row2.put(FIELD_ID, 2);
		row2.put(FIELD_PARENT_ID, 0);
		table.add(row2);
		
		//子节点3
		Map<String, Integer> row3=new HashMap<String,Integer>();
		row3.put(FIELD_ID, 3);
		row3.put(FIELD_PARENT_ID, 1);
		table.add(row3);
		
		//2.表数据转化为树
		DefaultMutableTreeNode treeNodeRoot=null;
		Map<Integer, DefaultMutableTreeNode> mapId2Node=new HashMap<Integer, DefaultMutableTreeNode>();
		for(Map<String, Integer> row:table){
			
			Integer nodeID=row.get(FIELD_ID);
			Integer parentID=row.get(FIELD_PARENT_ID);
			
			DefaultMutableTreeNode nodeExisted=mapId2Node.get(nodeID);
			if(nodeExisted==null){
				nodeExisted=new DefaultMutableTreeNode();
				nodeExisted.setUserObject(nodeID);
				mapId2Node.put(nodeID, nodeExisted);
			}
			
			//如果已经有父节点则加入，如果没有则创建
			if(parentID==null){
				//遇到根节点,纪录下来，供后边作为树的根
				treeNodeRoot=nodeExisted;
			}else{
				//获得父节点实例
				DefaultMutableTreeNode parentExisted=mapId2Node.get(parentID);
				if(parentExisted==null){
					parentExisted=new DefaultMutableTreeNode();
					parentExisted.setUserObject(parentID);
					mapId2Node.put(parentID, parentExisted);
				}
				
				//遇到非根节点,建立与它父节点的关联
				parentExisted.add(nodeExisted);
			}
		}
		
		//3.为节点增加右键菜单处理(增,删)
		final Action actionInsert=new AbstractAction(){
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "增");
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj=jtree.getSelectionPath().getLastPathComponent();
				DefaultMutableTreeNode treeNode=(DefaultMutableTreeNode)obj;
				//找到最大的ID,(因为没有关系数据库，会自动产生自增的ID，只能自己增加）
				int maxID=0;
				for(Map<String, Integer> row:table){
					Integer id=row.get(FIELD_ID);
					maxID=Math.max(maxID, id);
				}
				
				int currentID=maxID+1;
				int parentID=(Integer)((DefaultMutableTreeNode)treeNode.getParent()).getUserObject();

				//增加节点到表(数据库)
				Map<String, Integer> row=new HashMap<String, Integer>();
				row.put(FIELD_ID, currentID);
				row.put(FIELD_PARENT_ID, parentID);
				table.add(row);
				//增加节点到树
				DefaultMutableTreeNode treeNodeAdded=new DefaultMutableTreeNode();
				treeNodeAdded.setUserObject(currentID);
				treeNode.add(treeNodeAdded);
				jtree.updateUI();
			}
		};
		
		final Action actionDelete=new AbstractAction(){
			private static final long serialVersionUID = 1L;
			{
				putValue(Action.NAME, "删");
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				Object obj=jtree.getSelectionPath().getLastPathComponent();
				DefaultMutableTreeNode treeNode=(DefaultMutableTreeNode)obj;
				DefaultMutableTreeNode treeNodeParent=(DefaultMutableTreeNode)treeNode.getParent();
				treeNodeParent.remove(treeNode);
				jtree.updateUI();
			}
		};
		
		final JPopupMenu jpopupMenu=new JPopupMenu();
		jpopupMenu.add(actionInsert);
		jpopupMenu.add(actionDelete);
		
		jtree.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				showPopup(e);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				//鼠标右键选中
				TreePath treePath=jtree.getPathForLocation(e.getX(), e.getY());
				jtree.setSelectionPath(treePath);
				showPopup(e);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				showPopup(e);
			}
			private void showPopup(MouseEvent e){
				if(e.isPopupTrigger()){
					jpopupMenu.show(jtree, e.getX(), e.getY());
				}
			}
		});
		
		//4.增加一个按钮来输出当前表数据
		JButton jbutton=new JButton("显示表数据");
		jbutton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				for(Map<String, Integer> row:table){
					System.out.println(row);
				}
				System.out.println("---------");
			}
		});
		
		//5.显示
		DefaultTreeModel model=new DefaultTreeModel(treeNodeRoot);
		jtree.setModel(model);
		frame.add(jbutton, BorderLayout.NORTH);
		frame.add(jtree,BorderLayout.CENTER);
		frame.setSize(300,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
