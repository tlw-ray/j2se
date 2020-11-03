package com.tlw.eg.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class A05InvokingScriptObjectMethod {

	public static void main(String[] args) throws ScriptException, NoSuchMethodException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		engine.eval("var obj=new Object()");
		engine.eval("obj.hello=function(name){return 'Hello, '+name}");
		
		Object obj=engine.get("obj");
		
		Invocable inv=(Invocable)engine;
		
		Object result=inv.invokeMethod(obj, "hello", "Script Method!");
		System.out.println(result);
	}

}
