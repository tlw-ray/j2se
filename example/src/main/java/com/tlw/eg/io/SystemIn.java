package com.tlw.eg.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-3-30
@version:2009-3-30
Description:
 */
public class SystemIn {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		String line=br.readLine();
		String[] strs=line.split(" ");
		int[] a=new int[strs.length];
		for(int i=0;i<strs.length;i++){
			a[i]=Integer.parseInt(strs[i]);
		}
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	} 
}
