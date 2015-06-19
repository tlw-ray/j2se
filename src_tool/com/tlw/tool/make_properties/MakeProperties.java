package com.tlw.tool.make_properties;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年6月19日
 */
public class MakeProperties {

	public static void main(String[] args) throws IOException {
		String home="E:\\magus\\workspace2015\\com.magus.opmodel\\src\\com\\magus\\opmodel\\calc\\";
//		home+="operations\\";
//		home+="out\\";
//		home+="visualisation\\";
		home+="visualisation\\";
		
		
		File homeDir=new File(home);
		File[] files=homeDir.listFiles();
		for(File file:files){
			String fileName=file.getName();
			if(!file.isDirectory() && fileName.toLowerCase().endsWith(".java")){
				String propertyName=fileName.substring(0, fileName.lastIndexOf('.'));
				String propertyFileName=home+propertyName+".properties";
				String propertyFileName_zh=home+propertyName+"_zh.properties";
				createFile(propertyFileName);
				createFile(propertyFileName_zh);
			}
		}
	}
	private static void createFile(String fileName) throws IOException{
		File file=new File(fileName);
		if(!file.exists()){
			if(file.createNewFile()){
				Logger.getAnonymousLogger().info("Create: "+file.getPath());
			}else{
				Logger.getAnonymousLogger().warning("Create: "+file.getPath());
			}
		}
	}
}
