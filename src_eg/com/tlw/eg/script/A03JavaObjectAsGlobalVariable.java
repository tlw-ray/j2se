package com.tlw.eg.script;

import java.io.File;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class A03JavaObjectAsGlobalVariable {

	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		File file=new File("test.txt");
		engine.put("file", file);
		Object result=engine.eval("file.getAbsolutePath()");
		System.out.println(result);
	}

}
