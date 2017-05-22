package com.tlw.eg.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
@since 2011-4-23
@author 唐力伟 (liwei.tang@magustek.com)
 */
public class LogConfiged {
	public static void main(String[] args) throws SecurityException, IOException {
		System.out.println(System.getProperty("user.home"));
		System.out.println(LogManager.getLogManager().getClass());
		
		readConfig1();
//		readConfig2();
//		readConfig3();
		
		FileHandler f;
		
		Logger.getAnonymousLogger().finest("finest");
		Logger.getAnonymousLogger().finer("finer");
		Logger.getAnonymousLogger().fine("fine");
		Logger.getAnonymousLogger().info("info");
		Logger.getAnonymousLogger().warning("warning");
		Logger.getAnonymousLogger().severe("severe");
	}
	static void readConfig1() throws SecurityException, IOException{
		InputStream is=LogConfiged.class.getClassLoader().getResourceAsStream("tlw/example/log/log.properties");
		LogManager.getLogManager().readConfiguration(is);
	}
	static void readConfig2() throws SecurityException, IOException{
		InputStream is=LogConfiged.class.getResourceAsStream("log.properties");
		LogManager.getLogManager().readConfiguration(is);
	}
	static void readConfig3() throws SecurityException, IOException{
		System.setProperty("java.util.logging.config.file", "log/log.properties");
		LogManager.getLogManager().readConfiguration();
	}
}
