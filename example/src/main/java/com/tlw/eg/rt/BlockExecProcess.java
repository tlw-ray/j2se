package com.tlw.eg.rt;

import java.io.IOException;

/**
 * @author liwei.tang@magustek.com
 * @since 2016年2月26日
 * 阻塞，直至外部线程运行完毕
 */
public class BlockExecProcess {

	public static void main(String[] args) throws IOException, InterruptedException {
		Process process = Runtime.getRuntime().exec("notepad");
		process.waitFor();
	}

}
