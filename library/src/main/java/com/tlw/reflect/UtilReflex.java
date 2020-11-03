package com.tlw.reflect;

import java.lang.reflect.Method;
import java.util.Vector;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-10
@version:2008-12-10
Descript:
 */
public class UtilReflex {
	/**
	 * 解析对象的可读属性，获得此对象所有的get且无参的方法到getMethods
	 * @param obj 要被解析的对象
	 * @return 返回对象中所有get开头无参数的Method对象
	 */
	public static Vector getReadAbleMethods(Object obj){
		Method[] methods=obj.getClass().getMethods();
		Vector readAbleMethods=new Vector();
		for(int i=0;i<methods.length;i++){
			Method method=methods[i];
			String name=method.getName();
			int paramCount=method.getParameterTypes().length;
			if(name.startsWith("get") && paramCount==0) {
				readAbleMethods.add(method);
			}
		}
		return readAbleMethods;
	}
}
