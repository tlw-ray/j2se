package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B01AccessingJavaCalsses {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		//use Java.type() function to accessing java classes
		engine.eval("var ArrayList=Java.type('java.util.ArrayList')");
		engine.eval("var intType=Java.type('int')");
		engine.eval("var StringArrayType=Java.type('java.lang.String[]')");
		engine.eval("var Int2DArrayType=Java.type('int[][]')");

		//instance new object
		//TODO example fail...
//		engine.eval("var anArrayList = new Java.type('java.util.ArrayList')");
	
//		//instantiate new object use constructor
		engine.eval("var defaultSizeArrayList=new ArrayList");
		engine.eval("var customSizeArrayList=new ArrayList(16)");
		
		//use the type object returned by Java.type();
		engine.eval("var File=Java.type('java.io.File')");
		engine.eval("File.createTempFile('nashorn', '.tmp')");
		System.out.println(System.getProperty("java.io.tmpdir"));
		
		//Access static inner class use dollar sign ($)
		engine.eval("var Float = Java.type('java.awt.geom.Arc2D$Float')");
		
		//access static inner class by class type object
		engine.eval("var Arc2D=Java.type('java.awt.geom.Arc2D')");
		engine.eval("var Float=Arc2D.Float");
	}

}
