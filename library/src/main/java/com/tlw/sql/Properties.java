package com.tlw.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public abstract class Properties {
    public String getProperty(String name){
        Connection conn=null;
        Statement stmt=null;
        try {
            conn=getDbConnection();
            stmt = conn.createStatement();
            String sql="select "+getValueField()+" from "+getTableName()+" where "+getNameField()+"='"+name+"'";
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next())return rs.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            DB.closeStatement(stmt);
            DB.closeConnection(conn);
        }
        return null;
    }
    public void setProperty(String name,String value){
        String oldValue=getProperty(name);
        Connection conn=null;
        Statement stmt=null;
        try{
            conn=getDbConnection();
            stmt=conn.createStatement();
            String sql="";
            if (oldValue == null) {
                //insert
                sql="insert into "+getTableName()+" ("+getNameField()+","+getValueField()+") values ('"+name+"','"+value+"')";;
            }else if (!oldValue.equals(value)) {
                //update
                sql="update "+getTableName()+" set "+getValueField()+"='"+value+"' where "+getNameField()+"='"+name+"'";;
            }else return;
            stmt.executeUpdate(sql);
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            DB.closeStatement(stmt);
            DB.closeConnection(conn);
        }
    }
    public void deleteProperty(String name){
        String oldValue=getProperty(name);
        if(oldValue!=null){
            Connection conn=getDbConnection();
            Statement stmt=null;
            try{
                stmt=conn.createStatement();
                String sql="delete from "+getTableName()+" where "+getNameField()+"='"+name+"'";
                stmt.execute(sql);
            }catch(SQLException ex){
                ex.printStackTrace();
            }finally{
                DB.closeStatement(stmt);
                DB.closeConnection(conn);
            }
        }
    }
    //<String,String> 
    public HashMap getKeyValueMap(){
        Connection conn=null;
        Statement stmt=null;
        try{
            conn=getDbConnection();
            stmt=conn.createStatement();
            String sql="select "+getNameField()+","+getValueField()+" from "+getTableName();
            ResultSet rs=stmt.executeQuery(sql);
            //<String,String>
            HashMap hashMap=new HashMap();
            while(rs.next()){
                hashMap.put(rs.getString(1),rs.getString(2));
            }
            return hashMap;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            DB.closeStatement(stmt);
            DB.closeConnection(conn);
        }
        return null;
    }
    /***/
    abstract public String getNameField();
    abstract public String getValueField();
    abstract public String getTableName();
    abstract public Connection getDbConnection();
}
