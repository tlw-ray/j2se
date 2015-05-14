package com.tlw.eg.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class A04InvokingScriptFunction {

	public static void main(String[] args) throws ScriptException, NoSuchMethodException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		engine.eval("function hello(name){return 'Hello, '+name+'!'}");
		Invocable inv=(Invocable)engine;
		Object result=inv.invokeFunction("hello", "Scription");
		System.out.println(result);
	}

}
