package com.tlw.eg.anno;

import java.lang.reflect.InvocationTargetException;

import com.tlw.eg.anno.Yts.YtsType;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月28日
 */
@Yts(classType = YtsType.service)
public class SayHello {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, InstantiationException {
		ParseAnnotation parse = new ParseAnnotation();
		parse.parseMethod(SayHello.class);
		parse.parseType(SayHello.class);
	}

	@HelloWorld(name = " AA ")
	public void sayHello(String name) {
		if (name == null || name.equals("")) {
			System.out.println("hello world!");
		} else {
			System.out.println(name + "say hello world!");
		}
	}
}
