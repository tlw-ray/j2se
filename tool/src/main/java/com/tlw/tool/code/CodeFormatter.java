package com.tlw.tool.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import com.tlw.io.DirSpreader;



/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-10
@version:2008-12-10
Descript:对代码进行格式上的美化和压缩，使得更为可读。
1.去掉多余的空行
2.压缩DOC注释。
 */
public class CodeFormatter {
	public static void main(String[] args) {
		System.out.println("----------Begin------------");
		File dir=new File("D:\\app.java\\workspace\\Magus-20081209-MyNet\\src");
		DirSpreader dirSpreader=new DirSpreader(dir,"java");
		List<File> fileList=dirSpreader.getFileList();
		for(File file:fileList){
			String content=file2str(file);
			content=content.replaceAll("\\n[\\s\\*]*[\\n]+", "\n");//替换多余的由空格,*,回车组成的空行。
			content=content.replaceAll("/\\*\\*[\\n\\s\\*]+", "/**");//压缩注释行首
			content=content.replaceAll("[\\n\\s\\*]+\\*/", "*/");//压缩注释行尾
			str2file(content,file);
			System.out.println(file.getPath());
		}
		System.out.println("--------Finish-----------");
	}
	static String file2str(File file){
		String content="",line;
		try{
			FileReader fis=new FileReader(file);
			BufferedReader br=new BufferedReader(fis);
			while((line=br.readLine())!=null)
				content+=line+"\n";
			br.close();
			fis.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return content;
	}
	static void str2file(String content,File file){
		try{
			FileWriter fw=new FileWriter(file);
			fw.write(content);
			fw.flush();
			fw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
