package com.tlw.swing.dbtree;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.sql.DataSource;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.apache.derby.jdbc.EmbeddedDataSource;

public class DbTree extends JTree{
	private static final long serialVersionUID = -3671536145860172758L;
	public static void main(String[] args) {
        //*
        //Example;
        com.tlw.swing.UIExtends.setMetalNoBold();
        //init DataSource
        EmbeddedDataSource ds=new EmbeddedDataSource();
        ds.setDatabaseName("D:\\data\\Derby\\dbTreeExample");
        //init dbTree;
        DbTree tree=new DbTree(ds,"dbtree");
        //init ui
        JFrame frame=new JFrame();
        JPanel paneNorth = new JPanel();
        JScrollPane jsp = new JScrollPane(tree);
        JToolBar toolBar1 = tree.getToolBar();

        frame.setPreferredSize(new Dimension(250, 300));
        frame.setLayout(new BorderLayout());
        frame.add(paneNorth, BorderLayout.NORTH);
        frame.add(jsp, BorderLayout.CENTER);

        paneNorth.setLayout(new GridLayout());
        paneNorth.add(toolBar1);

        frame.setSize(400,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //*/
    }
    DataSource _ds;
    String _tableName;
    /**根据数据源和表名构建dbTree;
     * @param DataSource 数据源
     * @param String 表名
     */
    public DbTree(DataSource ds,String tableName){
        _ds=ds;
        _tableName=tableName;
        setModel(new DefaultDbTreeModel(ds,tableName));
        DefaultTreeSelectionModel sModel = new DefaultTreeSelectionModel();
       sModel.setSelectionMode(DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
       setSelectionModel(sModel);
       addMouseListener(new TreeMouseListener());
    }
    public TreeModel getModel(){
        return (DefaultDbTreeModel)super.getModel();
    }
    /***Actions***/
    public Action insertChildFirst = new InsertChildFirst();
    public Action insertChildLast = new InsertChildLast();
    public Action insertBefore = new InsertBefore();
    public Action insertAfter = new InsertAfter();
    public Action moveUp = new MoveUp();
    public Action moveDown = new MoveDown();
    public Action delete = new Delete();
    public Action refresh = new Refresh();
    public Action copy = new Copy();
    public Action paste = new Paste();
    public Action cut = new Cut();
    public Action property=new Property();

    /***Actions Implements***/
    class InsertChildFirst extends AbstractAction {
		private static final long serialVersionUID = 5980650832189122724L;

		public InsertChildFirst() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/InsertChildFirst.png")));
            putValue(Action.NAME, "插入子节点于首部");
            putValue(Action.SHORT_DESCRIPTION, "为选中节点插入子节点,为第一子节点。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                TreePath path = getSelectionPath();
                int row = getSelectionRows()[0];
                ((DefaultDbTreeModel) getModel()).insertNewChildAtFirst(getCurrentNode());
                setSelectionRow(row);
                expandPath(path);
            }
        }
    }
    class InsertChildLast extends AbstractAction {
		private static final long serialVersionUID = 8086482935025852643L;

		public InsertChildLast() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/InsertChildLast.png")));
            putValue(Action.NAME, "插入子节点于尾部");
            putValue(Action.SHORT_DESCRIPTION, "为选定节点插入子节点，为末尾子节点。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                TreePath path= getSelectionPath();
                int row = getSelectionRows()[0];
                ((DefaultDbTreeModel) getModel()).insertNewChildAtLast(getCurrentNode());
                setSelectionRow(row);
                expandPath(path);
            }
        }
    }


    class InsertBefore extends AbstractAction {
		private static final long serialVersionUID = 2542432400318067150L;

