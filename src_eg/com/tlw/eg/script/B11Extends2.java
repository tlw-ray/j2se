package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B11Extends2 {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		String script="var Thread=Java.type('java.lang.Thread');"
				+ "var threadExtender=Java.extend(Thread);"
				+ "var t=new threadExtender(){"
				+ "run: function(){print('Thread running!')}"
				+ "};"
				+ "t.start();"
				+ "t.join();";
		engine.eval(script);
	}

}
