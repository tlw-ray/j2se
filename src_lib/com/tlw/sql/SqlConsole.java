package com.tlw.sql;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;
public class SqlConsole {
	DataSource _ds;
	public static void main(String[] args) {
		//HOW TO USE:
		DataSource someDataSource=null;//这里替换为要连接数据库的DataSource;
		SqlConsole c=new SqlConsole(someDataSource);
		c.run();
	}
	public SqlConsole(){}
	public SqlConsole(DataSource ds){
		_ds=ds;
		Connection conn=null;
		try{
			conn=ds.getConnection();
			System.out.println(conn.getMetaData().getDatabaseProductName()+" "+
					conn.getMetaData().getDatabaseProductVersion()+" Connected!");
			System.out.println(conn.getCatalog()+" Data Base Opened!");
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			DB.closeConnection(conn);
		}
	}
	public void run(){
		String readed="";
		System.out.println("--Enter sql line to run and 'exit' to Exit.--");
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		try{
			conn=_ds.getConnection();
			stmt=conn.createStatement();
			do{
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				try {
					readed=br.readLine();
					rs=null;
					if(stmt.execute(readed)){
						rs=stmt.getResultSet();
					}else if(stmt.getMoreResults()){
						rs=stmt.getResultSet();
					}
					if(rs!=null){
						DB.showResultSet(rs);
					}
					System.out.println("#SQL RUNED!");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}while(!readed.equalsIgnoreCase("exit"));
		}catch(SQLException ex){
			ex.printStackTrace();
		}finally{
			DB.closeConnection(conn);
		}
		System.out.println("----SqlConsole Exited!---");
	}
}