		public InsertBefore() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/InsertBefore.png")));
            putValue(Action.NAME, "插入兄节点");
            putValue(Action.SHORT_DESCRIPTION, "在选中节点之前插入兄弟节点。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                int row = getSelectionRows()[0];
                ((DefaultDbTreeModel) getModel()).insertBefore(getCurrentNode());
                setSelectionRow(row + 1);
            }
        }
    }


    class InsertAfter extends AbstractAction {
		private static final long serialVersionUID = 7082087527247711113L;

		public InsertAfter() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/InsertAfter.png")));
            putValue(Action.NAME, "插入弟节点");
            putValue(Action.SHORT_DESCRIPTION, "在选中节点之后插入兄弟节点。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                int row = getSelectionRows()[0];
                ((DefaultDbTreeModel) getModel()).insertAfter(getCurrentNode());
                setSelectionRow(row);
            }
        }
    }


    class Delete extends AbstractAction {
		private static final long serialVersionUID = 2108947227806981640L;

		public Delete() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/remove.png")));
            putValue(Action.NAME, "删除");
            putValue(Action.SHORT_DESCRIPTION, "删除选定节点，及其所有子节点。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                if(JOptionPane.showConfirmDialog(null,
                        "决定要删除么？删除操作后所有子区域以及区域相关设备都将被级联删除。",
                                              JOptionPane.MESSAGE_PROPERTY,
                                              JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    int row = getSelectionRows()[0];
                    ((DefaultDbTreeModel) getModel()).delete(getCurrentNode());
                    setSelectionRow(row);
                }
            }
        }
    }
    class Refresh extends AbstractAction {
		private static final long serialVersionUID = 7109981576016758236L;

		public Refresh() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/synch_toc_nav.png")));
            putValue(Action.NAME, "刷新");
            putValue(Action.SHORT_DESCRIPTION, "重新从数据库加载选定节点。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                int row = getSelectionRows()[0];
                ((DefaultDbTreeModel) getModel()).reload(getCurrentNode());
                setSelectionRow(row);
            }
        }
    }
    class MoveUp extends AbstractAction {
		private static final long serialVersionUID = -4542362640839055863L;

		public MoveUp() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/MoveUp.png")));
            putValue(Action.NAME, "上移");
            putValue(Action.SHORT_DESCRIPTION, "将选定节点上移。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                int row = getSelectionRows()[0];
                if (((DefaultDbTreeModel) getModel()).moveup(getCurrentNode()))
                    setSelectionRow(row - 1);
            }
        }
    }


    class MoveDown extends AbstractAction {
		private static final long serialVersionUID = 2383254574738748920L;

		public MoveDown() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/moveDown.png")));
            putValue(Action.NAME, "下移");
            putValue(Action.SHORT_DESCRIPTION, "将选定节点下移。");
        }

        public void actionPerformed(ActionEvent e) {
            if (checkNodeNotNull()) {
                int row = getSelectionRows()[0];
                if (((DefaultDbTreeModel) getModel()).movedown(getCurrentNode()))
                    setSelectionRow(row + 1);
            }
        }
    }


    class Copy extends AbstractAction {
		private static final long serialVersionUID = -4374279297413485844L;

		public Copy() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/copy.png")));
            putValue(Action.NAME, "复制");
            putValue(Action.SHORT_DESCRIPTION, "将选定节点复制，准备粘贴。");
        }

        public void actionPerformed(ActionEvent e) {

        }
    }


    class Cut extends AbstractAction {
		private static final long serialVersionUID = 7916023975523641118L;

		public Cut() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/cut.png")));
            putValue(Action.NAME, "剪切");
            putValue(Action.SHORT_DESCRIPTION, "将选定节点剪切，准备粘贴。");
        }

        public void actionPerformed(ActionEvent e) {

        }
    }
    class Paste extends AbstractAction {
		private static final long serialVersionUID = 3504591073370693509L;

		public Paste() {
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/paste.png")));
            putValue(Action.NAME, "粘贴");
            putValue(Action.SHORT_DESCRIPTION, "将被复制或剪切的节点加入选中节点作为第一个子节点。");
        }

        public void actionPerformed(ActionEvent e) {

        }
    }
    class Property extends AbstractAction {
		private static final long serialVersionUID = 67726787926374169L;

		public Property() {
            putValue(Action.SHORT_DESCRIPTION, "区域属性");
            putValue(Action.NAME, "属性");
            putValue(Action.SMALL_ICON,
                     new ImageIcon(getClass().getClassLoader().
                                   getResource("tlw/swing/dbtree/res/property.png")));
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("Property Dlg");
        }
    }
    public DefaultDbTreeNode getCurrentNode() {
        return (DefaultDbTreeNode) getSelectionPath().getLastPathComponent();
    }

    boolean checkNodeNotNull() {
        if (getSelectionPath() == null) {
            JOptionPane.showMessageDialog(this, "没有选中节点！");
            return false;
        } else {
            return true;
        }
    }
    /***Mouse Listener***/
    class TreeMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            //System.out.println("MouseClicked!");
        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            //System.out.println("MousePressed");
            //右键选中;
            if (e.getButton() == MouseEvent.BUTTON3) {
                TreePath path = getPathForLocation(e.getX(), e.getY());
                setSelectionPath(path);
            }
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            //System.out.println("MouseRelease");
            maybeShowPopup(e);
        }
        //右键弹出菜单;
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                getPopupMenu().show(e.getComponent(),
                                    e.getX(), e.getY());
            }
        }
    }
    /***Get Default UI***/
    public JPopupMenu _jpopupMenu;
    public JPopupMenu getPopupMenu() {
        if (_jpopupMenu == null) {
            _jpopupMenu = new JPopupMenu();
            _jpopupMenu.add(insertAfter);
            _jpopupMenu.add(insertBefore);
            _jpopupMenu.add(insertChildFirst);
            _jpopupMenu.add(insertChildLast);
            _jpopupMenu.add(moveUp);
            _jpopupMenu.add(moveDown);
            _jpopupMenu.addSeparator();
            //_jpopupMenu.add(copy);
            //_jpopupMenu.add(cut);
            //_jpopupMenu.add(paste);
            _jpopupMenu.add(delete);
            _jpopupMenu.addSeparator();
            _jpopupMenu.add(property);
        }
        return _jpopupMenu;
    }
    JToolBar _toolBar;
    public JToolBar getToolBar(){
        if(_toolBar==null){
            _toolBar=new JToolBar();
            _toolBar.add(insertChildFirst);
            _toolBar.add(insertChildLast);
            _toolBar.add(insertBefore);
            _toolBar.add(insertAfter);
            _toolBar.addSeparator();
            _toolBar.add(refresh);
            _toolBar.add(moveUp);
            _toolBar.add(moveDown);
            _toolBar.add(delete);
        }
        return _toolBar;
    }
}
