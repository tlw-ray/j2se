package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B12AccessSuperclass {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		String script="var Exception = Java.type('java.lang.Exception');"
				+ "var ExceptionAdapter=Java.extend(Exception);"
				+ "var exception=new ExceptionAdapter('My Exception Message'){"
				+ "getMessage:function(){"
				+ "var _super_ = Java.super(exception);"
				+ "return _super_.getMessage().toUpperCase();"
				+ "}"
				+ "};"
				+ "try{throw exception;"
				+ "}catch(ex){"
				+ "print(exception)}";
		engine.eval(script);
	}

}
