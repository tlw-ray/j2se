package com.tlw.eg.script;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class A08MultipleScopes {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		//set global variable
		engine.put("x", "hello");
		
		//evaluate JavaScript code that prints the variable (x="hello")
		engine.eval("print(x)");
		
		//define a different script context
		ScriptContext newContext=new SimpleScriptContext();
		newContext.setBindings(engine.createBindings(), ScriptContext.ENGINE_SCOPE);
		Bindings engineScope=newContext.getBindings(ScriptContext.ENGINE_SCOPE);
		
		//set the variable to a different value in another scope
		engineScope.put("x", "world");
		
		engine.eval("print(x)", newContext);
	}

}
