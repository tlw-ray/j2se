package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B10ExtendsClass {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		String script="var Timer=Java.type('java.util.Timer');"
				+ "timer=new Timer();"
				+ "timer.schedule(function(){print('Hello World!')},1000);";
		engine.eval(script);
	}

}
