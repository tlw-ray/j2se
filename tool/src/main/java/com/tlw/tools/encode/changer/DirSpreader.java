package com.tlw.tools.encode.changer;

import java.io.File;
import java.io.FileFilter;
import java.util.List;
import java.util.Vector;

/*******************************
Author:唐力伟
E-Mail:tlw_ray@163.com
Date:2008-10-10
Description:根据给定扩展名遍历目录，列举所有文件的类
 ********************************/
public class DirSpreader {
	public static void main(String[] args){
		File dir=new File("D:\\app.java\\workspace\\TLW_TOOLS\\src\\");
		DirSpreader dirSpreader=new DirSpreader(dir,"java");
		List<File> lst=dirSpreader.getFileList();//<File>
		for(int i=0;i<lst.size();i++){
			File file=(File)lst.get(i);
			System.out.println(file.getAbsolutePath());
		}
	}
	FileFilter filter;
	List<File> fileList=new Vector<File>();//<File>
	public DirSpreader(File dir,String extName){
		filter=new FileExtFilter(extName.toLowerCase());
		throwFileList(dir);
	}
	private void throwFileList(File dir){
		File[] files=dir.listFiles(filter);
		for(int i=0;i<files.length;i++){
			File ff=files[i];
			if(ff.isFile())
				fileList.add(ff);
			else 
				throwFileList(ff);
		}
	}
	public List<File> getFileList(){//<File>
		return fileList;
	}
	//返回目录和指定扩展名的文件。
	static class FileExtFilter implements FileFilter{
		String fileExt;
		public FileExtFilter(String fileExtName){
			fileExt=fileExtName;
		}
		public boolean accept(File pathname) {
			if(pathname.isDirectory())return true;
			if(pathname.getName().toLowerCase().endsWith("."+fileExt))return true;
			return false;
		}
	}
}
