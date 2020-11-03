package com.tlw.eg.io;

import java.io.File;
import java.io.IOException;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-5-14
@version:2009-5-14
Description:
 */
public class CreateDir {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String dirPath="D:\\dir1\\aaa\\bbb";
		File dir=new File(dirPath);
		boolean result=dir.mkdirs();
		System.out.println(result);
	}

}
