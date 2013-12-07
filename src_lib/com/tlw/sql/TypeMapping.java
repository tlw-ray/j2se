package com.tlw.sql;

import java.sql.Date;
import java.sql.Types;
//Sql类型与JAVA类型的映射
public class TypeMapping{
    public static int getSqlType(Class cls){
        if(cls==Integer.class){
            return Types.INTEGER;
        }else if(cls==String.class){
            return Types.VARCHAR;
        }else if(cls==Double.class){
            return Types.DOUBLE;
        }else if(cls==Float.class){
            return Types.FLOAT;
        }else {
            return Types.VARCHAR;
        }
    }
    public static Class getJavaType(int SqlType){
        switch (SqlType) {
        case Types.INTEGER:
            return Integer.class;
        case Types.DOUBLE:
            return Double.class;
        case Types.FLOAT:
            return Float.class;
        case Types.DATE:
            return Date.class;
        case Types.TIMESTAMP:
            return Date.class;
        case Types.TIME:
            return Date.class;
        case Types.BIT:
            return Boolean.class;
        default:
            return String.class;
        }
    }
}
