package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B08ImplementsJava {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		String script="var r=new java.lang.Runnable(){"
				+ "run: function(){"
				+ "print('running...\\n')}};"
				+ "var th=new java.lang.Thread(r);"
				+ "th.start();"
				+ "th.join();";
		engine.eval(script);
	}

}
