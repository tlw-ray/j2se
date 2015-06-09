package com.tlw.eg.awt;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author liwei.tang@magustek.com
 * @since 2015年5月30日
 */
public class Browser {

	public static void main(String[] args) throws URISyntaxException, IOException {
		URI uri=new URI("http://www.baidu.com");
		Desktop.getDesktop().browse(uri);
	}

}
