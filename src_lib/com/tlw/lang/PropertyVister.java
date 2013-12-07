package com.tlw.lang;

import java.lang.reflect.Field;

public class PropertyVister {
	public static void main(String[] args) {

	}
	public static void showProperties(Object obj){
		Field[] flds=obj.getClass().getDeclaredFields();
		for(int i=0;i<flds.length;i++){
			System.out.println(flds[i]);
		}
	}
}
