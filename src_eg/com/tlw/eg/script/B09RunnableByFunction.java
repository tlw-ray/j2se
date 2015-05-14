package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B09RunnableByFunction {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		String script="function func(){"
				+ "print('I am func!');"
				+ "};"
				+ "var th=new java.lang.Thread(func);"
				+ "th.start();"
				+ "th.join();";
		
		engine.eval(script);
	}

}
