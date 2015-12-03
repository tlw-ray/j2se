package com.tlw.eg.anno;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月28日
 */
public class ParseAnnotation {

	public void parseMethod(Class clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, InstantiationException {
		Object obj = clazz.getConstructor(new Class[] {}).newInstance(new Object[] {});
		for (Method method : clazz.getDeclaredMethods()) {
			HelloWorld say = method.getAnnotation(HelloWorld.class);
			String name = "";
			if (say != null) {
				name = say.name();
				method.invoke(obj, name);
			}
//			Yts yts = (Yts) method.getAnnotation(Yts.class);
//			if (yts != null) {
//				if (YtsType.util.equals(yts.classType())) {
//					System.out.println("this is a util method");
//				} else {
//					System.out.println("this is a other method");
//				}
//			}
		}
	}

	@SuppressWarnings("unchecked")
	public void parseType(Class clazz) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Yts yts = (Yts) clazz.getAnnotation(Yts.class);
		System.out.println(yts.classType());
	}

}
