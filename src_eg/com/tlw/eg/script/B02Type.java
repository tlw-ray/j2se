package com.tlw.eg.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B02Type {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		
		engine.eval("var ArrayList=Java.type('java.util.ArrayList')");
		engine.eval("var a=new ArrayList");
		
		//All of the following are true
		engine.eval("print(a instanceof ArrayList)");
//		engine.eval("print(a instanceof a.getClass())");	//Example fail...
		engine.eval("print(a instanceof a.getClass().static)");
		engine.eval("print(a.getClass()!==ArrayList)");
		engine.eval("print(a.getClass().static === ArrayList)");
	}

}
