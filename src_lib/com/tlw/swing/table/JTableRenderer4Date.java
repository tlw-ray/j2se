package com.tlw.swing.table;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableCellRenderer;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-2-2
@version:2009-2-2
Description:
 */
public class JTableRenderer4Date extends DefaultTableCellRenderer{
	private static final long serialVersionUID = -4050257250456743251L;
	public void setValue(Object value) { 
    if ((value != null) && (value instanceof java.util.Date)) { 
	    Date dateValue = (java.util.Date) value; 
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    value = formatter.format(dateValue); 
    } 
    super.setValue(value); 
  } 
}
