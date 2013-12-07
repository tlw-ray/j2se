package com.tlw.tool.shower;

import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-2-20
@version:2009-2-20
Description:展示用觉默认值,一般用来获取字体信息
 */
public class ShowUIDefaults {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		new JFrame();
		//TODO:请注意这里keys()与keyset()之间的区别。寻找此区别之间的原因
		UIDefaults defaults=UIManager.getDefaults();
//		System.out.println("------------UIDefaults.keyset()--------------");
//		for(Object obj:defaults.keySet()){
//			System.out.println(obj.toString()+":::"+defaults.get(obj));
//		}
		System.out.println("------------UIDefaults.keys()--------------");
        Enumeration<Object> keys = defaults.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
             System.out.println(key.toString()+":::"+defaults.get(key));
        }
	}
}
