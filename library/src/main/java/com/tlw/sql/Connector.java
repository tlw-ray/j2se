package com.tlw.sql;
import java.sql.Connection;
import java.sql.DriverManager;
public class Connector {
    public static Connection ConnectToMSAccess(String mdbFile){
        try {
            Connection conn = null;
            String strurl="jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="+mdbFile;
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            conn=DriverManager.getConnection(strurl);
            return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
