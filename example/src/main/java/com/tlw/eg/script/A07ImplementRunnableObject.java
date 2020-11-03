package com.tlw.eg.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class A07ImplementRunnableObject {

	public static void main(String[] args) throws ScriptException, InterruptedException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		engine.eval("var obj=new Object()");
		engine.eval("obj.run=function(){print('obj.run() method called')}");
		
		Object obj=engine.get("obj");
		
		Invocable inv=(Invocable)engine;
		
		Runnable r=inv.getInterface(obj, Runnable.class);
	
		Thread th=new Thread(r);
		th.start();
		th.join();
	}

}
