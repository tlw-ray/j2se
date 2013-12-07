package com.tlw.sql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.swing.JOptionPane;
public abstract class DB{
    public static void closeConnection(Connection conn){
        if(conn!=null)
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
            	conn=null;
            }
    }
    public static void closeStatement(Statement stmt) {
        if (stmt != null)
            try {
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
            	stmt=null;
            }
    }
    public static void closeResultSet(ResultSet rs){
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
            	rs=null;
            }
    }

    public static void showResultSet(ResultSet rs) {
        try {
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("-------TABLE ----------");
//            System.out.println("Schema:" + meta.getSchemaName(1));
//            System.out.println("Catalog:" + meta.getCatalogName(1));
//            System.out.println("TableName:" + meta.getTableName(1));
            System.out.print("FIELDS: ");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.print(meta.getColumnName(i) + " , ");
            }
            System.out.println();
            System.out.println("Data:");
            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + " , ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public abstract DataSource getDataSource();

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"数据库连接失败请检查网络连接状况并与管理员联系。/n错误信息:"+e.getMessage());
            return null;
        }
    }
    public static void doQuery(Connection conn,String sql){
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			showResultSet(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
}
