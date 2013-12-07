package com.tlw.swing.dbtree;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.tlw.sql.DB;


public class DefaultDbTreeModel extends DefaultTreeModel implements DbTreeInfo {
	private static final long serialVersionUID = 2468647120183187560L;
	DataSource _ds;
    String _tableName;
    public DefaultDbTreeModel(DataSource ds, String tableName) {
        super(null);
        _ds = ds;
        _tableName = tableName;
        checkMeta();
        getRoot();
    }

    DefaultDbTreeModel(TreeNode root) {
        super(root);
    }

    DefaultDbTreeModel(TreeNode root, boolean asksAllowsChildren) {
        super(root, false);
    }

    public Object getRoot() {
        if (this.root == null) {
            // 取得默认根节点
            // 默认根节点有且只有一个，它的TreePath为空或者"";
            System.out.println("getDefaultRootNode");
            String rootNodeCondation =
                    "The root node whose nodePath is null or ''";
            DefaultDbTreeNode croot = new DefaultDbTreeNode(_ds, _tableName);
            croot.setParent(null);
            String sql = "select nodeId,nodeOrder,parentPath from "
                         + _tableName +
                         " where parentPath is null or parentPath=''";
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                conn = _ds.getConnection();
                stmt = conn.createStatement();
                System.out.println(sql);
                rs = stmt.executeQuery(sql);
                boolean loadRoot = false;
                while (rs.next()) {
                    if (!loadRoot) {
                        croot._nodeId = rs.getInt(FLD_ID);
                        croot._nodeOrder = rs.getFloat(FLD_ORDER);
                        croot.loadUserObject();
                        loadRoot = true;
                    } else {
                        //找到多个符合root的节点则抛出异常
                        new Exception("Found not only one root node!" +
                                      rootNodeCondation);
                    }
                }
                if (!loadRoot) {
                    // 如果沒有找到一个符合root的节点则抛出异常
                    new Exception("Found no root node!" + rootNodeCondation);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                DB.closeResultSet(rs);
                DB.closeStatement(stmt);
                DB.closeConnection(conn);
            }
            root = croot;
        }
        return root;
    }

    public void reload(TreeNode node) {
        ((DefaultDbTreeNode) node).refresh();
        super.reload(node);
    }

    /***自定义的方法***/
    void checkMeta() {
        //校验数据库结构
        //首先必须有名为_dataTable的表
        //其次它应拥有列nodeId（int，pk，autoIncrease）,nodeOrder（float）,nodePath(varchar 256)
        //如果已有此表但结构不符合则报错，提示错误；如果尚无此表则建立一个
        System.out.println("Check MetaData in " + _tableName + ".");
    }

    /***供编辑树节点调用的方法***/
    public void insertNewChildAt(DefaultDbTreeNode baseNode, int index) {
        //插入新的节点到baseNode的第index子位置处
        baseNode.dbInsert(index);
        reload(baseNode);
    }

    public void insertNewChildAtFirst(DefaultDbTreeNode baseNode) {
        //插入新的节点为baseNode的第一个子节点
        baseNode.dbInsert(0);
        reload(baseNode);
    }

    public void insertNewChildAtLast(DefaultDbTreeNode baseNode) {
        //插入新的节点到baseNode的子节点末
        baseNode.dbInsert(baseNode.getChildCount() + 1);
        reload(baseNode);
    }

    String rootJustOne = "根节点只能有一个！";
    public void insertBefore(DefaultDbTreeNode baseNode) {
        //插入新节点为baseNode兄
        if (baseNode.equals(root)) {
            JOptionPane.showMessageDialog(null, rootJustOne);
        } else {
            int idx = baseNode.getParent().getIndex(baseNode);
            ((DefaultDbTreeNode) baseNode.getParent()).dbInsert(idx);
            reload(baseNode.getParent());
        }
    }

    public void insertAfter(DefaultDbTreeNode baseNode) {
        //插入新节点为baseNode弟
        if (baseNode.equals(root)) {
            JOptionPane.showMessageDialog(null, rootJustOne);
        } else {
            int idx = baseNode.getParent().getIndex(baseNode);
            ((DefaultDbTreeNode) baseNode.getParent()).dbInsert(idx + 1);
            reload(baseNode.getParent());
        }
    }

    public void delete(DefaultDbTreeNode baseNode) {
        //删除baseNode（级联所有baseNode子节点）
        baseNode.dbDelete();
        reload(baseNode.getParent());
    }

    public void move(DefaultDbTreeNode baseNode, DefaultDbTreeNode newParent,
                     int idx) {
        //移动baseNode到新的父节点newParent（级联所有baseNode子节点）
        DefaultDbTreeNode oldParent = (DefaultDbTreeNode) baseNode.getParent();
        baseNode.dbMove(newParent, idx);
        reload(oldParent);
        reload(newParent);
    }

    public boolean moveup(DefaultDbTreeNode baseNode) {
        TreeNode parent = baseNode.getParent();
        if (parent == null) {
            JOptionPane.showMessageDialog(null, "不能移动");
            return false;
        }
        int idx = baseNode.getParent().getIndex(baseNode);
        if (idx == 0) {
            JOptionPane.showMessageDialog(null, "已经在最上了");
            return false;
        }
        move(baseNode, (DefaultDbTreeNode) baseNode.getParent(), idx - 1);
        reload(parent);
        return true;
    }

    public boolean movedown(DefaultDbTreeNode baseNode) {
        TreeNode parent = baseNode.getParent();
        if (parent == null) {
            JOptionPane.showMessageDialog(null, "不能移动！");
            return false;
        }
        int idx = baseNode.getParent().getIndex(baseNode);
        if (idx == baseNode.getParent().getChildCount() - 1) {
            JOptionPane.showMessageDialog(null, "已经在最下了");
            return false;
        }
        move(baseNode, (DefaultDbTreeNode) baseNode.getParent(), idx + 2);
        reload(parent);
        return true;
    }
}
