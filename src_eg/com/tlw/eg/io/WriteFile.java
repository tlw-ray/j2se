package com.tlw.eg.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年7月8日
 */
public class WriteFile {

	public static void main(String[] args) throws IOException {
		File file=new File("temp.dat");
		file.createNewFile();
		System.out.println(file.length());
		
		for(int i=0;i<3;i++){
			FileWriter fw=new FileWriter(file);
			fw.append("aaa");
			fw.flush();
			fw.close();
			
			System.out.println(file.length());
		}
		
		
	}

}
