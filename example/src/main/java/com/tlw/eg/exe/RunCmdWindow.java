package com.tlw.eg.exe;

import java.io.IOException;

/**
 * @author tlw_ray
 * @since 2013-9-18
 */
public class RunCmdWindow {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		Runtime.getRuntime().exec("cmd /c start dir").waitFor();
	}

}
