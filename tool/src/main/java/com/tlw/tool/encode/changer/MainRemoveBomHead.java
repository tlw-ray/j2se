package com.tlw.tool.encode.changer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-7-27
@version:2009-7-27
Description:
 */
public class MainRemoveBomHead {
	public static void main(String[] args) {
		String path="";
		path="D:\\app.java\\workspace\\M-DataSnap\\src";
		path="D:\\app.java\\workspace\\M-Common\\src";
		path="D:\\app.java\\workspace\\Magus-20090512-DataView-v2\\src\\magus\\dbf";
		path="D:\\app.java\\workspace\\Magus-20081021-MComponents\\src";
		removeBomHead(path,"java");
	}
	static byte bomUtf8B0=(byte)0xEF;
	static byte bomUtf8B1=(byte)0xBB;
	static byte bomUtf8B2=(byte)0xBF;
	static byte bomUtf16BE0=(byte)0xFE;
	static byte bomUtf16BE1=(byte)0xFF;
	static byte bomUtf16LE0=(byte)0xFF;
	static byte bomUtf16LE1=(byte)0xFE;
	public static void removeBomHead(String dir,String extName){
		DirSpreader spreader=new DirSpreader(new File(dir),extName);
		List<?> files=spreader.getFileList();
		for(int i=0;i<files.size();i++){
			File file=(File)files.get(i);
			System.out.println("Dispath:"+file.getAbsolutePath());
			try {
				FileInputStream fis = new FileInputStream(file);
				int b0=fis.read();
				int b1=fis.read();
				int b2=fis.read();
				System.out.println("B0:"+Integer.toHexString(b0)+" B1:"+Integer.toHexString(b1)+" B2:"+Integer.toHexString(b2));
				if((byte)b0==bomUtf8B0 && (byte)b1==bomUtf8B1 && (byte)b2==bomUtf8B2){
					System.out.println("has BOM!");
					String content="",line="";
					InputStreamReader reader=new InputStreamReader(fis,"UTF-8");
					BufferedReader br=new BufferedReader(reader);
					while((line=br.readLine())!=null)content+=line+"\r\n";
					br.close();
					reader.close();
					fis.close();
					FileOutputStream fos=new FileOutputStream(file);
					OutputStreamWriter writer=new OutputStreamWriter(fos,"UTF-8");
					BufferedWriter bw=new BufferedWriter(writer);
					bw.write(content);
					bw.close();
					writer.close();
					fos.close();
					System.out.println("REMOVE BOM!");
				}
			} catch (Exception e) {
				System.err.println(file.getName()+"去除BOM头发生错误:"+e.getMessage());
			}
		}
	}
}
