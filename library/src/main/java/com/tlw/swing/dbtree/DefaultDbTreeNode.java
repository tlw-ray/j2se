package com.tlw.swing.dbtree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.swing.tree.DefaultMutableTreeNode;

import com.tlw.sql.DB;


public class DefaultDbTreeNode extends DefaultMutableTreeNode implements
        DbTreeInfo {
    private static final long serialVersionUID = 1L;
    /***节点属性***/
    public int _nodeId = -1; //节点ID
    public float _nodeOrder = 1024; //节点在同父兄弟节点中的排序依据
    public String _parentPath = ""; //祖先节点路径
    /***数据库信息***/
    public DataSource _ds; //数据源
    public String _tableName; //树存放的表名

    public DefaultDbTreeNode(DataSource ds, String tableName) {
        _ds = ds;
        _tableName = tableName;
    }

    /***自定义的关于树方法***/
    public void load() { //加载自身
        System.out.println("Load:" + _nodeId);
        String sql = "select nodeOrder,parentPath from " + _tableName +
                     " where nodeId="
                     + _nodeId;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = _ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            rs.next();
            _nodeOrder = rs.getFloat(FLD_ORDER);
            _parentPath = rs.getString(FLD_PATH);
            loadUserObject();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stmt);
            DB.closeConnection(conn);
        }
        loadChildren();
    }

    boolean _childrenLoaded = false;
    public void loadChildren() {
        //加载自身子节点
        System.out.println("Load Children!:" + _nodeId);
        _childrenLoaded = true;
        String nodePath = getPathStr();
        String sql = "select nodeId,nodeOrder from " + _tableName +
                     " where parentPath='"
                     + nodePath + "' order by nodeOrder";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        removeAllChildren();
        try {
            conn = _ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                DefaultDbTreeNode cNode = new DefaultDbTreeNode(_ds, _tableName);
                cNode._nodeId = rs.getInt(FLD_ID);
                cNode._nodeOrder = rs.getFloat(FLD_ORDER);
                cNode._parentPath = nodePath;
                cNode.loadUserObject();
                //cNode.loadChildren();//加上这句就是一次性加载所有子节点
                add(cNode);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(stmt);
            DB.closeConnection(conn);
        }
    }

    public void loadUserObject() {
        //加载用户对象根据_userObjectId;
    }

    public boolean isLeaf() {
        //此函数非常经常被调用；
        if (!_childrenLoaded) {
            loadChildren();
        }
        //System.out.println("isLeaf()"+_nodeId+" "+super.isLeaf());
        return super.isLeaf();
    }

    /***继承自Object的方法***/
    public String toString() {
        return "{DefaultDbTreeNode[id:" + _nodeId + "],[order:" + _nodeOrder
                + "],[path:" + _parentPath + "]}";
    }

    /***对节点增删改数据库操作,供外部调用***/
    public void refresh() {
        //从数据库刷新自身，以及自身直属子节点的所有信息；
        //System.out.println("refresh");
        load();
        loadChildren();
    }

    private float judgeNodeOrder(int index) {
        float nodeOrder = 1024;
        if (!isLeaf()) {
            // 如果此节点已有子节点
            System.out.println("index:" + index);
            if (index == 0) { // 加入首部子节点
                DefaultDbTreeNode first = (DefaultDbTreeNode) getChildAt(0);
                nodeOrder = first._nodeOrder / 2;
            } else if (index < getChildCount()) { // 加入子节点之中;
                DefaultDbTreeNode first = (DefaultDbTreeNode) getChildAt(index -
                        1);
                DefaultDbTreeNode second = (DefaultDbTreeNode) getChildAt(index);
                nodeOrder = (second._nodeOrder + first._nodeOrder) / 2;
            } else { // 加入最后一个子节点；
                DefaultDbTreeNode last = (DefaultDbTreeNode) getLastChild();
                nodeOrder = last._nodeOrder * 2;
            }
        }
        return nodeOrder;
    }

    public void dbInsert(int index) {
        //把一个新的节点(没有userObject)插入当前节点的子节点的index处
        String parentPath = getPathStr();
        float nodeOrder = judgeNodeOrder(index);
        Connection conn = null;
        PreparedStatement pstmt = null;
        String insert = "insert into " + _tableName +
                        " (nodeOrder,parentPath) values (?,?)";
        try {
            conn = _ds.getConnection();
            pstmt = conn.prepareStatement(insert);
            pstmt.setFloat(1, nodeOrder);
            pstmt.setString(2, parentPath);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(pstmt);
            DB.closeConnection(conn);
        }
        _childrenLoaded = false;
    }

    public void dbMove(DefaultDbTreeNode newParent, int index) {
        //变更当前节点的父节点;使当前节点成为新父节点的第index个子节点;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DefaultDbTreeNode oldParent = (DefaultDbTreeNode) getParent();
        try {
            conn = _ds.getConnection();
            conn.setAutoCommit(false);
            if (newParent.equals(oldParent)) {
                //在父节点内移动
                String update = "update " + _tableName +
                                " set nodeOrder=? where nodeId=?";
                float order = newParent.judgeNodeOrder(index);
                if (index != newParent.getIndex(this)) {
                    //目标并非自身
                    pstmt = conn.prepareStatement(update);
                    pstmt.setFloat(1, order);
                    pstmt.setInt(2, _nodeId);
                    int count = pstmt.executeUpdate();
                    System.out.println("父节点内move操作," + count + "行数据被更新！");
                }
            } else {
                //移出了父节点
                //更新当前节点的祖先路径
                String update1 = "update " + _tableName +
                                 " set nodeOrder=?,parentPath=? where nodeId=?";
                //更新当前节点所有子节点的祖先路径
                String update2 = "update " + _tableName +
                                 " set parentPath=? where parentPath like ?";
                pstmt = conn.prepareStatement(update1);
                pstmt.setFloat(1, newParent.judgeNodeOrder(index));
                pstmt.setString(2, newParent.getPathStr());
                pstmt.setInt(3, _nodeId);
                int count = pstmt.executeUpdate();
                if (count == 1)
                    System.out.println("父节点外move操作,被拖动节点更新！");
                else {
                    System.err.println("被拖动节点没有被改变！？");
                }
                pstmt = conn.prepareStatement(update2);
                pstmt.setString(1, newParent.getPathStr());
                pstmt.setString(2, getPathStr() + "%");
                count = pstmt.executeUpdate();
                System.out.println("父节点外move操作,被移动节点所含" + count + "个子节点数据被更新！");
            }
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                System.out.println("回滚失败！");
                e.printStackTrace();
            }
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(pstmt);
            DB.closeConnection(conn);
        }
        oldParent._childrenLoaded = false;
        newParent._childrenLoaded = false;
    }

    public void dbDelete() {
        //级联删除，删除节点和所有子节点；
        System.out.println("Delete DbNode");
        Connection conn = null;
        PreparedStatement pstmt1 = null, pstmt2 = null;
        String delete = "delete from " + _tableName + " where nodeId=?";
        String deleteCld = "delete from " + _tableName + " where parentPath=?";
        try {
            conn = _ds.getConnection();
            conn.setAutoCommit(false);
            pstmt1 = conn.prepareStatement(delete);
            pstmt1.setInt(1, _nodeId);
            pstmt1.execute();
            pstmt2 = conn.prepareStatement(deleteCld);
            if (getPathStr() == null || getPathStr().equals("")) {
                System.err.println("错误:删除异常，可能导致误删除操作。");
                return;
            }
            pstmt2.setString(1, getPathStr() + "%");
            pstmt2.execute();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            DB.closeStatement(pstmt1);
            DB.closeStatement(pstmt2);
            DB.closeConnection(conn);
        }
        ((DefaultDbTreeNode) getParent())._childrenLoaded = false;
    }

    /*数据库操纵代码模板
      Connection conn=null;
     Statement stmt=null;
     ResultSet rs=null;
     try{
      conn=_ds.getConnection();

     }catch(SQLException ex){
      ex.printStackTrace();
     }finally{
      DB.closeResultSet(rs);
      DB.closeStatement(stmt);
      DB.closeConnection(conn);
     }
     * */
    /***自定义方法***/
    public String getPathStr() {
        if (_parentPath == null)
            return _nodeId + ",";
        else
            return _parentPath + _nodeId + ",";
    }
}
