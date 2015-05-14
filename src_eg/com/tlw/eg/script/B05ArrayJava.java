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
public class B05ArrayJava {

	public static void main(String[] args) throws FileNotFoundException, ScriptException, URISyntaxException {
		ScriptEngineManager manager=new ScriptEngineManager();
		ScriptEngine engine=manager.getEngineByName("javascript");
		URL u=A02EvalFile.class.getResource("arrayJava.js");
		File file=new File(u.toURI());
		engine.eval(new FileReader(file));
	}

}
