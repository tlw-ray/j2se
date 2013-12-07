package com.tlw.lang.management;
/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2008-12-29
@version:2008-12-29
Descript:
 */
public class Shower {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showMemoryState();
	}
	public static void showMemoryState(){
		long memoryMax=Runtime.getRuntime().maxMemory();
		long memoryFree=Runtime.getRuntime().freeMemory();
		long memoryTotal=Runtime.getRuntime().totalMemory();
		System.out.println("Memory State:\t [Max:"+memoryMax+"\t Total:"+memoryTotal+"\t Free:"+memoryFree+"]");
	}
}
