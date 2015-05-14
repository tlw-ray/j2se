package com.tlw.eg.script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月14日
 */
public class B04Import {

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException, ScriptException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		URL u=A02EvalFile.class.getResource("import2.js");
		File file=new File(u.toURI());
		engine.eval(new FileReader(file));
	}

}
