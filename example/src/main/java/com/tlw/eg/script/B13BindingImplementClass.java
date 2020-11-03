package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B13BindingImplementClass {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		String script="var Runnable=java.lang.Runnable;"
				+ "var r1=new Runnable(function(){print('Im runnable 1!')});"
				+ "var r2=new Runnable(function(){print('Im runnable 2!')});"
				+ "r1.run();"
				+ "r2.run();"
				+ "print('We share the same class: '+ (r1.class===r2.class))";
		engine.eval(script);
	}

}
