package com.tlw.swing.dbtable;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class ResultSetModel extends AbstractTableModel{
	private static final long serialVersionUID = 5083187039855263775L;
	ResultSet _rs;
    public ResultSetModel(ResultSet rs){
        _rs=rs;
    }
    public int getRowCount() {
        try {
            _rs.last();
            return _rs.getRow();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    public int getColumnCount() {
        try {
           return  _rs.getMetaData().getColumnCount();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            _rs.absolute(rowIndex+1);
            return _rs.getObject(columnIndex+1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    //非必要继承的
    public Class getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    public String getColumnName(int columnIndex) {
        return super.getColumnName(columnIndex);
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }
}
