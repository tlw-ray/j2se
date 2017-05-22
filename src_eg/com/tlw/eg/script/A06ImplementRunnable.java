package com.tlw.eg.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 * Implementing a java interface with script function
 */
public class A06ImplementRunnable {

	public static void main(String[] args) throws ScriptException, InterruptedException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		engine.eval("function run(){print('run() function called.')}");
		
		Invocable inv=(Invocable)engine;
		Runnable r=inv.getInterface(Runnable.class);
		
		Thread th=new Thread(r);
		th.start();
		th.join();
	}

}
