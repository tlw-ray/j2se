package com.tlw.swing.dbtree;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.derby.jdbc.EmbeddedDataSource;

import com.tlw.sql.DB;


/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-6
@version:2009-5-6
Description:
 */
public class InitializeDB {
	public static void main(String[] args) throws Exception {
		EmbeddedDataSource ds=new EmbeddedDataSource();
		ds.setDatabaseName("D:\\data\\Derby\\dbTreeExample");
		Connection conn=ds.getConnection();
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery("select * from dbtree");
		DB.showResultSet(rs);
	}
	public static void clearData(){
		String sqlDelete="delete";
	}
	public static void initData(){
		
	}
}
